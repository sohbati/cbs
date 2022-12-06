package com.sina.cbs.information.infrastructure.security;

import java.lang.reflect.Method;
import java.util.Arrays;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sina.cbs.information.infrastructure.security.annotation.Resource;
import com.sina.cbs.information.infrastructure.security.jwt.JWTDecoderUtil;
import com.sina.cbs.information.infrastructure.security.jwt.record.ResourceScopeRecord;
import com.sina.cbs.information.infrastructure.security.jwt.record.TokenPayloadRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sina.cbs.information.infrastructure.IntrastructureErrorCode;
import com.sina.cbs.information.infrastructure.exception.ServiceException;
import com.sina.cbs.information.infrastructure.security.annotation.ActionScope;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SecurityInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        HandlerMethod hm = null;
        try {
            hm = (HandlerMethod) handler;
        } catch (ClassCastException e) {
            log.error(e.getMessage(), e);
            return true;
        }
        final Method method = hm.getMethod();
        AccessRequestRecord accessLevelRecord = getControllerAuthorizationAnnotation(method);

        return true;
//        return authorizeResourceCall(request, accessLevelRecord);
    }

    private boolean authorizeResourceCall(HttpServletRequest request, AccessRequestRecord accessRecord) {
        String token = extractTokenFromHeader(request);
        TokenPayloadRecord payloadRecord = null;
        String payload = JWTDecoderUtil.getPayload(token);
        try {
            payloadRecord = fromJsonPayload(payload);
        } catch (JsonProcessingException e) {
            if (log.isDebugEnabled()) {
                log.info(String.format("token %s", token));
            }
            log.error(e.getMessage(), e);
            throw ServiceException.codeAndTypeException(IntrastructureErrorCode.AUTH_TOKEN_EXCEPTION,
                    ServiceException.ExceptionType.AUTH);
        }
        return authorizeResource(payloadRecord, accessRecord);
    }

    private boolean authorizeResource(TokenPayloadRecord tokenPayloadRecord, AccessRequestRecord accessReqRec) {
        if (tokenPayloadRecord == null || tokenPayloadRecord.Authorization() == null ||
                tokenPayloadRecord.Authorization().permissions() == null ||
                tokenPayloadRecord.Authorization().permissions().isEmpty()) {
            return false;
        }
        boolean hasResourcePermission = false;
        boolean hasScopePermission = false;
        for (ResourceScopeRecord item : tokenPayloadRecord.Authorization().permissions()) {
            if (item.resource().equalsIgnoreCase(accessReqRec.resource())) {
                hasResourcePermission = true;
            }
            if (Arrays.asList(item.scopes()).contains(accessReqRec.scope())) {
                hasScopePermission = true;
            }
        }
        if (!(hasResourcePermission && hasScopePermission)) {
            log.error("No access to the resource");
            if (log.isDebugEnabled()) {
                log.debug(String.format("The method resource and scope is[%s,%s]",
                        accessReqRec.resource(), accessReqRec.scope()));
                log.debug("Token claims is: %s", tokenPayloadRecord);
            }

            throw ServiceException.codeTypeArgsException(
                    IntrastructureErrorCode.ACCESS_DENIED_FOR_REQUESTED_RESOURCE_AND_SCOPE,
                    ServiceException.ExceptionType.AUTH, "");
        }

        return true;
    }

    private TokenPayloadRecord fromJsonPayload(String payload) throws JsonMappingException, JsonProcessingException {
        return objectMapper.readValue(payload, TokenPayloadRecord.class);
    }

    private String extractTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.length() < 7) {
            if (log.isDebugEnabled()) {
                log.info(String.format("token %s", token));
            }
            throw ServiceException.codeAndTypeException(IntrastructureErrorCode.AUTH_TOKEN_EXCEPTION,
                    ServiceException.ExceptionType.AUTH);
        }

        return token.substring(7, token.length());
    }

    private AccessRequestRecord getControllerAuthorizationAnnotation(Method method) {
        String resource = "";
        String scope = "";
        if (method.getDeclaringClass().isAnnotationPresent(Resource.class)) {
            resource = method.getDeclaringClass().getAnnotation(Resource.class).name();
        }

        if (method.isAnnotationPresent(ActionScope.class)) {
            scope = method.getAnnotation(ActionScope.class).scope();

        }
        return new AccessRequestRecord(resource, scope);
    }
}
