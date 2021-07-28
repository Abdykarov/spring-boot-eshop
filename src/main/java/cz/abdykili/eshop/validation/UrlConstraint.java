package cz.abdykili.eshop.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = UrlValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface UrlConstraint {
    String message() default "Url doesnt contain any of image extensions";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
