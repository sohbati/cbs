package com.sina.cbs.information.infrastructure.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ServiceException extends RuntimeException {
    public enum ExceptionType {
        AUTH, RESOUCE_NOT_FOUND
    }

    private final String code;
    private final ExceptionType type;
    private final String extraData;
    private final String[] args;

    public static ServiceException codeOnlyException(String code) {
        return new ServiceException(code, null, null, null);
    }

    public static ServiceException codeAndTypeException(String code, ExceptionType type) {
        return new ServiceException(code, type, null, null);
    }

    public static ServiceException codeTypeArgsException(String code, ExceptionType type, String... args) {
        return new ServiceException(code, type, null, args);
    }

}
