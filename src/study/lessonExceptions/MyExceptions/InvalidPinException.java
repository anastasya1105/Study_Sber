package study.lessonExceptions.MyExceptions;

public class InvalidPinException extends AccountExceptions {
    public InvalidPinException(String message) {
        super(message);
    }
}
