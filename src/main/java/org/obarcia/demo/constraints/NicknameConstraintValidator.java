package org.obarcia.demo.constraints;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.obarcia.demo.components.Utilities;
import org.obarcia.demo.dao.ArticleDaoImpl;
import org.obarcia.demo.models.user.User;
import org.obarcia.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Heck
 */
public class NicknameConstraintValidator implements ConstraintValidator<NicknameConstraint, Object>
{
    @Autowired
    private UserService userService;
    
    private String fieldName;
    private String message;
    
    @Override
    public void initialize(NicknameConstraint c)
    {
        fieldName = c.field();
        message = c.message();
    }
    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext cvc)
    {
        try {
            final Object id = Utilities.getPropertyValue(candidate, "id");
            final Object nickname = Utilities.getPropertyValue(candidate, "nickname");

            User user = userService.getUserByNickname((String)nickname);
            if  (user == null || (id != null && user.getId().equals(id))) {
                return true;
            } else {
                cvc.disableDefaultConstraintViolation();
                cvc.buildConstraintViolationWithTemplate(message).addNode(fieldName).addConstraintViolation();

                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return true;
    }
}