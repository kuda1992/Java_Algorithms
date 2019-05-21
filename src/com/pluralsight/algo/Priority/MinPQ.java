package com.pluralsight.algo.Priority;

public class MinPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N;

    public MinPQ() {
        this(1000);
    }

    public MinPQ(int max) {
        pq = (Key[]) new Comparable[max];
        N = 0;
    }

    public MinPQ(Key[] a) {
    }

    public void insert(Key v) {
        pq[N++] = v;
    }

    public Key min() {
        int min = 0;
        for (int i = 0; i < N; i++)
            if (less(i, min)) min = i;
        return pq[min];
    }

    public Key delMin() {
        int min = 0;
        for (int i = 0; i < N; i++)
            if (less(i, min)) min = i;
        exch(pq, min, N - 1);
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