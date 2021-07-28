package cz.abdykili.eshop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception ex, ServletWebRequest webRequest){
        log.error("An error ocurred while processing request " +
                webRequest.getRequest().getMethod() + " at " +
                webRequest.getRequest().getRequestURI(), ex);

        ItaResponseException itaResponseException = new ItaResponseException(
            ex.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR,
            ex.getClass().getName(),
            ZonedDateTime.now(ZoneId.of("Europe/Prague"))
        );

        return handleExceptionInternal(ex, itaResponseException,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }
}
