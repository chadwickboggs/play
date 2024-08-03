package com.tiffanytimbric.play.tp;

import junit.framework.TestCase;

public class MainTest extends TestCase {

    public void testHasBalancedParens_Frown() {
        boolean hasBalancedParens = Main.hasBalancedParens("(:()");

        assertTrue(hasBalancedParens);
    }

    public void testHasBalancedParens_Coin() {
        boolean hasBalancedParens = Main.hasBalancedParens("(:)");

        assertTrue(hasBalancedParens);
    }
}