package com.pluralsight.algo.elementary;

import com.pluralsight.algo.algs4.StdRandom;

public class QuickSort {

    public static void sort (Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length -1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if(hi <= lo) return;;

        int j = partition(a, lo, hi);
        sort(a, lo, j -1);
        sort(a, j + 1, hi);

    }

    private static int partition(Comparable[] a, int lo, int hi) {

        int i = lo;
        int j = hi + 1;

        while (true) {
            while (less(a[++i], a[lo])) {
                if (i == hi) break; // find the item on the left to swap
            }
            while (less(a[lo], a[--j])) {
                if (j == lo) break; // find the item on the right to swap
            }
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
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
