package com.globits.da.dto;

import com.globits.core.dto.AuditableEntityDto;
import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Commune;
import com.globits.da.domain.Province;

public class CommuneDto extends AuditableEntityDto {

    private Long id;
    private String codeDistrict;
    private String codeCommune;
    private String name;

    public CommuneDto() {}

    public CommuneDto(Commune commune) {
        if (commune != null) {

            this.id = commune.getId();
            this.codeDistrict = commune.getCodeDistrict();
            this.codeCommune = commune.getCodeCommune();
            this.name = commune.getName();
        }
    }

    //getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeCommune() {
        return codeCommune;
    }

    public void setCodeCommune(String codeCommune) {
        this.codeCommune = codeCommune;
    }

    public String getCodeDistrict() {
        return codeDistrict;
    }

    public void setCodeDistrict(String codeDistrict) {
        this.codeDistrict = codeDistrict;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
