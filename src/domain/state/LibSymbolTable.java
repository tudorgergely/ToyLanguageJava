package domain.state;

import exceptions.VariableNotDefinedException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author tudor.gergely on 1/17/2016
 */
public final class LibSymbolTable implements SymbolTable {
    private static final long serialVersionUID = -8672188077174414015L;
    private final Map<String, Integer> symbolTable;

    public LibSymbolTable() {
        this.symbolTable = new HashMap<>(10);
    }

    public LibSymbolTable(final SymbolTable libSymbolTable) {
        this.symbolTable = new HashMap<>(10);

        libSymbolTable.symbols().stream()
            .forEach(symbol -> {
                try {
                    set(symbol, libSymbolTable.get(symbol));
                } catch (final VariableNotDefinedException e) {
                    e.printStackTrace();
                }
            });
    }

    @Override
    public Integer get(final String name) throws VariableNotDefinedException {
        if (!symbolTable.containsKey(name)) {
            throw new VariableNotDefinedException("Variable " + name + " not found");
        }
        return symbolTable.get(name);
    }

    @Override
    public void set(final String name, final Integer value) {
        symbolTable.put(name, value);
    }

    @Override
    public Set<String> symbols() {
        return symbolTable.keySet();
    }

    @Override
    public String toString() {
        return "Symbol table: \n" + symbolTable.keySet().stream()
            .map(s -> s + "->" + symbolTable.get(s))
            .reduce((accumulator, s) -> accumulator + '\n' + s)
            .orElse("");
    }
}
