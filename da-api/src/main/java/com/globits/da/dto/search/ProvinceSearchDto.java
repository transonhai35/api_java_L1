package com.globits.da.dto.search;

import java.util.UUID;

public class ProvinceSearchDto {

    private Long id;
    private String codeProvince;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
