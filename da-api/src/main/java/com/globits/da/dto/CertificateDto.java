package com.globits.da.dto;

import com.globits.core.dto.AuditableEntityDto;
import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Certificate;

import java.time.LocalDate;

public class CertificateDto extends AuditableEntityDto {

    private Long id;
    private String name;
    private LocalDate validFrom;
    private LocalDate validTo;

    public CertificateDto() {}

    public CertificateDto(Certificate certificate) {
        if (certificate != null) {

            this.id = certificate.getId();
            this.name = certificate.getName();
            this.validFrom = certificate.getValidFrom();
            this.validTo = certificate.getValidTo();
        }
    }

    //getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }
}
