package com.globits.da.dto.search;

public class CommuneSearchDto {

    private Long id;
    private String codeCommune;
    private String codeDistrict;
    private String name;

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
