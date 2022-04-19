package exception;

public class NotFoundException extends Exception {

    public NotFoundException() {
        super("Request Content is not found.");
    }
}
