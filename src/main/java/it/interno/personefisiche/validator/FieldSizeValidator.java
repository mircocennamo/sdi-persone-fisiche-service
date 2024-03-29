package it.interno.personefisiche.validator;

import it.interno.personefisiche.client.TabelleComuniClient;
import it.interno.personefisiche.validator.annotations.FieldSize;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;

public class FieldSizeValidator implements ConstraintValidator<FieldSize, String> {

    @Autowired
    private TabelleComuniClient tabelleComuniClient;

    private int max;
    private String message;

    @Override
    public void initialize(FieldSize constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (!Objects.nonNull(value) || value.isEmpty())  return true;

        return tabelleComuniClient.traslittera(value).getBody().getBody().length() <= max;
    }
}
