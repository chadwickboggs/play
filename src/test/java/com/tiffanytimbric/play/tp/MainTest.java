package com.tiffanytimbric.play.tp;

import junit.framework.TestCase;

public class MainTest extends TestCase {

    public void testHasBalancedParens_False() {
        boolean hasBalancedParens = Main.hasBalancedParens("(()");

        assertFalse(hasBalancedParens);
    }

    public void testHasBalancedParens_True() {
        boolean hasBalancedParens = Main.hasBalancedParens("()");

        assertTrue(hasBalancedParens);
    }
}
