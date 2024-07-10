package com.globits.da.utils.validation;


import com.globits.da.utils.validation.impl.CommuneValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CommuneValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCommune {

    String message() default "Invalid location relationship";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
