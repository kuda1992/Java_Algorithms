package com.pluralsight.algo.Simulation;

public class LinearProbingHash<Key, Value> {

  private int M = 30001;
  private Value[] values = (Value[]) new Object[M];
  private Key[] keys = (Key[]) new Object[M];

  private int hash(Key key) {
    return (key.hashCode() & 0x7fffffff) % M;
  }

  private void put(Key key, Value value) {
    int i;

    for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
      if (keys[i].equals(key)) break;
    }

    keys[i] = key;
    values[i] = value;
  }


  private Value get(Key key) {

    for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
      if (keys[i].equals(key)) return values[i];
    }
    return null;
  }

}
