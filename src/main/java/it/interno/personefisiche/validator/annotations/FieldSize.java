package it.interno.personefisiche.validator.annotations;

import it.interno.personefisiche.validator.FieldSizeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = FieldSizeValidator.class)
@Target({FIELD})
@Repeatable(FieldSize.List.class)
@Retention(RUNTIME)
@Documented
public @interface FieldSize {

    String message() default "Il campo ha troppi caratteri.";

    int max() default Integer.MAX_VALUE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default  {};

    @Target({FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        FieldSize[] value();
    }
}
