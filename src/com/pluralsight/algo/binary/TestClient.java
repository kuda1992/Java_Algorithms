package com.pluralsight.algo.binary;

import java.util.Iterator;

public class TestClient {

    public static void main(String[] args) {

        final BST<String, String> dictionary = new BST<>();

        final Iterator<String> iterator = dictionary.iterator();

        dictionary.put("phone", "something used to make calls with");
        dictionary.put("chargers", "something used to change the phone");

        System.out.println("The size of the binary tree is " + dictionary.size());

        System.out.println(dictionary.get("chargers"));

        while (iterator.hasNext()) {
            System.out.println("The next key is " + iterator.next());
        }


    }
}
