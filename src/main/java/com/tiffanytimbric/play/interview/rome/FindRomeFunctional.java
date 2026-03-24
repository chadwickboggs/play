package com.tiffanytimbric.play.interview.rome;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

/**
 * This implementation uses functional techniques via the Java 8 stream API.
 */
public class FindRomeFunctional implements FindRome {

    public static final String NAME = "findRomeFunctional";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int findRome(
            @Nullable final List<Integer> fromCityIds,
            @Nullable final List<Integer> toCityIds
    ) {
        if (fromCityIds == null || toCityIds == null) {
            return FindRome.CITY_ID_NOT_FOUND;
        }
        if (fromCityIds.isEmpty() || toCityIds.isEmpty()) {
            return FindRome.CITY_ID_NOT_FOUND;
        }

        final HashSet<Integer> originCityIds = new HashSet<>(fromCityIds);

        return toCityIds.stream()
                .mapToInt(toCityId -> toCityId)
                .filter(toCityId ->
                        !originCityIds.contains(toCityId)
                )
                .findFirst()
                .orElse(FindRome.CITY_ID_NOT_FOUND);
    }
}
