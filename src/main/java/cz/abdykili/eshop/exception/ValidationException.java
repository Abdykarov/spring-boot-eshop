package cz.abdykili.eshop.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ValidationException extends ItaRequestException{
    private final String message;
    private final HttpStatus httpStatus;

    public ValidationException(String message, HttpStatus httpStatus){
        super(message, httpStatus);
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
