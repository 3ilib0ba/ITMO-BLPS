package evgesha.blps.lab1.exception_handler;

import evgesha.blps.lab1.dto.MessageDto;
import evgesha.blps.lab1.exception.BadCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BadCredentialsExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<MessageDto> handleBadPassword() {
        return new ResponseEntity<>(new MessageDto("not correct password"), HttpStatus.BAD_REQUEST);
    }
}
