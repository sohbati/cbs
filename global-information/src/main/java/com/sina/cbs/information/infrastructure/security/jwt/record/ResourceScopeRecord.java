package com.sina.cbs.information.infrastructure.security.jwt.record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResourceScopeRecord(
    @JsonProperty("rsname")
    String resource,
    @JsonProperty("scopes")
    String[] scopes
) {
}
