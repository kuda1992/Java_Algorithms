package com.pluralsight.algo.binary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BSTTest {

    private BST<String, Integer> letters;

    @BeforeEach
    void setUp() {
        letters = new BST<>();
        letters.put("S", 0);
        letters.put("E", 1);
        letters.put("A", 2);
        letters.put("R", 3);
        letters.put("C", 4);
        letters.put("H", 5);
        letters.put("E", 6);
        letters.put("X", 7);
        letters.put("A", 8);
        letters.put("M", 9);
        letters.put("P", 10);
        letters.put("L", 11);
        letters.put("E", 12);
    }


    @Test
    public void shouldHaveTheCorrectSizeOfTheBinarySearch() {

        assertEquals(10, letters.size());
    }


    @Test
    public void shouldGetTheMinimumKeyOfTheBinarySearchTree() {

        assertEquals("A", letters.min());
    }

    @Test
    public void shouldGetTheMaximumKeyOfTheBinarySearchTree() {

        assertEquals("X", letters.max());
    }

    @Test
    public void shouldDeleteTheMinimumKeyOfTheBinarySearchTree() {

        assertEquals("A", letters.min());
        letters.deleteMin();
        assertEquals("C", letters.min());

    }


}
