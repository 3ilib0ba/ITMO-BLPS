package evgesha.blps.lab1.exception_handler;


import evgesha.blps.lab1.dto.MessageDto;
import evgesha.blps.lab1.exception.MeasureNotCorrectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MeasureNotCorrectExceptionHandler {
    @ExceptionHandler(MeasureNotCorrectException.class)
    protected ResponseEntity<MessageDto> handleThereIsNoMeasureException() {
        return new ResponseEntity<>(new MessageDto("entered incorrect measure"), HttpStatus.BAD_REQUEST);
    }
}
