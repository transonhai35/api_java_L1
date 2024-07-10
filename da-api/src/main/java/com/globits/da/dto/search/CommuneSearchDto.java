package com.globits.da.dto.search;

import com.globits.da.domain.District;

public class CommuneSearchDto {

    private Long id;
    private String name;

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
