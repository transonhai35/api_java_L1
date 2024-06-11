package com.globits.da.domain;

import com.globits.core.auditing.AuditableEntity;
import com.globits.core.domain.BaseObject;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@Entity
@Table(name = "certificate")
@XmlRootElement
public class Certificate extends AuditableEntity {

    @Id
    @Column(
            name = "id",
            nullable = false,
            unique = true

    )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "validFrom")
    private LocalDate validFrom;

    @Column(name = "validTo")
    private LocalDate validTo;

    //getter setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id < 0 || id > 0xFFFFFFFFL) {
            throw new IllegalArgumentException("ID must be in range 0 to 4294967295 (unsigned int range)");
        }
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
}
