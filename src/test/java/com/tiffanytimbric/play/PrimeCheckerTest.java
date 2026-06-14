package com.tiffanytimbric.play;

import junit.framework.TestCase;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.List;

public class PrimeCheckerTest extends TestCase {

    @Test
    public void testIsPrime() {
        List.of(
                Pair.of(-1, false),
                Pair.of(0, false),
                Pair.of(1, false),
                Pair.of(2, true),
                Pair.of(3, true),
                Pair.of(4, false),
                Pair.of(5, true),
                Pair.of(136, false),
                Pair.of(137, true),
                Pair.of(138, false),
                Pair.of(139, true),
                Pair.of(140, false),
                Pair.of(2147483629, true),
                Pair.of(2147483645, false),
                Pair.of(2147483647, true) // Integer.MAX_VALUE
        ).parallelStream().forEach(pair ->
            assertEquals(
                    (boolean) pair.getRight(),
                    PrimeChecker.isPrime(pair.getLeft())
            )
        );
    }
}