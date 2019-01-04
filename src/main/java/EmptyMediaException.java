public class EmptyMediaException extends Throwable {
    public EmptyMediaException() {
    }

    public EmptyMediaException(String message) {
        super(message);
    }

    public EmptyMediaException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyMediaException(Throwable cause) {
        super(cause);
    }

    public EmptyMediaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
