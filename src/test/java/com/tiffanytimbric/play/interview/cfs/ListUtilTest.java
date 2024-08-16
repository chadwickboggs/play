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
    private final static MyLinkedList<String> LIST_WITH_CYCLE;

    static {
        final MyLinkedListNode<String> nodeTwo = new MyLinkedListNode<>(
                "Value_Two"
        );
        final MyLinkedListNode<String> nodeThree = new MyLinkedListNode<>(
                "Value_Three"
        );
        nodeTwo.next = nodeThree;
        nodeThree.next = nodeTwo;

        LIST_WITH_CYCLE = new MyLinkedList<>(
                new MyLinkedListNode<>(
                        "Value_One", nodeTwo
                )
        );
    }

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
    }

    @Test
    public void testRemoveDuplicates_With_Duplicates() {
        final MyLinkedList<String> duplicatesRemoved = ListUtil.removeDuplicates(LIST_PLAIN);

        assertNotNull(duplicatesRemoved);
        assertFalse(duplicatesRemoved.isEmpty());
    }

    @Test
    public void testContainsCycles_Empty() {
        boolean containsCycles = ListUtil.containsCycles(LIST_EMPTY);

        assertFalse(containsCycles);
    }

    @Test
    public void testContainsCycles_Plain() {
        boolean containsCycles = ListUtil.containsCycles(LIST_PLAIN);

        assertFalse(containsCycles);
    }

    @Test
    public void testContainsCycles_With_Cycles() {
        boolean containsCycles = ListUtil.containsCycles(LIST_WITH_CYCLE);

        assertTrue(containsCycles);
    }

    @Test
    public void testFindCycles_Empty() {
        final MyLinkedList<String> cycles = ListUtil.findCycles(LIST_EMPTY);

        assertNotNull(cycles);
        assertTrue(cycles.isEmpty());
    }

    @Test
    public void testFindCycles_Plain() {
        final MyLinkedList<String> cycles = ListUtil.findCycles(LIST_PLAIN);

        assertNotNull(cycles);
        assertTrue(cycles.isEmpty());
    }

    @Test
    public void testFindCycles_With_Empty() {
        final MyLinkedList<String> cycles = ListUtil.findCycles(LIST_EMPTY);

        assertNotNull(cycles);
        assertTrue(cycles.isEmpty());
    }

    @Test
    public void testFindCycles_With_Plain() {
        final MyLinkedList<String> cycles = ListUtil.findCycles(LIST_PLAIN);

        assertNotNull(cycles);
        assertTrue(cycles.isEmpty());
    }

    @Test
    public void testFindCycles_With_Cycles() {
        final MyLinkedList<String> cycles = ListUtil.findCycles(LIST_WITH_CYCLE);

        assertNotNull(cycles);
        assertFalse(cycles.isEmpty());
    }

    @Test
    public void testRemoveCycles_Empty() {
        final MyLinkedList<String> cyclesRemoved = ListUtil.removeCycles(LIST_EMPTY);

        assertNotNull(cyclesRemoved);
        assertTrue(cyclesRemoved.isEmpty());
    }

    @Test
    public void testRemoveCycles_Plain() {
        final MyLinkedList<String> cyclesRemoved = ListUtil.removeCycles(LIST_PLAIN);

        assertNotNull(cyclesRemoved);
        assertFalse(cyclesRemoved.isEmpty());
    }

}