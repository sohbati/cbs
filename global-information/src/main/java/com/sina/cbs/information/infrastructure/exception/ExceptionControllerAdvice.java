package com.sina.cbs.information.infrastructure.exception;

import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.sina.cbs.information.infrastructure.security.AuthorizationConstants;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<ExceptionResponse> handleException(ServiceException ex, WebRequest request) {
        HttpStatus status = getStatus(ex);

        ExceptionResponse response = new ExceptionResponse();
        response.setStatus(status.value());
        response.setLocale(request.getLocale());
        response.setErrorCode(ex.getCode());
        response.setTime(LocalDateTime.now());
        response.setService(AuthorizationConstants.RESOURCE_NAME_OFFICE);
        response.setErrorMessage(getLocalizedMessage(ex.getCode(), request.getLocale(), ex.getArgs()));

        return new ResponseEntity<>(response, status);
    }

    private HttpStatus getStatus(ServiceException ex) {
        if (ex.getType().equals(ServiceException.ExceptionType.AUTH)) {
            return HttpStatus.FORBIDDEN;
        } else if (ex.getType().equals(ServiceException.ExceptionType.RESOUCE_NOT_FOUND)) {
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private String getLocalizedMessage(String code, Locale locale, String[] args) {
      try {
        return messageSource.getMessage(code, args, locale);
      }catch(NoSuchMessageException ex) {
        return "No message found under code";
      }
    }
}
