package com.globits.da.utils.validation.impl;

import com.globits.da.dto.EmployeeDto;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.utils.validation.ValidDistrict;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DistrictValidatorImpl implements ConstraintValidator<ValidDistrict, EmployeeDto> {

    @Autowired
    private DistrictRepository districtRepo;

    @Autowired
    private ProvinceRepository provinceRepo;

    @Override
    public void initialize(ValidDistrict constraintAnnotation) {}

    @Override
    public boolean isValid(EmployeeDto dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return true; // @NotNull handle
        }

        //Validate District belongs to Province
        if (dto.getDistrictId() != null && dto.getProvinceId() != null) {
            return districtRepo.existsByIdAndProvinceId(dto.getDistrictId(), dto.getProvinceId());

        }

        return true;
    }
}
