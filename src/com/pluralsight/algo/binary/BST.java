package com.pluralsight.algo.binary;

import com.pluralsight.algo.algs4.Out;
import com.pluralsight.algo.algs4.StdOut;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

public class BST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private Node root = null;


    @NotNull
    @Override
    public Iterator<Key> iterator() {

        return new Iterator<Key>() {
            Node current = root;
            private Stack<Node> nodes = new Stack<>();

            @Override
            public boolean hasNext() {

                if (current != null) {
                    nodes.push(current);
                    current = current.left;
                }
                return !nodes.isEmpty();
            }

            @Override
            public Key next() {
                Node node = nodes.pop();
                if (node.right != null) {
                    node.right = node.right;
                    while (node != null) {
                        nodes.push(node);
                        node = node.left;
                    }
                }
                return node.key;
            }
        };
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        final Queue<Key> queue = new ArrayDeque<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        final int loCompare = lo.compareTo(x.key);
        final int hiCompare = hi.compareTo(x.key);

        if (loCompare < 0) keys(x.left, queue, lo, hi);
        if (loCompare <= 0 && hiCompare >= 0) queue.add(x.key);
        if (hiCompare > 0) keys(x.right, queue, lo, hi);
    }

    private class Node {

        private Key key;
        private Value value;
        private Node left, right;
        private int N;

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }

        public Key getKey() {
            return key;
        }

        public Value getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getN() {
            return N;
        }

        public void setN(int n) {
            N = n;
        }
    }

    public int size() {
        return size(root);
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public void put(Key key, Value value) {

        root = put(root, key, value);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    private Value get(Node x, Key key) {

        if (x == null) return null;

        final int compare = key.compareTo(x.key);

        if (compare < 0) return get(x.left, key);
        else if (compare > 0) return get(x.right, key);
        else return x.value;

    }

    private Node put(Node x, Key key, Value value) {

        if (x == null) return new Node(key, value, 1);

        final int compare = key.compareTo(x.key);

        if (compare < 0) x.left = put(x.left, key, value);
        else if (compare > 0) x.right = put(x.right, key, value);
        else x.value = value;

        x.N = size(x.left) + size(x.right) + 1;

        return x;
    }


    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public Key min() {
        return min(root).getKey();
    }

    public Key max() {
        return max(root).getKey();
    }

    public Key floor(Key key) {
        final Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    public Key ceiling(Key key) {
        final Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }


    public void delete(Key key) {
        root = delete(root, key);
    }

    private void print(Node x) {
        if (x == null) return;
        print(x.left);
        StdOut.println(x.key);
        print(x.right);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        final int compare = key.compareTo(x.key);

        if (compare == 0) return x;
        if (compare < 0) return floor(x.left, x.key);
        Node t = floor(x.right, x.key);
        if (t != null) return t;
        return x;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        final int compare = key.compareTo(x.key);

        if (compare == 0) return x;
        if (compare < 0) return floor(x.right, x.key);
        Node t = ceiling(x.left, x.key);
        if (t != null) return t;
        return x;
    }


}
