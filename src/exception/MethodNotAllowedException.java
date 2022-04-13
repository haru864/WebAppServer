package exception;

public class MethodNotAllowedException extends Exception {

    public MethodNotAllowedException() {
        super("Request Method is not allowed.");
    }
}
