package com.sina.cbs.information.infrastructure.security.jwt.record;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Authorization(
        ArrayList<ResourceScopeRecord> permissions) {
}
