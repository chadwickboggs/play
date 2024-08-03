package com.tiffanytimbric.play.tp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * The Main class implements an application that reads lines from the standard input
 * and prints them to the standard output.
 */
public class Main {

    public static final String ANSWER_YES = "YES";
    public static final String ANSWER_NO = "NO";

    public static boolean hasBalancedParens(String line) {
        if (line == null) {
            throw new IllegalArgumentException("The \"line\" parameter must be non-null.");
        }

        if (line.trim().isEmpty()) {
            return true;
        }

        line = " " + line
                .replaceAll("\\(:\\)", "")
                .replaceAll(":\\)", "")
                .replaceAll(":\\(", "") + " ";
        int openParenCount = line.split("\\(").length;
        int closeParenCount = line.split("\\)").length;

        return openParenCount == closeParenCount;
    }

    /**
     * Iterate through each line of input.
     */
    public static void main(final String[] args) throws IOException {
        final InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        final BufferedReader in = new BufferedReader(reader);

        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(
                    hasBalancedParens(line) ? ANSWER_YES : ANSWER_NO
            );
        }
    }
}
