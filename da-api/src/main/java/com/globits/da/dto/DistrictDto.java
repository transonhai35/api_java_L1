package com.globits.da.dto;

import com.globits.core.dto.AuditableEntityDto;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;

public class DistrictDto extends AuditableEntityDto {

    private Long id;
    private String codeProvince;
    private String codeDistrict;
    private String name;

    public DistrictDto() {}

    public DistrictDto(District district) {
        if (district != null) {

            this.id = district.getId();
            this.codeProvince = district.getCodeProvince();
            this.codeDistrict = district.getCodeDistrict();
            this.name = district.getName();
        }
    }

    //getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeProvince() {
        return codeProvince;
    }

    public void setCodeProvince(String codeProvince) {
        this.codeProvince = codeProvince;
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
