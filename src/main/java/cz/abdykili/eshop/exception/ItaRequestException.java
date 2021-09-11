package cz.abdykili.eshop.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class ItaRequestException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;
}
