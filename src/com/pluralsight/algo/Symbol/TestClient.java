package com.pluralsight.algo.Symbol;

import com.pluralsight.algo.algs4.StdIn;
import com.pluralsight.algo.algs4.StdOut;

public class TestClient {

    public static void main(String[] args) {

        int minLen = Integer.parseInt(args[0]);

        final ST<String, Integer> st = new ST<>();

        for (int i = 0; !StdIn.isEmpty(); i++) {
            final String key = StdIn.readString();
            st.put(key, i);
        }
    }


}
