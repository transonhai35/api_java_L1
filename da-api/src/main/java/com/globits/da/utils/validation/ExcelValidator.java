package com.globits.da.utils.validation;

import com.globits.da.dto.EmployeeDto;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ExcelValidator {

    public static List<String> validateEmployees(List<EmployeeDto> employees) {
        List<String> errors = new ArrayList<>();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        for (int i = 0; i < employees.size(); i++) {
            EmployeeDto employee = employees.get(i);

            Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employee);

            if (!violations.isEmpty()) {
                StringBuilder errorBuilder = new StringBuilder();
                errorBuilder.append("Row ").append(i + 1).append(": ");
                for (ConstraintViolation<EmployeeDto> violation : violations) {
                    errorBuilder.append(violation.getMessage()).append("; ");
                }
                errors.add(errorBuilder.toString());
            }
        }

        return errors;
    }
}

