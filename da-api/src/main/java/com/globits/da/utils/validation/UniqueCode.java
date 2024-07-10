package com.globits.da.utils.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.globits.da.utils.validation.impl.UniqueCodeValidatorImpl;
import org.springframework.context.annotation.Configuration;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Documented
@Constraint(validatedBy = UniqueCodeValidatorImpl.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCode {

    String message() default "Code must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


}
