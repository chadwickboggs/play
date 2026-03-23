package com.tiffanytimbric.play.interview.rome;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class FindRome {

    public static final int CITY_ID_NOT_FOUND = -1;

    public static void main(final String... args) {
        /*
         * Cities: (null, null)
         */
        List<Integer> fromCityIds = null;
        List<Integer> toCityIds = null;
        System.out.println(String.format(
                "findRome(%s, %s): %s", fromCityIds, toCityIds,
                findRome(null, null)
        ));

        /*
         * Cities: (null, null)
         */
        fromCityIds = List.of();
        toCityIds = List.of();
        System.out.println(String.format(
                "findRome(%s, %s): %s", fromCityIds, toCityIds,
                findRome(null, null)
        ));

        /*
         * Cities:
         * 0: Rome
         * 1: Naples -> Rome
         */
        fromCityIds = List.of(1);
        toCityIds = List.of(0);
        System.out.println(String.format(
                "findRome(%s, %s): %s", fromCityIds, toCityIds,
                findRome(null, null)
        ));

        /*
         * Cities:
         * 0: Rome
         * 1: Naples -> Rome
         * 2: Turin -> Naples
         * 3: Palermo -> Rome
         * 4: Genova -> Palermo
         */
        fromCityIds = List.of(1, 2, 3, 4);
        toCityIds = List.of(0, 1, 0, 3);
        System.out.println(String.format(
                "findRome(%s, %s): %s", fromCityIds, toCityIds,
                findRome(fromCityIds, toCityIds)
        ));

        /*
         * Cities:
         * 0: Naples -> Rome
         * 1: Rome
         * 2: Turin -> Naples
         * 3: Palermo -> Rome
         * 4: Genova -> Palermo
         */
        fromCityIds = List.of(0, 2, 3, 4);
        toCityIds = List.of(1, 0, 1, 3);
        System.out.println(String.format(
                "findRome(%s, %s): %s", fromCityIds, toCityIds,
                findRome(fromCityIds, toCityIds)
        ));
    }

    private static int findRome(
            @Nullable final List<Integer> fromCityIds,
            @Nullable final List<Integer> toCityIds
    ) {
        if (fromCityIds == null || toCityIds == null) {
            return CITY_ID_NOT_FOUND;
        }
        if (fromCityIds.isEmpty() || toCityIds.isEmpty()) {
            return CITY_ID_NOT_FOUND;
        }

        final HashSet<Integer> originCityIds = new HashSet<>(fromCityIds);

        for (int toCityId : toCityIds) {
            if (!originCityIds.contains(toCityId)) {
                return toCityId;
            }
        }

        return CITY_ID_NOT_FOUND;
    }

}
