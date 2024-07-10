package com.globits.da.utils.validation.impl;

import com.globits.da.dto.EmployeeDto;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.utils.validation.ValidLocation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LocationValidatorImpl implements ConstraintValidator<ValidLocation, EmployeeDto> {

    @Autowired
    private ProvinceRepository provinceRepo;

    @Autowired
    private DistrictRepository districtRepo;

    @Autowired
    private CommuneRepository communeRepo;

    @Override
    public void initialize(ValidLocation constraintAnnotation) {
    }

    @Override
    public boolean isValid(EmployeeDto employeeDto, ConstraintValidatorContext context) {
        if (employeeDto == null) {
            return true; // @NotNull handle
        }

        // Validate Commune belongs to District
        if (employeeDto.getCommuneId() != null && employeeDto.getDistrictId() != null) {
            boolean communeBelongsToDistrict = communeRepo.existsByIdAndDistrictId(employeeDto.getCommuneId(), employeeDto.getDistrictId());
            if (!communeBelongsToDistrict) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Commune must belong to the selected District").addConstraintViolation();
                return false;
            }
        }

        // Validate District belongs to Province
        if (employeeDto.getDistrictId() != null && employeeDto.getProvinceId() != null) {
            boolean districtBelongsToProvince = districtRepo.existsByIdAndProvinceId(employeeDto.getDistrictId(), employeeDto.getProvinceId());
            if (!districtBelongsToProvince) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("District must belong to the selected Province").addConstraintViolation();
                return false;
            }
        }

        return true;
    }

}

