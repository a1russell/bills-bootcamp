package unitconversion;

public class InvalidConversionException extends Exception {
    public InvalidConversionException() {}

    public InvalidConversionException(String message) {
        super(message);
    }

    public InvalidConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidConversionException(Throwable cause) {
        super(cause);
    }
}
