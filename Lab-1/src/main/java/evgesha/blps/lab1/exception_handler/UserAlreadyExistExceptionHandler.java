package evgesha.blps.lab1.exception_handler;

import evgesha.blps.lab1.dto.MessageDto;
import evgesha.blps.lab1.exception.ImageTooBigException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserAlreadyExistExceptionHandler {
    @ExceptionHandler(ImageTooBigException.class)
    protected ResponseEntity<MessageDto> userAlreadyExist() {
        return new ResponseEntity<>(new MessageDto("user already exist"), HttpStatus.BAD_REQUEST);
    }
}
