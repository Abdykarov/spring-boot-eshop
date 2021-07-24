package cz.abdykili.eshop.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
@Data
public class ItaResponseException {
    private final String message;
    private final HttpStatus httpStatus;
    private final String throwableException;
    private final ZonedDateTime zonedDateTime;
}
