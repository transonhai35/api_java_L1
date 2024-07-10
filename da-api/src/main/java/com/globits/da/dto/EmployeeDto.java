package com.globits.da.dto;

import com.globits.core.dto.AuditableEntityDto;
import com.globits.da.domain.Employee;
import com.globits.da.utils.validation.UniqueCode;
import com.globits.da.utils.validation.ValidCommune;
import com.globits.da.utils.validation.ValidDistrict;
import com.globits.da.utils.validation.ValidProvince;

import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

@ValidDistrict
@ValidCommune
public class EmployeeDto extends AuditableEntityDto {

    private Long id;


    @NotBlank(message = "Code cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,10}$", message = "Code must be 6-10 characters long and contain no spaces")
    @UniqueCode
    private String code;

    @NotBlank(message = "Name cannot be null")
    private String name;

    @NotBlank(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone cannot be null")
    @Pattern(regexp = "^[0-9]{1,11}$", message = "Phone number must be up to 11 digits and contain only numbers")
    private String phone;

    @NotNull(message = "Age cannot be null")
    @Min(value = 0, message = "Age cannot be negative")
    private Integer age;

    @NotNull(message = "Province cannot be null")
    @ValidProvince
    private Long provinceId;

    @NotNull(message = "District cannot be null")
    private Long districtId;

    @NotNull(message = "Commune cannot be null")
    private Long communeId;

    private List<CertificateDto> certificates;

    public EmployeeDto() {}

    public EmployeeDto(Employee employee) {
        if (employee != null) {
            this.id = employee.getId();
            this.code = employee.getCode();
            this.name = employee.getName();
            this.email = employee.getEmail();
            this.phone = employee.getPhone();
            this.age = employee.getAge();
            this.provinceId = employee.getProvinceId();
            this.districtId = employee.getDistrictId();
            this.communeId = employee.getCommuneId();

//          Convert the raw values to certificate objects
//            if (employee.getCertificates() != null) {
//                this.certificates = employee.getCertificates().stream()
//                       .map(CertificateDto::new)
//                        .collect(Collectors.toList());
//            }

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

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getCommuneId() {
        return communeId;
    }

    public void setCommuneId(Long communeId) {
        this.communeId = communeId;
    }

    public List<CertificateDto> getCertificates(){
        return certificates;
    }

    public  void setCertificates( List<CertificateDto> certificates){
        this.certificates = certificates;
    }

}
