package com.globits.da.utils.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.globits.da.repository.EmployeeRepository;
import com.globits.da.utils.validation.UniqueCode;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueCodeValidatorImpl implements ConstraintValidator<UniqueCode, String> {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public void initialize(UniqueCode constraintAnnotation) {
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        if (code == null) {
            return true; //@NotBlank handle
        }
        return !employeeRepo.existsByCode(code);
    }
}

