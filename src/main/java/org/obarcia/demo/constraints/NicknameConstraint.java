package org.obarcia.demo.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Heck
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = NicknameConstraintValidator.class)
public @interface NicknameConstraint {
    String message();
    String field();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
