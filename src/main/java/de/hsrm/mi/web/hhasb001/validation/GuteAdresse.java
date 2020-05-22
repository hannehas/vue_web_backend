package de.hsrm.mi.web.hhasb001.validation;

import javax.validation.Constraint;

@Constraint(validatedBy = GuteAdresseValidator.class)
public @interface GuteAdresse {
    String value();
    
}
