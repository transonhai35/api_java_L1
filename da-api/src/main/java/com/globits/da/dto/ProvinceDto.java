package com.globits.da.dto;

import com.globits.core.dto.AuditableEntityDto;
import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Province;

public class ProvinceDto extends AuditableEntityDto {

    private Long id;
    private String name;

    public ProvinceDto() {}

    public ProvinceDto(Province province) {
        if (province != null) {

            this.id = province.getId();
            this.name = province.getName();
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

}
