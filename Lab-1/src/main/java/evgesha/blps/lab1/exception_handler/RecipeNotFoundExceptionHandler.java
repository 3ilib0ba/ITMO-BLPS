package evgesha.blps.lab1.exception_handler;

import evgesha.blps.lab1.dto.MessageDto;
import evgesha.blps.lab1.exception.RecipeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RecipeNotFoundExceptionHandler {
    @ExceptionHandler(RecipeNotFoundException.class)
    protected ResponseEntity<MessageDto> handleRecipeNotFoundException() {
        return new ResponseEntity<>(new MessageDto("Recipes not found"), HttpStatus.OK);
    }
}
