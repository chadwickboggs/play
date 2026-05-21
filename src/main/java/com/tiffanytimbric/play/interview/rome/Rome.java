package com.tiffanytimbric.play.interview.rome;

import javax.annotation.Nullable;
import java.util.List;

/**
 * This class finds the city ID of the first city which contains no outbound roads
 * in the data provided.
 */
public class Rome {

    public static final FindRomeImperative FIND_ROME_IMPERATIVE = new FindRomeImperative();
    public static final FindRomeFunctional FIND_ROME_FUNCTIONAL = new FindRomeFunctional();
    public static final FindRomeReactive FIND_ROME_REACTIVE = new FindRomeReactive();

    public static void main(final String... args) {
        /*
         * Cities: (null, null)
         */
        System.out.printf(
                "Expected Value: %s%n", FindRomeConstants.CITY_ID_NOT_FOUND
        );
        findRome(null, null);

        /*
         * Cities: (null, null)
         */
        System.out.println();
        System.out.printf(
                "Expected Value: %s%n", FindRomeConstants.CITY_ID_NOT_FOUND
        );
        findRome(
                List.of(),
                List.of()
        );

        /*
         * Cities:
         * 0: Rome
         * 1: Naples -> Rome
         */
        System.out.println();
        System.out.println("Expected Value: 0");
        findRome(
                List.of(1),
                List.of(0)
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
        System.out.println("Expected Value: 0");
        findRome(
                List.of(1, 2, 3, 4),
                List.of(0, 1, 0, 3)
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
        System.out.println("Expected Value: 1");
        findRome(
                List.of(0, 2, 3, 4),
                List.of(1, 0, 1, 3)
        );
    }

    private static void findRome(
            @Nullable final List<Integer> fromCityIds,
            @Nullable final List<Integer> toCityIds
    ) {
        System.out.printf(
                "%s\t--> %s(%s, %s)%n",
                FIND_ROME_IMPERATIVE.findRome(fromCityIds, toCityIds),
                FIND_ROME_IMPERATIVE.getName(), fromCityIds, toCityIds
        );

        System.out.printf(
                "%s\t--> %s(%s, %s)%n",
                FIND_ROME_FUNCTIONAL.findRome(fromCityIds, toCityIds),
                FIND_ROME_FUNCTIONAL.getName(), fromCityIds, toCityIds
        );

        System.out.printf(
                "%s\t--> %s(%s, %s)%n",
                FIND_ROME_REACTIVE.findRome(fromCityIds, toCityIds),
                FIND_ROME_REACTIVE.getName(), fromCityIds, toCityIds
        );
    }

}
