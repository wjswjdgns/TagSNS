package cronWeb.cronWeb_Spring.UserException;

public class LoginFailure extends RuntimeException{
    public LoginFailure(String message) {
        super(message);
    }
}
