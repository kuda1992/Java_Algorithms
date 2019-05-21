package com.pluralsight.algo;

import com.pluralsight.algo.PatternRecognition.Point;

import java.util.Iterator;

public class BST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private class Node {

        private Key key;
        private Value value;
        private Node left;
        private Node right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

    }

    private Node root;

    public BST() {
        root = null;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value);
        int comp = key.compareTo(x.key);

        if (comp < 0) x.left = put(x.left, key, value);
        else if (comp > 0) x.right = put(x.right, key, value);
        else x.value = value;
        return x;
    }


    public void put(Key key, Value value) {
        root = put(root, key, value);
    }


    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    public void delete(Key key) {

    }


    @Override
    public Iterator<Key> iterator() {
        return null;
    }

}
