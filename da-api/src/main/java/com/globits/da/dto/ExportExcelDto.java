package com.globits.da.dto;

import org.apache.poi.ss.formula.functions.Index;

public class ExportExcelDto {

    private int index;
    private String name;
    private String code;
    private String email;
    private String phone;
    private int age;

    public ExportExcelDto(ExportExcelDto dto) {
        this.code = dto.getCode();
        this.name = dto.getName();
        this.index = dto.getIndex();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.age = dto.getAge();
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

    public Integer getIndex() {
        return index;
    }
    public void setId(Integer index) {
        index = this.index;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
