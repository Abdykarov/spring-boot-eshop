package cz.abdykili.eshop.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ProductNotFoundException extends ItaRequestException {
    private final String message;
    private final HttpStatus httpStatus;

    public ProductNotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
