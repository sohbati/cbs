package com.sina.cbs.information.office.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Entity
@Table(name = "OFFICE")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Office {
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Office parent;

    @Column(name = "OFFICE_NAME", length = 250, nullable = false, unique = true)
    private String officeName;

    @Column(name = "OFFICE_CODE", length = 250, nullable = false, unique = true)
    private String officeCode;

    @Column(name = "OFFICE_DESCIPTION", length = 500, nullable = true, unique = false)
    private String officeDescription;

    @Column(name = "hierarchy", nullable = true, length = 50)
    private String hierarchy;

    @Column(name = "opening_date", nullable = false)
    private LocalDateTime openingDatetDateTime;

    public static Office newOffice(
            String id, Office parent, String name, String code, String description, String hirarchy,
            LocalDateTime openingDatetDateTime) {
        return new Office(id, parent, name, code, description, hirarchy, openingDatetDateTime);
    }
    
    public static Office newOffice(
            String id, String name, String code, String description,
            LocalDateTime openingDatetDateTime) {
        return new Office(id, null, name, code, description, null, openingDatetDateTime);
    }
}
