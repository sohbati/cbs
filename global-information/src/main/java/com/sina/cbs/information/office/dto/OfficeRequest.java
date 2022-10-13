package com.sina.cbs.information.office.dto;

import java.time.LocalDateTime;

public record OfficeRequest(
        String name,
        String officeCode,
        String description,
        LocalDateTime openingDate,
        String parentOfficeCode
        ) {
}
