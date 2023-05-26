package evgesha.blps.lab1.exception_handler;

import evgesha.blps.lab1.dto.MessageDto;
import evgesha.blps.lab1.exception.ImageDeleteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ImageDeleteExceptionHandler {

    @ExceptionHandler(ImageDeleteException.class)
    protected ResponseEntity<MessageDto> handleErrorWithDeletingImage() {
        return new ResponseEntity<>(new MessageDto("error with deleting an image"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
