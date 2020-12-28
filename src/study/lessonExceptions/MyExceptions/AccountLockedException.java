package study.lessonExceptions.MyExceptions;

public class AccountLockedException extends AccountExceptions {
    public AccountLockedException(String message) {
        super(message);
    }
}
