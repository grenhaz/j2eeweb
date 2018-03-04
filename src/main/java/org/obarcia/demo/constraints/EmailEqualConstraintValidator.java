package org.obarcia.demo.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.obarcia.demo.components.Utilities;
import org.obarcia.demo.models.user.RegisterForm;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author Heck
 */
public class EmailEqualConstraintValidator implements ConstraintValidator<EmailEqualConstraint, Object>
{
    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(EmailEqualConstraint c)
    {
        firstFieldName = c.first();
        secondFieldName = c.second();
        message = c.message();
    }
    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext cvc)
    {
        try {
            final Object firstObj = Utilities.getPropertyValue(candidate, firstFieldName);
            final Object secondObj = Utilities.getPropertyValue(candidate, secondFieldName);
            if (firstObj != null && secondObj != null && firstObj.equals(secondObj)) {
                return true;
            } else {
                cvc.disableDefaultConstraintViolation();
                cvc.buildConstraintViolationWithTemplate(message).addNode(firstFieldName).addConstraintViolation();

                return false;
            }
        } catch (Exception e) {}
        
        return true;
    }
}