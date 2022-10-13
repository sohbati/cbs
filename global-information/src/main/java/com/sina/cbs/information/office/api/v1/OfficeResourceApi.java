package com.sina.cbs.information.office.api.v1;

import com.sina.cbs.information.infrastructure.security.annotation.ActionScope;
import com.sina.cbs.information.infrastructure.security.annotation.Resource;
import com.sina.cbs.information.infrastructure.security.AuthorizationConstants;
import com.sina.cbs.information.office.dto.OfficeRequest;

import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1")
@Resource(name = AuthorizationConstants.RESOURCE_NAME_OFFICE)
@Log4j2
public class OfficeResourceApi {

    @PostMapping(value = "/offices")
    public HttpStatus createOffice(@RequestBody OfficeRequest officeRequest) {
        return HttpStatus.OK;
    }

    @GetMapping(value = "/offices")
    @ActionScope(scope = AuthorizationConstants.SCOPE_VIEW)
    public HttpStatus getOfficeList() {

        return HttpStatus.OK;
    }

}
