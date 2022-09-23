package ecommerce.eco.exception;

public class InvalidCredentialsException extends  RuntimeException {

    public InvalidCredentialsException(String message){
        super(message);
    }
}
