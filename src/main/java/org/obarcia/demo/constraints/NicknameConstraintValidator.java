package org.obarcia.demo.constraints;

import java.lang.reflect.InvocationTargetException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.obarcia.demo.components.Utilities;
import org.obarcia.demo.models.user.User;
import org.obarcia.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Constraint Validator para el uso único de un nickname.
 * 
 * @author obarcia
 */
public class NicknameConstraintValidator implements ConstraintValidator<NicknameConstraint, Object>
{
    /**
     * Servicio de usuarios.
     */
    @Autowired
    private UserService userService;
    
    /**
     * Nombre del campo donde obtener el nickname.
     * El erro se incluirá en este campo.
     */
    private String fieldName;
    /**
     * Mensaje de error.
     */
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
                cvc.buildConstraintViolationWithTemplate(message)
                        // TODO: Deprecated
                        .addNode(fieldName)
                        .addConstraintViolation();

                return false;
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {}
        
        return true;
    }
}