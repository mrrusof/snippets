package com.example;

public class Assertions {
    public static void main(String[] args) {
	int i = 1;
	if(args.length == 1) {
	    i = Integer.parseInt(args[0]);
	}
	assert i > 0 : "i (" + i + ") is not positive thus I will now fail.";
	System.out.printf("i (%d) is positive thus I will now exit.\n", i);
    }
}
