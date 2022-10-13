package com.sina.cbs.information.infrastructure.security;

public record AccessRequestRecord(
        String resource,
        String scope) {
}