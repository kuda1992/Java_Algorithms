package com.pluralsight.algo.sorting;

import java.util.Arrays;

public class ShellSort {

  public static void sort(Comparable[] a) {

    System.out.println("Array starts as " + Arrays.toString(a));
    int[] gaps = {701, 301, 132, 57, 23, 10, 4, 1};
    final int length = a.length;

    for (final int gap : gaps) {
      System.out.println("The current gap being processesed " + gap);
      for (int i = gap; i < length; i++) {
        final Comparable temp = a[i];
        System.out.println("Processing the value of " + temp);
        int j;
        for (j = i; j >= gap && less(a[j - gap], temp); j -= gap) {
          a[j] = a[j - gap];
        }
        a[j] = temp;
      }

    }
    System.out.println("Array ends as " + Arrays.toString(a));
  }


  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  public static void exch(Comparable[] a, int i, int j) {
    Comparable temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}
