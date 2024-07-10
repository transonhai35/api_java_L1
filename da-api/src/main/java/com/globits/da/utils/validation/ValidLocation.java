package com.globits.da.utils.validation;

import com.globits.da.utils.validation.impl.LocationValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LocationValidatorImpl.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLocation {

    String message() default "Invalid location relationship";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

