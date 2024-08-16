package com.tiffanytimbric.play.interview.cfs;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListUtilTest {

    private final static MyLinkedList<String> LIST_EMPTY = (MyLinkedList<String>) MyLinkedList.EMPTY_LIST;
    private final static MyLinkedList<String> LIST_PLAIN = new MyLinkedList<>(
            new MyLinkedListNode<>(
                    "Value_One",
                    new MyLinkedListNode<>(
                            "Value_Two",
                            new MyLinkedListNode<>(
                                    "Value_Three"
                            )
                    )
            )
    );
    private final static MyLinkedList<String> LIST_WITH_DUPLICATE = new MyLinkedList<>(
            new MyLinkedListNode<>(
                    "Value_One",
                    new MyLinkedListNode<>(
                            "Value_Two",
                            new MyLinkedListNode<>(
                                    "Value_Two"
                            )
                    )
            )
    );

    @Test
    public void testContainsDuplicates_Empty() {
        boolean containsDuplicates = ListUtil.containsDuplicates(LIST_EMPTY);

        assertFalse(containsDuplicates);
    }

    @Test
    public void testContainsDuplicates_Plain() {
        boolean containsDuplicates = ListUtil.containsDuplicates(LIST_PLAIN);

        assertFalse(containsDuplicates);
    }

    @Test
    public void testContainsDuplicates_With_Duplicate() {
        boolean containsDuplicates = ListUtil.containsDuplicates(LIST_WITH_DUPLICATE);

        assertTrue(containsDuplicates);
    }

    @Test
    public void testFindDuplicates_Empty() {
        final MyLinkedList<String> duplicates = ListUtil.findDuplicates(LIST_EMPTY);

        assertNotNull(duplicates);
        assertTrue(duplicates.isEmpty());
    }

    @Test
    public void testFindDuplicates_Plain() {
        final MyLinkedList<String> duplicates = ListUtil.findDuplicates(LIST_PLAIN);

        assertNotNull(duplicates);
        assertTrue(duplicates.isEmpty());
    }

    @Test
    public void testFindDuplicates_With_Duplicates() {
        final MyLinkedList<String> duplicates = ListUtil.findDuplicates(LIST_WITH_DUPLICATE);

        assertNotNull(duplicates);
        assertFalse(duplicates.isEmpty());
    }

    @Test
    public void testRemoveDuplicates_Empty() {
        final MyLinkedList<String> duplicatesRemoved = ListUtil.removeDuplicates(LIST_EMPTY);

        assertNotNull(duplicatesRemoved);
        assertTrue(duplicatesRemoved.isEmpty());
    }

    @Test
    public void testRemoveDuplicates_Plain() {
        final MyLinkedList<String> duplicatesRemoved = ListUtil.removeDuplicates(LIST_PLAIN);

        assertNotNull(duplicatesRemoved);
        assertFalse(duplicatesRemoved.isEmpty());

        // TODO: Assert that the output list equals the input list.
    }

    @Test
    public void testRemoveDuplicates_With_Duplicates() {
        final MyLinkedList<String> duplicatesRemoved = ListUtil.removeDuplicates(LIST_PLAIN);

        assertNotNull(duplicatesRemoved);
        assertFalse(duplicatesRemoved.isEmpty());

        // TODO: Assert that the output list equals the input list with its duplicates nodes removed.
    }
}