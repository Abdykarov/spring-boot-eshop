package cz.abdykili.eshop.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ItaExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(ItaExceptionHandler.class);

    @ExceptionHandler(ItaRequestException.class)
    public ResponseEntity<Object> handleItaException(ItaRequestException e){
        logger.error(e.getMessage());
        return new ResponseEntity<>(
                new ItaResponseException(
                        e.getMessage(),
                        e.getHttpStatus(),
                        e.getClass().getName(),
                        ZonedDateTime.now(ZoneId.of("Europe/Prague"))
                ), e.getHttpStatus()
        );
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException e){
        logger.error(e.getMessage());
        return new ResponseEntity<>(
                new ItaResponseException(
                        e.getMessage(),
                        e.getHttpStatus(),
                        e.getClass().getName(),
                        ZonedDateTime.now(ZoneId.of("Europe/Prague"))
                ), e.getHttpStatus()
        );
    }
}
