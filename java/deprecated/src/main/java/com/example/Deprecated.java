package com.example;

import java.util.Date;

public class Deprecated {
    public static void main(String[] args) {
	Date d = new Date(99, 12, 24); // This is deprecated in Java 1.8
	System.out.println("Date is " + d);
    }
}
