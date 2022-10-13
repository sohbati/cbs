package com.sina.cbs.information.infrastructure.exception;

import java.time.LocalDateTime;
import java.util.Locale;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class ExceptionResponse {
    private int status;
    private String errorCode;
    private String errorMessage;
    private LocalDateTime time;
    private String service;
    private Object extraData;
    private Locale locale;
}
