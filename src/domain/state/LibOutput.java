package domain.state;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tudor.gergely on 1/17/2016
 */
public final class LibOutput implements Output {
    private static final long serialVersionUID = -3212306084671327439L;
    private final List<String> output;

    public LibOutput() {
        this.output = new ArrayList<>(10);
    }

    @Override
    public void add(final String s) {
        output.add(s);
    }

    @Override
    public String toString() {
        return "Output: \n" + output.stream()
            .reduce((accumulator, s) -> accumulator + '\n' + s)
            .orElse("");
    }
}
