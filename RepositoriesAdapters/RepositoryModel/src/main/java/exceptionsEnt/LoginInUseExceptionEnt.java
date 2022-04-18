package exceptionsEnt;

public class LoginInUseExceptionEnt extends Exception {
    public LoginInUseExceptionEnt(String errorMessage) {
        super(errorMessage);
    }
}
