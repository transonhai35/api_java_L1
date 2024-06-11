package com.globits.da.domain;

import com.globits.core.auditing.AuditableEntity;
import com.globits.core.domain.BaseObject;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "commune")
@XmlRootElement
public class Commune extends AuditableEntity {

    @Id
    @Column(
            name = "id",
            nullable = false,
            unique = true

    )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "codeDistrict")
    private String codeDistrict;

    @Column(name = "codeCommune")
    private String codeCommune;

    @Column(name = "name")
    private String name;

    //getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id < 0 || id > 0xFFFFFFFFL) {
            throw new IllegalArgumentException("ID must be in range 0 to 4294967295 (unsigned int range)");
        }
        this.id = id;
    }

    public String getCodeDistrict() {
        return codeDistrict;
    }

    public void setCodeDistrict(String codeDistrict) {
        this.codeDistrict = codeDistrict;
    }

    public String getCodeCommune() {
        return codeCommune;
    }

    public void setCodeCommune(String codeCommune) {
        this.codeCommune = codeCommune;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
