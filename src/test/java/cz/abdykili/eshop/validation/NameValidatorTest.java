package cz.abdykili.eshop.validation;

import org.assertj.core.api.WithAssertions;
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
class NameValidatorTest implements WithAssertions {

    private final NameValidator validator = new NameValidator();

    @Test
    void nameValidator_TestIsValid() {
        //arrange
        final String s = "Validname";
        //act
        final boolean result = validator.isValid(s, null);
        //assert
        assertThat(result).isTrue();
    }

    @Test
    void nameValidator_TestIsNotValid() {
        //arrange
        final String s = "invalidname";
        //act
        final boolean result = validator.isValid(s, null);
        //assert
        assertThat(result).isFalse();
    }
}