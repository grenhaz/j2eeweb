package org.obarcia.demo.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Constraint para el uso único de un nickname.
 * 
 * @author obarcia
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ArrayNotEmptyConstraintValidator.class)
public @interface ArrayNotEmptyConstraint
{
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}