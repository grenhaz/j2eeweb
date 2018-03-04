package org.obarcia.demo.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.obarcia.demo.components.Utilities;
import org.obarcia.demo.models.user.RegisterForm;
import org.obarcia.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Heck
 */
public class EmailUniqueConstraintValidator implements ConstraintValidator<EmailUniqueConstraint, Object>
{
    @Autowired
    private UserService userService;

    @Override
    public void initialize(EmailUniqueConstraint c) {}
    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext cvc)
    {
        String email = (String)candidate;
        return (userService.getUserByEmail(email) == null);
    }
}