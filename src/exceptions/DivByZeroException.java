package exceptions;

public final class DivByZeroException extends Exception {
    private static final long serialVersionUID = 3315555131457149434L;

    public DivByZeroException(final String msg) {
        super(msg);
    }
}
