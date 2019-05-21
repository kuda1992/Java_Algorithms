package com.pluralsight.algo.Priority;

public class MaxHeapPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N;

    public MaxHeapPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public MaxHeapPQ() {
        this(10);
    }


    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key max() {
        Key max = pq[1]; // Retrieve max key from top.
        return max;
    }

    public Key delMax() {
        Key max = pq[1]; // Retrieve max key from top.
        exch(pq, 1, N--); // Exchange with last item.
        pq[N + 1] = null; // Avoid loitering.
        sink(1); // Restore heap property.
        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(pq, k / 2, k);
            k = k / 2;
        }

    }

    private void sink(int k) {

        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(pq, k, j);
            k = j;
        }
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
