package com.sina.cbs.information.infrastructure;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IntrastructureErrorCode {
    
    public static final String AUTH_TOKEN_EXCEPTION = "error.token.invalid";
    public static final String ACCESS_DENIED_FOR_REQUESTED_RESOURCE_AND_SCOPE = "error.access.denied.rquested.resource.scope";

}
