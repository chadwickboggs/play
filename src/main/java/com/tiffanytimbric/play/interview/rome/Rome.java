package com.tiffanytimbric.play.interview.rome;

import javax.annotation.Nullable;
import java.util.List;

import static com.tiffanytimbric.play.interview.rome.Constants.CITY_ID_NOT_FOUND;

/**
 * This class finds the city ID of the first city which contains no outbound roads
 * in the data provided.
 */
public class Rome {

    public static final FindRomeImperative FIND_ROME_IMPERATIVE = new FindRomeImperative();
    public static final FindRomeFunctional FIND_ROME_FUNCTIONAL = new FindRomeFunctional();
    public static final FindRomeReactive FIND_ROME_REACTIVE = new FindRomeReactive();
    public static final String PASS_EXPECTED_ACTUAL = "Pass: (expected, actual)";
    public static final String FAIL_EXPECTED_ACTUAL = "Fail: (expected, actual)";

    public static void main(final String... args) {
        /*
         * Cities: (null, null)
         */
        findRome(null, null, CITY_ID_NOT_FOUND);

        /*
         * Cities: (null, null)
         */
        System.out.println();
        findRome(
                List.of(),
                List.of(),
                CITY_ID_NOT_FOUND
        );

        /*
         * Cities:
         * 0: Rome
         * 1: Naples -> Rome
         */
        System.out.println();
        findRome(
                List.of(1),
                List.of(0),
                0
        );

        /*
         * Cities:
         * 0: Rome
         * 1: Naples -> Rome
         * 2: Turin -> Naples
         * 3: Palermo -> Rome
         * 4: Genova -> Palermo
         */
        System.out.println();
        findRome(
                List.of(1, 2, 3, 4),
                List.of(0, 1, 0, 3),
                0
        );

        /*
         * Cities:
         * 0: Naples -> Rome
         * 1: Rome
         * 2: Turin -> Naples
         * 3: Palermo -> Rome
         * 4: Genova -> Palermo
         */
        System.out.println();
        findRome(
                List.of(0, 2, 3, 4),
                List.of(1, 0, 1, 3),
                1
        );
    }

    private static void findRome(
            @Nullable final List<Integer> fromCityIds,
            @Nullable final List<Integer> toCityIds,
            int valueExpected
    ) {
        System.out.printf(
                "%s(%s, %s)",
                FIND_ROME_IMPERATIVE.getName(), fromCityIds, toCityIds
        );
        validateResult(valueExpected, FIND_ROME_IMPERATIVE.findRome(fromCityIds, toCityIds));

        System.out.printf(
                "%s(%s, %s)",
                FIND_ROME_FUNCTIONAL.getName(), fromCityIds, toCityIds
        );
        validateResult(
                valueExpected, FIND_ROME_FUNCTIONAL.findRome(fromCityIds, toCityIds)
        );

        System.out.printf(
                "%s(%s, %s)",
                FIND_ROME_REACTIVE.getName(), fromCityIds, toCityIds
        );
        validateResult(
                valueExpected, FIND_ROME_REACTIVE.findRome(fromCityIds, toCityIds)
        );
    }

    private static void validateResult(int valueExpected, int value) {
        if  (value == valueExpected) {
            System.out.printf(" --> " + PASS_EXPECTED_ACTUAL + " = (%s, %s)%n", valueExpected, value);
        }
        else {
            System.out.printf(" --> " + FAIL_EXPECTED_ACTUAL + " = (%s, %s)%n", valueExpected, value);
        }
    }

}
