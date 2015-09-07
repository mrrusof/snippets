package com.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UnitTestingTest {
    @Test
    public void testSum() {
	UnitTesting o = new UnitTesting();
	assertEquals(o.sum(1, 2), 3);
    }
}
