package com.globits.da.utils.validation.impl;

import com.globits.da.dto.EmployeeDto;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.utils.validation.ValidCommune;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CommuneValidatorImpl implements ConstraintValidator<ValidCommune, EmployeeDto> {

    @Autowired
    private DistrictRepository districtRepo;

    @Autowired
    private CommuneRepository communeRepo;

    @Override
    public void initialize(ValidCommune constraintAnnotation) {}

    @Override
    public boolean isValid(EmployeeDto dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return true; // @NotNull handle
        }

        //Validate District belongs to Province
        if (dto.getDistrictId() != null && dto.getCommuneId() != null) {
            return communeRepo.existsByIdAndDistrictId( dto.getCommuneId(),dto.getDistrictId());
        }

        return true;
    }

}
