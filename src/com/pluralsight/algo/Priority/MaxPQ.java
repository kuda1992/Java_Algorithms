package com.pluralsight.algo.Priority;

public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N;

    public MaxPQ(int max) {
        pq = (Key[]) new Comparable[max];
    }

    public MaxPQ(Key[] a) {
    }

    public MaxPQ() {
        this(5);
    }

    public void insert(Key v) {
        pq[N++] = v;
    }

    public Key max() {
        int max = 0;
        for(int i = 0; i < N; i++)
            if(less(max, i)) max = i;
        return pq[max];
    }

    public Key delMax() {
        int max = 0;
        for(int i = 0; i < N; i++)
            if(less(max, i)) max = i;
        exch(pq, max, N-1);
        return pq[--N];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
