package com.globits.da.dto;

import com.globits.da.domain.EmployeeCertificate;

import java.time.LocalDate;

public class EmployeeCertificateDto {

    private Long id;
    private Long employeeId;
    private Long certificateId;
    private Long provinceId;


    public EmployeeCertificateDto(EmployeeCertificate employeeCertificate) {
        if (employeeCertificate != null) {

            this.id = employeeCertificate.getId();

            this.provinceId = employeeCertificate.getProvince() != null ? employeeCertificate.getProvince().getId() : null;
            this.employeeId = employeeCertificate.getEmployee() != null ? employeeCertificate.getEmployee().getId() : null;
            this.certificateId = employeeCertificate.getCertificate() != null ? employeeCertificate.getCertificate().getId() : null;
        }
    }

    //getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Long certificateId) {
        this.certificateId = certificateId;
    }

}
