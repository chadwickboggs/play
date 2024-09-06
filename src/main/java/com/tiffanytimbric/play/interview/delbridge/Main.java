package com.tiffanytimbric.play.interview.delbridge;

import javax.annotation.Nullable;

public final class Main {

    public static void main(@Nullable final String... args) {
        boolean passed = true;

        //
        // Pass: null.
        //
        boolean expectedValue = true;
        passed = passed && expectedValue ==
                test(null, expectedValue);

        //
        // Pass: empty.
        //
        passed = passed && expectedValue ==
                test("", expectedValue);

        //
        // Pass: simple.
        //
        passed = passed && expectedValue ==
                test("()", expectedValue);

        //
        // Pass: complicated.
        //
        expectedValue = false;
        passed = passed && expectedValue ==
                test("({)}", expectedValue);

        System.out.printf(
                "%nFull Test -> %s%n",
                passed ? "PASSED" : "FAILED"
        );
    }

    private static boolean test(
            @Nullable final String value,
            final boolean expectedValue
    ) {
        System.out.printf("\"%s\"", value);

        boolean areMatched = new BracketChecker().areBracketsMatched(value);

        System.out.printf(
                " -> (%s, %s) -> %s%n",
                areMatched,
                expectedValue,
                areMatched == expectedValue ? "PASSED" : "FAILED"
        );

        return areMatched;
    }
}
