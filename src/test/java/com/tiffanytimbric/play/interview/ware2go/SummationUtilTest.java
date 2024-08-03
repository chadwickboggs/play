package com.tiffanytimbric.play.interview.ware2go;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SummationUtilTest {

    @Test
    public void testSum_No_Lists() {
        final List<Pair<Integer, Long>> summation = SummationUtil.sum();

        assertTrue(isEmpty(summation));
    }

    @Test
    public void testSum_Null_List() {
        final List<Pair<Integer, Long>> summation = SummationUtil.sum(null);

        assertTrue(isEmpty(summation));
    }

    @Test
    public void testSum_One_List() {
        final List<Pair<Integer, Long>> summation = SummationUtil.sum(
                List.of(
                        Pair.of(1, 1L),
                        Pair.of(3, 3L)
                )
        );

        assertEquals(
                List.of(
                        Pair.of(1, 1L),
                        Pair.of(3, 3L)
                ),
                summation
        );
    }

    @Test
    public void testSum_One_List_One_Null_List() {
        final List<Pair<Integer, Long>> summation = SummationUtil.sum(
                List.of(
                        Pair.of(1, 1L),
                        Pair.of(3, 3L)
                ),
                null
        );

        assertEquals(
                List.of(
                        Pair.of(1, 1L),
                        Pair.of(3, 3L)
                ),
                summation
        );
    }

    @Test
    public void testSum_One_List_Containing_Null_values() {
        final List<Pair<Integer, Long>> inputList = new ArrayList<>();
        inputList.add(
                Pair.of(1, 1L)
        );
        inputList.add(null);
        inputList.add(
                Pair.of(3, 3L)
        );
        final List<Pair<Integer, Long>> summation = SummationUtil.sum(inputList);

        assertEquals(
                List.of(
                        Pair.of(1, 1L),
                        Pair.of(3, 3L)
                ),
                summation
        );
    }

    @Test
    public void testSum_Partially_Intersecting_Lists() {
        final List<Pair<Integer, Long>> listOne = List.of(
                Pair.of(1, 1L),
                Pair.of(3, 3L)
        );

        final List<Pair<Integer, Long>> listTwo = List.of(
                Pair.of(2, 20L),
                Pair.of(3, 30L)
        );

        final List<Pair<Integer, Long>> summation = SummationUtil.sum(
                listOne, listTwo
        );

        assertEquals(
                List.of(
                        Pair.of(1, 1L),
                        Pair.of(2, 20L),
                        Pair.of(3, 33L)
                ),
                summation
        );
    }

}