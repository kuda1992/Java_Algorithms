package com.pluralsight.algo.deques;

import com.pluralsight.algo.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int n;

    public RandomizedQueue() {
        a = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        autoEnlarge();
        a[n++] = item;
    }

    private void autoEnlarge() {
        if (n == a.length) resize(2 * a.length);
    }

    public Item dequeue() {
        assertNotEmpty();
        int index = randomIndex();
        Item item = a[index];
        a[index] = a[n - 1];
        a[n - 1] = null;
        n--;
        autoShrink();
        return item;
    }

    private void assertNotEmpty() {
        if (isEmpty()) throw new NoSuchElementException();
    }

    private void autoShrink() {
        if (n > 0 && n == a.length / 4) resize(a.length / 2);
    }

    private int randomIndex() {
        return StdRandom.uniform(0, n);
    }

    public Item sample() {
        assertNotEmpty();
        return a[randomIndex()];
    }

    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item> {
        private Item[] r;
        private int i;

        public RandomArrayIterator() {
            copyQueue();
            StdRandom.shuffle(r);
        }

        private void copyQueue() {
            r = (Item[]) new Object[n];
            for (int j = 0; j < n; j++) {
                r[j] = a[j];
            }
        }

        public boolean hasNext() {
            return i < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return r[i++];
        }
    }
}
