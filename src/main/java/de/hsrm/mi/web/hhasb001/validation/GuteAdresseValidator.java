package de.hsrm.mi.web.hhasb001.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class GuteAdresseValidator implements ConstraintValidator<GuteAdresse, String> {
    protected String adresse;
    @Override
    public void initialize(GuteAdresse constraintAnnotation) {
        // TODO Auto-generated method stub
        this.adresse = constraintAnnotation.value();
       //ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        if(value.matches("[\\p{L}]+[\\s]([\\p{L}]+\\s)*([0-9]+\\s)?[0-9]+,[\\s]\\d{5}[\\s]*[\\p{L}]+([\\p{L}]+\\s)*[\\p{L}]+"))
            return true;
        return false;
    }
    
}