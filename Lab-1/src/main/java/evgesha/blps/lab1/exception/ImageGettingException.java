package evgesha.blps.lab1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "can't get an image")
public class ImageGettingException extends RuntimeException{

}
