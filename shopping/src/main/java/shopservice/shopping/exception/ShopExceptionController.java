package shopservice.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ShopExceptionController {

    @ExceptionHandler(ShopException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String shopNotFound(ShopException ex){
        return new ResponseEntity<>(("shop not found"),HttpStatus.NOT_FOUND).toString();
    }
}
