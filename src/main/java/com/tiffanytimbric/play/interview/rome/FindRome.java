package com.tiffanytimbric.play.interview.rome;

import java.util.List;

/**
 * Find Rome in a graph of roads.
 */
public interface FindRome {

    int CITY_ID_NOT_FOUND = -1;

    /**
     * Return the name of the implementation.
     *
     * @return the name of the implementation.
     */
    String getName();

    /**
     * Find the first city in the provided data having no outbound road.
     *
     * @param fromCityIds the city ID of the starting city.
     * @param toCityIds the city ID of the ending city.
     * @return the city ID of the first city in the provided data having no outbound road.
     */
    int findRome(
            List<Integer> fromCityIds,
            List<Integer> toCityIds
    );

}
