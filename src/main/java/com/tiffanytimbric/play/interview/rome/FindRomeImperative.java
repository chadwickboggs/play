package com.tiffanytimbric.play.interview.rome;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

/**
 * This implementation is imperative usiing no functional programming techniques.
 */
public class FindRomeImperative implements FindRome {

    public static final String NAME = "findRomeImperative";

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
            return FindRomeConstants.CITY_ID_NOT_FOUND;
        }
        if (fromCityIds.isEmpty() || toCityIds.isEmpty()) {
            return FindRomeConstants.CITY_ID_NOT_FOUND;
        }

        final HashSet<Integer> fromCityIdsSet = new HashSet<>(fromCityIds);

        for (int toCityId : toCityIds) {
            if (!fromCityIdsSet.contains(toCityId)) {
                return toCityId;
            }
        }

        return FindRomeConstants.CITY_ID_NOT_FOUND;
    }
}
