package gr.siberian.ecommerce.handler;

import gr.siberian.ecommerce.exceptions.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final View error;

    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    /**
     * Custom exception
     * @param e
     * @return a message NOT_FOUND
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException e) {
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND).
                body(e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e){

        var errors = new HashMap<String , String>();
        e.getBindingResult().getAllErrors()
                .forEach(error -> {
        var fieldName = ((FieldError)error).getField();
        var errorMessage = ((FieldError) error).getDefaultMessage();
        errors.put(fieldName , errorMessage);
                });
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(new ErrorResponse(errors));
    }
}
