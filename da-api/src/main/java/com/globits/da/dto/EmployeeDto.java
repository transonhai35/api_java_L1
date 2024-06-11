package com.globits.da.dto;

import com.globits.core.dto.AuditableEntityDto;
import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Employee;

import javax.persistence.Column;
import org.joda.time.LocalDateTime;
import java.util.UUID;

public class EmployeeDto extends AuditableEntityDto {

    private Long id;
    private String code;
    private String name;
    private String email;
    private String phone;
    private Integer age;

    public EmployeeDto() {}

    public EmployeeDto(Employee entity) {
        if (entity != null) {

            this.id = entity.getId();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.email = entity.getEmail();
            this.phone = entity.getPhone();
            this.age = entity.getAge();
        }
    }

    //getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

//
//    public LocalDateTime getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(LocalDateTime createDate) {
//        this.createDate = createDate;
//    }
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }

    @Override
    public String toString() {
        return "MyEmplayeeDTO{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age + '\'' +
                ", email=" + email + '\'' +
                ", phone=" + phone +
                '}';
    }

}
