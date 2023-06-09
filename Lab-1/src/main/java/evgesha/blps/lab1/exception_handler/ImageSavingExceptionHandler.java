package evgesha.blps.lab1.exception_handler;

import evgesha.blps.lab1.dto.MessageDto;
import evgesha.blps.lab1.exception.ImageSavingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ImageSavingExceptionHandler {
    @ExceptionHandler(ImageSavingException.class)
    protected ResponseEntity<MessageDto> handleThereIsNoSuchUserException() {
        return new ResponseEntity<>(new MessageDto("can't save an image"), HttpStatus.BAD_REQUEST);
    }
}
