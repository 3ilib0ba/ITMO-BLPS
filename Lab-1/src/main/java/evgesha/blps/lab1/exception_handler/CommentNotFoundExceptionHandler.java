package evgesha.blps.lab1.exception_handler;

import evgesha.blps.lab1.dto.MessageDto;
import evgesha.blps.lab1.exception.CommentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommentNotFoundExceptionHandler {
    @ExceptionHandler(CommentNotFoundException.class)
    protected ResponseEntity<MessageDto> handleCommentNotFound() {
        return new ResponseEntity<>(new MessageDto("comment not found"), HttpStatus.BAD_REQUEST);
    }
}
