package com.tiffanytimbric.play;

import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrimeChecker {

    public static final int EXIT_CODE_SUCCESS = 0;
    public static final int EXIT_CODE_ERROR_BAD_INPUT = 1;

    public static final String USAGE = """
        Usage:\n\t$ java com.tiffanytimbric.play.PrimeChecker <integers to check>
        """;
    public static final String ERROR_MSG = "Error:";
    public static final String ERROR_MSG_BAD_INPUT = "At least one input is required, an integer number.";

    public static void main(String[] args) {
        if (ArrayUtils.isEmpty(args)) {
            System.err.println(ERROR_MSG);
            System.err.println("\t" + ERROR_MSG_BAD_INPUT);
            System.err.println();
            System.err.println(USAGE);

            System.exit(EXIT_CODE_ERROR_BAD_INPUT);
        }

        List<Integer> inputToCheck = List.of();
        try {
            inputToCheck = readInputToCheck(args);
        } catch (IllegalArgumentException e) {
            System.err.println(ERROR_MSG);
            System.err.println("\t" + e.getMessage());
            System.err.println(USAGE);

            System.exit(EXIT_CODE_ERROR_BAD_INPUT);
        }

        inputToCheck.stream().forEach(valueToCheck ->
            System.out.printf(
                    "isPrime(%d) = %b%n",
                    valueToCheck, isPrime(valueToCheck)
            )
        );

        System.exit(EXIT_CODE_SUCCESS);
    }

    public static boolean isPrime(int num) {
        if (num <= 2) {
            return false;
        }

        if (num % 2 == 0) {
            return false;
        }

        for (int i = 3; i < num / 2; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static List<Integer> readInputToCheck(@Nullable final String[] args) {
        if (ArrayUtils.isEmpty(args)) {
            throw new IllegalArgumentException(ERROR_MSG_BAD_INPUT);
        }

        return Arrays.stream(args)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

}
