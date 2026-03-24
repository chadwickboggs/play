package com.tiffanytimbric.play.interview.rome;

import reactor.core.publisher.Flux;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

/**
 * This implementation uses reactive programming techniques via the Reactor library.
 */
public class FindRomeReactive implements FindRome {

    public static final String NAME = "findRomeReactive";

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

        return Flux.fromIterable(toCityIds)
                .filter(toCityId ->
                        !originCityIds.contains(toCityId)
                )
                .defaultIfEmpty(CITY_ID_NOT_FOUND)
                .next()
                .block();
    }

}
