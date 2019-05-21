package com.pluralsight.algo.Priority;

import com.pluralsight.algo.algs4.In;
import com.pluralsight.algo.algs4.StdIn;

public class TestClient {

    public static void main(String[] args) {
        In in = new In(args[0]);
        final MaxHeapPQ<Transcation> pq = new MaxHeapPQ<>();

        while (in.hasNextLine()) {
            final String line = in.readLine();
            System.out.println("reading line " + line);
            final Transcation transcation = new Transcation(line);
            pq.insert(transcation);
            System.out.println("size of pq is " + pq.size());
            if(pq.size() > 10 - 1){
                System.out.println("the max item is " + pq.max());
                pq.delMax();
            }
        }
    }
}
