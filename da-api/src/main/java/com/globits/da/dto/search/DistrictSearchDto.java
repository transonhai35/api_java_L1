package com.globits.da.dto.search;

public class DistrictSearchDto {

    private Long id;
    private String codeProvince;
    private String codeDistrict;
    private String name;

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
