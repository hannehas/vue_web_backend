package de.hsrm.mi.web.bratenbank.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GuteAdresseValidator.class)
public @interface GuteAdresse {
    String message() default "Eingabe ist keine Adresse.";

    Class<? extends Payload>[] payload() default{};
    Class<?>[] groups() default{};
    
}
