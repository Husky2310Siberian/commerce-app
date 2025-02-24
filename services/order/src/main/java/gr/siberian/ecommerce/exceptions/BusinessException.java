package gr.siberian.ecommerce.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * It is used to represent business logic errors in the application.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException{
    private final String message;
}
