package exceptions;

public final class InvalidOptionException extends Exception {
    private static final long serialVersionUID = 3467647053644811025L;

    public InvalidOptionException(final String msg) {
        super(msg);
    }
}
