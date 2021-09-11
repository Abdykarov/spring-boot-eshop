package cz.abdykili.eshop.validation;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UrlValidatorTest implements WithAssertions {
    private final UrlValidator validator = new UrlValidator();

    @Test
    void urlValidator_TestIsValid() {
        //arrange
        final String s = "image.svg";
        //act
        final boolean result = validator.isValid(s, null);
        //assert
        assertThat(result).isTrue();
    }

    @Test
    void urlValidator_TestIsInvalid() {
        //arrange
        final String s = "image";
        //act
        final boolean result = validator.isValid(s, null);
        //assert
        assertThat(result).isFalse();
    }
}