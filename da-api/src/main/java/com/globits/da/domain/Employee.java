package com.globits.da.domain;

import com.globits.core.domain.BaseObject;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.UUID;
import com.globits.core.auditing.AuditableEntity;

@Entity
@Table(name = "employee")
@XmlRootElement
public class Employee extends AuditableEntity {


    private static final long serialVersionUID = 1L;


    @Id
    @Column(
            name = "id",
            nullable = false,
            unique = true

    )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "age")
    private Integer age;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id < 0 || id > 0xFFFFFFFFL) {
            throw new IllegalArgumentException("ID must be in range 0 to 4294967295 (unsigned int range)");
        }
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

}
