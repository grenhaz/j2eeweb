package org.obarcia.demo.constraints;

import java.util.List;
import java.util.logging.Logger;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Constraint Validator para el uso único de un nickname.
 * 
 * @author obarcia
 */
public class ArrayNotEmptyConstraintValidator implements ConstraintValidator<ArrayNotEmptyConstraint, Object>
{
    @Override
    public void initialize(ArrayNotEmptyConstraint c) {}
    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext cvc)
    {
        if (candidate != null) {
            if (candidate instanceof List) {
                return (((List)candidate).size() > 0);
            }
        }
        
        return false;
    }
}