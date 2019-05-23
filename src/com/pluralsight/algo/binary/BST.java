package com.pluralsight.algo.binary;

import java.text.Normalizer;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root = null;

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
}
