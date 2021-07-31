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
class UrlValidatorTest {
    @Mock
    UrlValidator urlValidator;

    @Mock
    ConstraintValidatorContext constraintValidatorContext;


    @BeforeEach
    public void setUp() {

        doCallRealMethod().when(urlValidator).initialize(any());
        when(urlValidator.isValid(any(), any())).thenCallRealMethod();

        UrlValidatorTestClass testClass = new UrlValidatorTestClass();

        urlValidator.initialize(testClass);

    }

    private class UrlValidatorTestClass implements UrlConstraint {

        @Override
        public String message() {
            return "Test Message";
        }

        @Override
        public Class<?>[] groups() {
            return new Class[]{};
        }

        @Override
        public Class<? extends Payload>[] payload() {
            return new Class[]{};
        }


        @Override
        public Class<? extends Annotation> annotationType() {
            return NameConstraint.class;
        }
    }
    @Test
    void testIsValidWithValidValues() {
        assertTrue(urlValidator.isValid("image.jpg", constraintValidatorContext));
    }
}