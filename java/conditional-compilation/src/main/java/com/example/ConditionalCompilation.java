package com.example;

public class ConditionalCompilation {

    final static boolean DEBUG = false;

    public static void main(String[] args) {
	System.out.println("Hello World!");
	if (DEBUG) {
	    System.out.println("Today day is the first day of the rest of your life.");
	}
    }
}
