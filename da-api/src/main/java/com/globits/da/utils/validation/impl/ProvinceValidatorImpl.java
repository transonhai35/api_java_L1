package com.globits.da.utils.validation.impl;

import com.globits.da.repository.ProvinceRepository;
import com.globits.da.utils.validation.ValidProvince;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ProvinceValidatorImpl implements ConstraintValidator<ValidProvince, Long> {

    @Autowired
    private ProvinceRepository provinceRepo;


    @Override
    public void initialize(ValidProvince constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {

        // Validate Province
        if (id == null) {
            return true; //@NotNull handle
            }
        return provinceRepo.existsById(id);
    }


}

