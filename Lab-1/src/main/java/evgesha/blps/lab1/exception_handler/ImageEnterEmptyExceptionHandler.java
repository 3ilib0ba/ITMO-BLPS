package evgesha.blps.lab1.exception_handler;

import evgesha.blps.lab1.dto.MessageDto;
import evgesha.blps.lab1.exception.ImageEnterEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ImageEnterEmptyExceptionHandler {
    @ExceptionHandler(ImageEnterEmptyException.class)
    protected ResponseEntity<MessageDto> handleNotFoundImage() {
        return new ResponseEntity<>(new MessageDto("image must be not empty"), HttpStatus.BAD_REQUEST);
    }
}
