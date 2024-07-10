package com.globits.da.dto;

import com.globits.core.dto.AuditableEntityDto;
import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;



public class CommuneDto extends AuditableEntityDto {

    private Long id;
    private Long districtId;
    private District district;
    private String name;

    public CommuneDto() {}

    public CommuneDto(Commune commune) {
        if (commune != null) {

            this.id = commune.getId();
            this.districtId = commune.getDistrict() != null ? commune.getDistrict().getId() : null;
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

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
