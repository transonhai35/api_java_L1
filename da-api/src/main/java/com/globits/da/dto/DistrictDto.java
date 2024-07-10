package com.globits.da.dto;

import com.globits.core.dto.AuditableEntityDto;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;

import java.util.List;
import java.util.stream.Collectors;

public class DistrictDto extends AuditableEntityDto {

    private Long id;
    private Long provinceId;
    private Province province;
    private String name;
    private List<CommuneDto> communes;


    public DistrictDto() {}

    public DistrictDto(District district) {
        if (district != null) {
            this.id = district.getId();
            this.provinceId = district.getProvince() != null ? district.getProvince().getId() : null;
            this.name = district.getName();

            // Convert the raw values to Commune objects
            if(district.getCommunes() != null){
                this.communes = district.getCommunes().stream()
                        .map(CommuneDto::new)
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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CommuneDto> getCommunes() {
        return communes;
    }

    public void setCommunes(List<CommuneDto> communes) {
        this.communes = communes;
    }
}
