package exception;

public class ContentNotFoundException extends Exception {

    public ContentNotFoundException() {
        super("Request Content is not found.");
    }
}
