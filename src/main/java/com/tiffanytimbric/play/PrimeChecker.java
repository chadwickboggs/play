package com.tiffanytimbric.play;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Comparator;
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
        }
        catch (IllegalArgumentException e) {
            System.err.println(ERROR_MSG);
            System.err.println("\t" + e.getMessage());
            System.err.println(USAGE);

            System.exit(EXIT_CODE_ERROR_BAD_INPUT);
        }

        inputToCheck.stream().parallel()
                .map(valueToCheck ->
                        Pair.of(valueToCheck, isPrime(valueToCheck))
                )
                .sorted(Comparator.comparing(Pair::getLeft))
                .forEachOrdered(resultPair ->
                        System.out.printf(
                                "isPrime(%d) = %b%n",
                                resultPair.getLeft(), resultPair.getRight()
                        )
                );

        System.exit(EXIT_CODE_SUCCESS);
    }

    /**
     * Checks if a number is prime.  Its algorithm is based on the fact that a
     * prime number is only divisible by 1 and itself.  It first checks if the
     * number is 2, which is the only even prime number.  Then it checks if the
     * number is less than or equal to 2, which are not prime numbers.  Next,
     * it checks if the number is even, which are not prime numbers.  Finally,
     * it checks (through single-threaded iteration) if the number is divisible
     * by any odd even number from 3 up to the square root of the number, which
     * are not prime numbers.
     *
     * @param num The number to check.
     * @return True if the number is prime, false otherwise.
     */
    public static boolean isPrime(int num) {
        if (num == 2) {
            return true;
        }

        if (num <= 2) {
            return false;
        }

        if ((num & 1) == 0) {
            return false;
        }

        /*
         * I read that after some value the iterating value may increase by 6 each
         * time instead of by 2 to improve performance, but said algorithm is not
         * implemented here.
         */

        final double sqrtNum = Math.sqrt(num);
        for (int i = 3; i <= sqrtNum; i += 2) {
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
