package exceptions;

public final class VariableNotDefinedException extends Exception {
    private static final long serialVersionUID = -1574899569762291534L;

    public VariableNotDefinedException(final String msg) {
        super(msg);
    }
}
