package domain.theADTs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gergely on 12/10/2015.
 */
public final class LibHeap implements MyHeap<Integer>, Serializable {
    private static final long serialVersionUID = 8L;
    private final List<Integer> heapList;

    public LibHeap() {
        heapList = new ArrayList<>();
    }

    @Override
    public Integer pushVal(Integer val) {
        heapList.add(val);
        return heapList.size() - 1;
    }

    @Override
    public Integer getVal(Integer pos) {
        return heapList.get(pos);
    }

    @Override
    public Integer putVal(Integer pos, Integer val) {
        if (heapList.size() < pos) {
            heapList.set(pos, val);
        }
        return pos;
    }

    @Override
    public String toString() {
        return heapList.stream()
            .map(i -> heapList.indexOf(i) + "->" + i + "\n")
            .reduce((s, s2) -> s + s2)
            .orElse("");
    }
}
