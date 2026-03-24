package com.tiffanytimbric.play.interview.rome;

import java.util.List;

public interface FindRome {

    int CITY_ID_NOT_FOUND = -1;

    String getName();

    int findRome(
            List<Integer> fromCityIds,
            List<Integer> toCityIds
    );

}
