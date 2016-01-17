package domain.state;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gergely on 12/10/2015.
 */
public final class LibHeap implements Heap {
    private static final long serialVersionUID = 5140698441128067660L;
    private final List<Integer> heapList;

    public LibHeap() {
        heapList = new ArrayList<>(10);
    }

    @Override
    public Integer push(final Integer val) {
        heapList.add(val);
        return heapList.size() - 1;
    }

    @Override
    public Integer get(final Integer pos) {
        return heapList.get(pos);
    }

    @Override
    public void put(final Integer pos, final Integer val) {
        heapList.set(pos, val);
    }

    @Override
    public String toString() {
        return "Heap: \n" + heapList.stream()
            .map(i -> heapList.indexOf(i) + "->" + i)
            .reduce((accumulator, s) -> accumulator + '\n' + s)
            .orElse("");
    }
}
