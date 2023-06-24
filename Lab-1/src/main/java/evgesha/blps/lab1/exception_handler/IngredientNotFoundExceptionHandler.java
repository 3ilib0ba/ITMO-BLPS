package evgesha.blps.lab1.exception_handler;

import evgesha.blps.lab1.dto.MessageDto;
import evgesha.blps.lab1.exception.IngredientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IngredientNotFoundExceptionHandler {

    @ExceptionHandler(IngredientNotFoundException.class)
    protected ResponseEntity<MessageDto> handleNotFoundIngredient() {
        return new ResponseEntity<>(new MessageDto("ingredient not found"), HttpStatus.NOT_FOUND);
    }
}
