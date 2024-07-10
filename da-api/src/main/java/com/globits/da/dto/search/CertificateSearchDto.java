package com.globits.da.dto.search;

import com.globits.da.domain.Certificate;
import com.globits.da.domain.Province;

import java.time.LocalDate;

public class CertificateSearchDto {

    private Long id;
    private String name;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Province pronvince;

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
