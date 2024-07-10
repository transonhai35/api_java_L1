package com.globits.da.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.globits.core.auditing.AuditableEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.List;

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

    @Column(name = "expired")
    private Boolean expired;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonBackReference
    private Employee employee;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "certificate", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<EmployeeCertificate> employeeCertificates;

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

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public Boolean getExpired(){
        return expired;
    }

    public void setExpired(Boolean expired){
        this.expired = expired;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


//    public List<EmployeeCertificate> getEmployeeCertificates() {
//        return employeeCertificates;
//    }
//
//    public void setEmployeeCertificates(List<EmployeeCertificate> employeeCertificates) {
//        this.employeeCertificates = employeeCertificates;
//    }


}
