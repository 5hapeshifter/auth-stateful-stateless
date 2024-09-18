package br.com.microservice.statefull_auth_api.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException e) {
        var details = new ExceptionDetails(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException e) {
        var details = new ExceptionDetails(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return new ResponseEntity<>(details, HttpStatus.UNAUTHORIZED);
    }
}
