package domain.state;

import exceptions.VariableNotDefinedException;
import java.io.Serializable;
import java.util.Set;

/**
 * @author tudor.gergely on 1/17/2016
 */
public interface SymbolTable extends Serializable {
    Integer get(String name) throws VariableNotDefinedException;

    void set(String name, Integer value);

    Set<String> symbols();
}
