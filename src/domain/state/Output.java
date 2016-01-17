package domain.state;

import java.io.Serializable;

/**
 * @author tudor.gergely on 1/17/2016
 */
public interface Output extends Serializable {
    void add(String s);
}
