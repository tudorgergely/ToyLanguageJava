package exceptions;

public final class EmptyContainerException extends Exception {
    private static final long serialVersionUID = -4256708838205252591L;

    public EmptyContainerException(final String msg){
        super(msg);
    }
}
