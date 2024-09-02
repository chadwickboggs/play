package com.tiffanytimbric.play.interview.delbridge;

import org.apache.commons.lang3.StringUtils;

import java.util.Stack;

public class Main {

    private static final boolean BLANK_MATCH_VALUE = true;

    public static void main(final String... args) {
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
            final String value,
            final boolean expectedValue
    ) {
        System.out.printf("\"%s\"", value);

        boolean areMatched = areBracketsMatched(value);

        System.out.printf(
                " -> (%s, %s) -> %s%n",
                areMatched,
                expectedValue,
                areMatched == expectedValue ? "PASSED" : "FAILED"
        );

        return areMatched;
    }

    private static boolean areBracketsMatched(final String value) {
        if (StringUtils.isBlank(value)) {
            return BLANK_MATCH_VALUE;
        }

        //
        // Pass: null
        // Pass: empty
        // Pass: ()
        // Fail: )(
        // Pass: (())
        // Pass: ()()
        // Fail: )()(
        // Fail: ())(
        //
        // Pass: ({})
        // Fail: ({)}
        //

        //
        // Implemtation:
        // If we have one stack:
        //  * Iterate through characters of string.
        //  * Push open parens.
        //  * Pop close parens.
        //  * Pass if stack empty only at end of string.
        //

        final Stack<Character> charStack = new Stack<>();

        value.chars()
                .forEach(theChar -> {
                    if ('(' == theChar || '{' == theChar) {
                        charStack.push((char) theChar);
                    }
                    else if (')' == theChar) {
                        if (charStack.isEmpty()) {
                            charStack.push('z');
                        }
                        else {
                            char poppedChar = charStack.peek();
                            if ('(' == poppedChar) {
                                // Check if `poppedChar` is regular close: Good to go.
                                charStack.pop();
                            }
                        }
                    }
                    else if ('}' == theChar) {
                        if (charStack.isEmpty()) {
                            charStack.push('z');
                        }
                        else {
                            char poppedChar = charStack.peek();
                            if ('{' == poppedChar) {
                                // Check if `poppedChar` is regular close: Good to go.
                                charStack.pop();
                            }
                        }
                    }
                });

        return charStack.isEmpty();
    }
}
