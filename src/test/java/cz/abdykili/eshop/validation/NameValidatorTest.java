package cz.abdykili.eshop.validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.*;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NameValidatorTest {

    @Mock
    NameValidator nameValidator;

    @Mock
    ConstraintValidatorContext constraintValidatorContext;


    @BeforeEach
    public void setUp() {

        doCallRealMethod().when(nameValidator).initialize(any());
        when(nameValidator.isValid(any(), any())).thenCallRealMethod();

        NameValidatorTestClass testClass = new NameValidatorTestClass();

        nameValidator.initialize(testClass);

    }

    private class NameValidatorTestClass implements NameConstraint {

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
        assertTrue(nameValidator.isValid("GFgffdgdf", constraintValidatorContext));
    }
}