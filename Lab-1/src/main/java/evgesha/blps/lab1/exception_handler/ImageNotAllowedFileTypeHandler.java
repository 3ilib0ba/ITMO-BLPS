package evgesha.blps.lab1.exception_handler;

import evgesha.blps.lab1.dto.MessageDto;
import evgesha.blps.lab1.exception.ImageNotAllowedFileTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ImageNotAllowedFileTypeHandler {
    @ExceptionHandler(ImageNotAllowedFileTypeException.class)
    protected ResponseEntity<MessageDto> handleNotAllowedFiletypeException() {
        return new ResponseEntity<>(new MessageDto("not allowed file type"), HttpStatus.BAD_REQUEST);
    }
}
