package com.pluralsight.algo;

import com.pluralsight.algo.elementary.MergeSort;
import com.pluralsight.algo.elementary.QuickSort;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {

        Integer[] numbers = {0, 5, 6, 8, 3, 1, 2};

        Arrays.sort(numbers);

        System.out.println(Arrays.toString(numbers));

    }
}
