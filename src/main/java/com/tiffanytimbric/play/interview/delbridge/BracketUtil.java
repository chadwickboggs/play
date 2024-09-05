package com.tiffanytimbric.play.interview.delbridge;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.Stack;

public final class BracketUtil {

    public static final boolean BLANK_MATCH_VALUE = true;
    public static final String DEFAULT_BRACKET_TYPES = "(),{},[]";

    private BracketTypesData bracketTypesData = new BracketTypesData(DEFAULT_BRACKET_TYPES);

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

        boolean areMatched = new BracketUtil().areBracketsMatched(value);

        System.out.printf(
                " -> (%s, %s) -> %s%n",
                areMatched,
                expectedValue,
                areMatched == expectedValue ? "PASSED" : "FAILED"
        );

        return areMatched;
    }

    public BracketUtil() {
        this(DEFAULT_BRACKET_TYPES);
    }

    public BracketUtil(
            @Nullable final String bracketTypes
    ) {
        this(new BracketTypesData(bracketTypes));
    }

    public BracketUtil(
            @Nullable final BracketTypesData bracketTypesData
    ) {
        if (bracketTypesData == null) {
            throw new IllegalArgumentException(
                    "Parameter `bracketTypesData` cannot be null."
            );
        }

        this.bracketTypesData = bracketTypesData;
    }

    public BracketTypesData getBracketTypesData() {
        return bracketTypesData;
    }

    public void setBracketTypes(
            @Nullable final BracketTypesData bracketTypesData
    ) {
        if (bracketTypesData == null) {
            throw new IllegalArgumentException(
                    "Parameter `bracketTypesData` cannot be null."
            );
        }

        this.bracketTypesData = bracketTypesData;
    }

    public boolean areBracketsMatched(
            @Nullable final String value
    ) {
        if (StringUtils.isBlank(value)) {
            return BLANK_MATCH_VALUE;
        }

        // Behavior:
        //  * Pass: null
        //  * Pass: empty
        //  * Pass: ()
        //  * Fail: )(
        //  * Pass: (())
        //  * Pass: ()()
        //  * Fail: )()(
        //  * Fail: ())(
        //  * Pass: ({})
        //  * Fail: ({)}

        // Implementation:
        // If we have one stack:
        //  * Iterate through characters of string.
        //  * Push open parens.
        //  * Pop close parens.
        //  * Pass if stack empty only at end of string.

        final Stack<Character> charStack = new Stack<>();

        value.chars()
                .forEach(theChar -> {
                    if (isOpenBracket((char) theChar)) {
                        charStack.push((char) theChar);

                        return;
                    }

                    bracketTypesData.bracketTypes.forEach(bracketType -> {
                        if (charStack.isEmpty()) {
                            return;
                        }
                        if (!isCloseBracket((char) theChar)) {
                            return;
                        }

                        final Optional<Pair<String, String>> bracketTypePairOpt = bracketTypesData.pairOf(
                                String.valueOf((char) theChar)
                        );
                        if (bracketTypePairOpt.isEmpty()) {
                            return;
                        }

                        final Pair<String, String> bracketTypePair = bracketTypePairOpt.get();
                        final String closeBracket = bracketType.getRight();
                        if (!closeBracket.equals(bracketTypePair.getRight())) {
                            return;
                        }

                        char peekedChar = charStack.peek();
                        if (bracketTypePair.getLeft().equals(String.valueOf(peekedChar))) {
                            charStack.pop();
                        }
                    });
                });

        return charStack.isEmpty();
    }

    private boolean isOpenBracket(char theChar) {
        return bracketTypesData.openBrackets.contains(String.valueOf(theChar));
    }

    private boolean isCloseBracket(char theChar) {
        return bracketTypesData.closeBrackets.contains(String.valueOf(theChar));
    }
}
