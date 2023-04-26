package evgesha.blps.lab1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "can't save an image")
public class ImageSavingException extends RuntimeException {

}
