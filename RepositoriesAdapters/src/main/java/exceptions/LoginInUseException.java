package exceptions;

public class LoginInUseException extends Exception {
    public LoginInUseException(String errorMessage) {
        super(errorMessage);
    }
}
