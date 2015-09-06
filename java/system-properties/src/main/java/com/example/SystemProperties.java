package com.example;

public class SystemProperties {
    public static void main(String[] args) {
	if(System.getProperty("debug.greeting") != null)
	    System.err.println("DEBUG: Greeting");
	System.out.println("Hello World!");
    }
}
