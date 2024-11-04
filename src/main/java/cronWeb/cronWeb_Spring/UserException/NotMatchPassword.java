package cronWeb.cronWeb_Spring.UserException;

public class NotMatchPassword extends IllegalStateException {
    public NotMatchPassword(String message) {
        super(message);
    }

}
