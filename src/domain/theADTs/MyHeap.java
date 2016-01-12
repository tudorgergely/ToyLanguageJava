package domain.theADTs;

/**
 * @author Gergely on 12/10/2015.
 */
public interface MyHeap<T> {
    // push value to heap
    // return the address
    Integer pushVal(T val);

    // get val from heap by address
    T getVal(Integer pos);

    Integer putVal(Integer pos, T val);
}
