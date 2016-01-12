package domain.theADTs;

import java.io.Serializable;
import java.util.*;

public final class LibList implements MyList<String>, Serializable {
    private static final long serialVersionUID = 5L;
    private final List<String> array;

    public LibList() {
        array = new java.util.ArrayList<>();
    }

    @Override
    public void addList(String s){
        array.add(s);
    }

    @Override
    public String toString(){
        return array.stream()
            .reduce((s, s2) -> s + "\n" + s2)
            .orElse("");
    }


}
