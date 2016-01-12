package domain.theADTs;

import exceptions.VariableNotDefined;

import java.io.Serializable;
import java.util.*;

public final class LibDictionary implements MyDictionary<String, Integer>, Serializable {
    private static final long serialVersionUID = 1L;
    private final Map<String, Integer> dict;

    public LibDictionary() {
        dict = new Hashtable<>();
    }

    public LibDictionary(LibDictionary libDictionary) {
        dict = new Hashtable<>();
        libDictionary.getAllKeys().forEach(key -> {
            try {
                this.addModifyElem(key, libDictionary.getElem(key));
            } catch (VariableNotDefined variableNotDefined) {
                variableNotDefined.printStackTrace();
            }
        });
    }

    @Override
    public Integer getElem(String key) throws VariableNotDefined {
        if (dict.get(key) == null) {
            throw new VariableNotDefined("No such variable in the dictionary \n");
        }
        return dict.get(key);
    }

    @Override
    public void addModifyElem(String k, Integer v) {
        if (dict.containsKey(k)) {
            dict.replace(k, v);
            return;
        }
        dict.put(k, v);
    }

    @Override
    public Set<String> getAllKeys() {
        return dict.keySet();
    }

    public String toString() {
        return dict.keySet().stream()
            .map(s1 -> s1 + "->" + dict.get(s1))
            .reduce((s, s2) -> s + "\n" + s2)
            .orElse("");
    }

}
