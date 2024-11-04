package cronWeb.cronWeb_Spring.UserException;
public class DuplicateName extends IllegalStateException {
    public DuplicateName(String message) {
        super(message);
    }
}
