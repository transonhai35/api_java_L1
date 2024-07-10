package com.globits.da.dto;

import com.globits.core.dto.AuditableEntityDto;
import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Province;

import java.util.List;
import java.util.stream.Collectors;

public class ProvinceDto extends AuditableEntityDto {

    private Long id;
    private String name;
    private List<DistrictDto> districts;

    public ProvinceDto() {}

    public ProvinceDto(Province province) {
        if (province != null) {
            this.id = province.getId();
            this.name = province.getName();

            // Convert the raw values to District objects
            if (province.getDistricts() != null) {
                this.districts = province.getDistricts().stream()
                        .map(DistrictDto::new)
                        .collect(Collectors.toList());
            }

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

    public List<DistrictDto> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictDto> districts) {
        this.districts = districts;
    }

}
