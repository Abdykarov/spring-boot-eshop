package cz.abdykili.eshop.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UrlValidator implements ConstraintValidator<UrlConstraint, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.contains(".png") || s.contains(".jpg") || s.contains(".gif") || s.contains(".svg")) {
            return true;
        }
        return false;
    }
}
