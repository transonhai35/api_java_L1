package com.globits.da.utils.validation;


import com.globits.da.utils.validation.impl.ProvinceValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ProvinceValidatorImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidProvince {

    String message() default "Invalid Province";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
