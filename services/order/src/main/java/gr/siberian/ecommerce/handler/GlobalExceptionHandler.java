package gr.siberian.ecommerce.handler;

import gr.siberian.ecommerce.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Custom exception
     * @return a message NOT_FOUND
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handle(BusinessException e) {
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND).
                body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e){

        var errors = new HashMap<String , String>();
        e.getBindingResult().getAllErrors()
                .forEach(error -> {
        var fieldName = ((FieldError)error).getField();
        var errorMessage = error.getDefaultMessage();
        errors.put(fieldName , errorMessage);
                });
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(new ErrorResponse(errors));
    }
}
