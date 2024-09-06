package com.tiffanytimbric.play.interview.delbridge;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BracketCheckerTest {

    public static final String MATCHED_SIMPLE = "()";
    public static final String NOT_MATCHED_SIMPLE = ")(";
    public static final String MATCHED_MEDIUM_NESTED = "(())";
    public static final String MATCHED_MEDIUM_NOT_NESTED = "()()";
    public static final String NOT_MATCHED_MEDIUM_ONE = ")()(";
    public static final String NOT_MATCHED_MEDIUM_TWO = "())(";
    public static final String MATCHED_TWO_TYPES = "({})";
    public static final String NOT_MATCHED_TWO_TYPES = "({)}";

    @Test
    public void testPass_Null() {
        boolean result = new BracketChecker().areBracketsMatched(null);

        assertEquals(BracketChecker.BLANK_MATCH_VALUE, result);
    }

    @Test
    public void testPass_Empty() {
        boolean result = new BracketChecker().areBracketsMatched(StringUtils.EMPTY);

        assertEquals(BracketChecker.BLANK_MATCH_VALUE, result);
    }

    @Test
    public void testPass_Matched_Simple() {
        boolean result = new BracketChecker().areBracketsMatched(MATCHED_SIMPLE);

        assertEquals(BracketChecker.BLANK_MATCH_VALUE, result);
    }

    @Test
    public void testFail_NotMatched_Simple() {
        boolean result = new BracketChecker().areBracketsMatched(NOT_MATCHED_SIMPLE);

        assertFalse(result);
    }

    @Test
    public void testPass_Matched_Medium_Nested() {
        boolean result = new BracketChecker().areBracketsMatched(MATCHED_MEDIUM_NESTED);

        assertEquals(BracketChecker.BLANK_MATCH_VALUE, result);
    }

    @Test
    public void testPass_Matched_Medium_NotNested() {
        boolean result = new BracketChecker().areBracketsMatched(MATCHED_MEDIUM_NOT_NESTED);

        assertEquals(BracketChecker.BLANK_MATCH_VALUE, result);
    }

    @Test
    public void testFail_NotMatched_Medium_One() {
        boolean result = new BracketChecker().areBracketsMatched(NOT_MATCHED_MEDIUM_ONE);

        assertFalse(result);
    }

    @Test
    public void testFail_NotMatched_Medium_Two() {
        boolean result = new BracketChecker().areBracketsMatched(NOT_MATCHED_MEDIUM_TWO);

        assertFalse(result);
    }

    @Test
    public void testPass_Matched_Two_Types() {
        boolean result = new BracketChecker().areBracketsMatched(MATCHED_TWO_TYPES);

        assertEquals(BracketChecker.BLANK_MATCH_VALUE, result);
    }

    @Test
    public void testFail_NotMatched_Two_Types() {
        boolean result = new BracketChecker().areBracketsMatched(NOT_MATCHED_TWO_TYPES);

        assertFalse(result);
    }

}