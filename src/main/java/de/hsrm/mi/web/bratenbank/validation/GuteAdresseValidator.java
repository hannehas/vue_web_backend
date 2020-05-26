package de.hsrm.mi.web.bratenbank.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GuteAdresseValidator implements ConstraintValidator<GuteAdresse, String> {
    @Override
    public void initialize(GuteAdresse constraintAnnotation) {
        // TODO Auto-generated method stub
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        if(value.matches("[\\p{L}]+[\\s]([\\p{L}]+[\\s])*([0-9]+[\\s])?[0-9]+,[\\s]\\d{5}[\\s]*[\\p{L}]+([\\p{L}]+\\s)*[\\p{L}]+"))
            return true;
        return false;
    }
    
}