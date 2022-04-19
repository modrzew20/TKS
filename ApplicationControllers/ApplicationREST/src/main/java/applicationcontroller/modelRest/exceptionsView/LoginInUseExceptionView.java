package applicationcontroller.modelRest.exceptionsView;

public class LoginInUseExceptionView extends Exception {
    public LoginInUseExceptionView(String errorMessage) {
        super(errorMessage);
    }
}
