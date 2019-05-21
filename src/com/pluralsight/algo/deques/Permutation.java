package com.pluralsight.algo.deques;
import com.pluralsight.algo.algs4.StdIn;
import com.pluralsight.algo.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        //Deque<String> queue = new Deque<String>();
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            //queue.addLast(StdIn.readString());
            queue.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.iterator().next());
        }
    }
}
