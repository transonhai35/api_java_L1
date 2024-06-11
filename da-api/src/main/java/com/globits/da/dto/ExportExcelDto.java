package com.globits.da.dto;

public class ExportExcelDto {

    private String code;
    private String name;
    private Integer Id;

    public ExportExcelDto(String code, String name, Integer Id) {
        this.code = code;
        this.name = name;
        this.Id = Id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
}
