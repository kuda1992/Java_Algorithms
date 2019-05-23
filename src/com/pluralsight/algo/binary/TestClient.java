package com.pluralsight.algo.binary;

public class TestClient {

    public static void main(String[] args) {

        final BST<String, String> dictionary = new BST<>();


        dictionary.put("phone", "something used to make calls with");
        dictionary.put("chargers", "something used to change the phone");


        System.out.println("The size of the binary tree is " + dictionary.size());

        System.out.println(dictionary.get("chargers"));
    }
}
