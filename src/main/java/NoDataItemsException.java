public class NoDataItemsException extends Throwable {
    public NoDataItemsException() {
    }

    public NoDataItemsException(String message) {
        super(message);
    }

    public NoDataItemsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDataItemsException(Throwable cause) {
        super(cause);
    }

    public NoDataItemsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
