package com.pluralsight.algo.Symbol;

import java.util.HashMap;

public class ST<Key extends Comparable<Key>, Value> {

    HashMap<Key, Value> ht;
    public ST() {
        ht = new HashMap<>();
    }

    public void put(Key key, Value value) {
        ht.put(key, value);
    }

    public Value get(Key key) {
        return ht.get(key);
    }

    public void delete(Key key) {
        ht.remove(key);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return ht.isEmpty();
    }

    public int size() {
        return ht.size();
    }

    /*public Iterable<Key> keys() {
        //return ht.
    }*/

}
