package com.tiffanytimbric.play.interview.rome;

import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Find Rome in a graph of roads.
 */
public interface FindRomeRx extends Named {

    /**
     * Find the first city in the provided data having no outbound road.
     *
     * @param fromCityIds the city ID of the starting city.
     * @param toCityIds the city ID of the ending city.
     * @return the city ID of the first city in the provided data having no outbound road.
     */
    Mono<Integer> findRomeRx(
            List<Integer> fromCityIds,
            List<Integer> toCityIds
    );

}
