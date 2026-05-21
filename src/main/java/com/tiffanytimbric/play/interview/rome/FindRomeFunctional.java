package com.tiffanytimbric.play.interview.rome;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

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
        if (isEmpty(fromCityIds) || isEmpty(toCityIds)) {
            return FindRomeConstants.CITY_ID_NOT_FOUND;
        }

        final HashSet<Integer> fromCityIdsSet = new HashSet<>(fromCityIds);

        return toCityIds.stream()
                .mapToInt(toCityId -> toCityId)
                .filter(toCityId ->
                        !fromCityIdsSet.contains(toCityId)
                )
                .findFirst()
                .orElse(FindRomeConstants.CITY_ID_NOT_FOUND);
    }
}
