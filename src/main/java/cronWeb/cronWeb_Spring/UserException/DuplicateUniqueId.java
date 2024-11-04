package cronWeb.cronWeb_Spring.UserException;

public class DuplicateUniqueId extends IllegalStateException {
    public DuplicateUniqueId(String message) {
        super(message);
    }
}
