package com.globits.da.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.globits.core.dto.AuditableEntityDto;
import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Certificate;
import com.globits.da.domain.Employee;
import com.globits.da.domain.Province;
import com.globits.da.utils.validation.ValidProvince;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class CertificateDto extends AuditableEntityDto {

    private Long id;
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate validFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate validTo;

    private Boolean expired;
    private Long provinceId;
    private Long employeeId;


    public CertificateDto() {}

    public CertificateDto(Certificate certificate) {
        if (certificate != null) {

            this.id = certificate.getId();

            this.name = certificate.getName();
            this.validFrom = certificate.getValidFrom();
            this.validTo = certificate.getValidTo();
            this.expired = certificate.getExpired();
            this.provinceId = certificate.getProvince() != null ? certificate.getProvince().getId() : null;
            this.employeeId = certificate.getEmployee() != null ? certificate.getEmployee().getId() : null;
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

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public Boolean getExpired(){
        return expired;
    }

    public void setExpired(Boolean expired){
        this.expired = expired;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId ){
        this.employeeId = employeeId;
    }

//    public Province getProvince() {
//        return province;
//    }
//
//    public void setProvince(Province province) {
//        this.province = province;
//    }
//
//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
}
