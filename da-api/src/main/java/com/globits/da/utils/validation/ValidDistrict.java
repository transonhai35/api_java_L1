package com.globits.da.utils.validation;

import com.globits.da.utils.validation.impl.DistrictValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DistrictValidatorImpl.class)
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDistrict {

    String message() default "Invalid District";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
