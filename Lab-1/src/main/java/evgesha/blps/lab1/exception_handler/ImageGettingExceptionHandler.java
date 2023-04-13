package evgesha.blps.lab1.exception_handler;

import evgesha.blps.lab1.dto.MessageDto;
import evgesha.blps.lab1.exception.ImageGettingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ImageGettingExceptionHandler {
    @ExceptionHandler(ImageGettingException.class)
    protected ResponseEntity<MessageDto> handleThereIsNoSuchUserException() {
        return new ResponseEntity<>(new MessageDto("can't get an image"), HttpStatus.NOT_FOUND);
    }
}
