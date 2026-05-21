package com.tiffanytimbric.play.interview.rome;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

/**
 * This implementation uses reactive programming techniques via the Reactor library.
 */
public class FindRomeReactive implements FindRome, FindRomeRx {

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
        return findRomeRx(fromCityIds, toCityIds).block();
    }

    @Nonnull
    public Mono<Integer> findRomeRx(
            @Nullable final List<Integer> fromCityIds,
            @Nullable final List<Integer> toCityIds
    ) {
        if (fromCityIds == null || toCityIds == null) {
            return Mono.just(FindRomeConstants.CITY_ID_NOT_FOUND);
        }
        if (fromCityIds.isEmpty() || toCityIds.isEmpty()) {
            return Mono.just(FindRomeConstants.CITY_ID_NOT_FOUND);
        }

        final HashSet<Integer> fromCityIdsSet = new HashSet<>(fromCityIds);

        return Flux.fromIterable(toCityIds)
                .filter(toCityId ->
                        !fromCityIdsSet.contains(toCityId)
                )
                .defaultIfEmpty(FindRomeConstants.CITY_ID_NOT_FOUND)
                .next();
    }

}
