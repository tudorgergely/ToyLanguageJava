package domain.theADTs;

import exceptions.VariableNotDefined;

import java.util.Set;

public interface MyDictionary<K,V> {
    //Return the value from the given key
    V getElem(K key) throws VariableNotDefined;

    //Put the value to  the key, if the key not exist, than this key will be created
    void addModifyElem(K key, V value);

    Set<K> getAllKeys();
}
