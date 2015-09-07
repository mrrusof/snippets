package com.example;

public class Buggy {

    static String name;

    public static void main(String[] args) {
        int n = name.length(); // this is bug 1
        System.out.println(n);
        name += "; The end."; // this is bug 2
        System.out.println(name); // this is bug 3
    }
}
