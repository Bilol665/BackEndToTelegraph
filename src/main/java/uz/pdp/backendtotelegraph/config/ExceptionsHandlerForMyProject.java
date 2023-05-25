package uz.pdp.backendtotelegraph.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.pdp.backendtotelegraph.exceptions.UserCreationException;

@ControllerAdvice
public class ExceptionsHandlerForMyProject {
    @ExceptionHandler(value = {UserCreationException.class})
    public ResponseEntity<Object> userCreationException(UserCreationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
