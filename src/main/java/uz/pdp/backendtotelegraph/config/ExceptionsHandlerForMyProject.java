package uz.pdp.backendtotelegraph.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.pdp.backendtotelegraph.exceptions.*;

@ControllerAdvice
public class ExceptionsHandlerForMyProject {
    @ExceptionHandler(value = {UserCreationException.class})
    public ResponseEntity<Object> userCreationException(UserCreationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<Object> authException(AuthenticationException e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {TelegraphNotCreatedException.class})
    public ResponseEntity<Object> telegraphNotCreatedEx(TelegraphNotCreatedException e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {TelegraphInvalidException.class})
    public ResponseEntity<Object> telegraphInvalidEx(TelegraphInvalidException e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(value = {DataNotException.class})
    public ResponseEntity<Object> dataNotFound(DataNotException e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {PageNotFoundException.class})
    public ResponseEntity<Object> pageNotFoundEx(PageNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }
}
