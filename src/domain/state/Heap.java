package domain.state;

import java.io.Serializable;

/**
 * @author tudor.gergely on 1/17/2016
 */
public interface Heap extends Serializable {
    Integer push(Integer val);

    Integer get(Integer pos);

    void put(Integer pos, Integer val);
}
