package cz.abdykili.eshop.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
public @interface NameConstraint {
    String message() default "The name doesnt start with an uppercase letter";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


