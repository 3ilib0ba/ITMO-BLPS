package evgesha.blps.lab1.exception_handler;

import evgesha.blps.lab1.dto.MessageDto;
import evgesha.blps.lab1.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotFoundExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<MessageDto> handleUserNotFoundException() {
        return new ResponseEntity<>(new MessageDto("user not found"), HttpStatus.OK);
    }

}
