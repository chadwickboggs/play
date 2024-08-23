package com.tiffanytimbric.play.interview.cfs;

import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public final class ListUtil {

    public static void main(String[] args) {
        //
        // Build a MyLinkedList which contains no duplicates and no cycles.
        //
        final List<Integer> jdkListClean = Arrays.asList(
                1, 2, 3
        );
        final List<Integer> jdkLinkedListClean = new LinkedList<>(jdkListClean);

        System.out.println("Size of Linked List Clean: " + jdkLinkedListClean.size());

        //
        // Build a MyLinkedList which contains one duplicate and zero cycles.
        //
        final MyLinkedList<Integer> myLinkedListWithDuplicates = new MyLinkedList<>(jdkListClean)
                .addAll(jdkListClean);

        System.out.println("Size of Linked List With Duplicates: " + myLinkedListWithDuplicates.size());

        // Remove duplicates.
        final MyLinkedList<Integer> myLinkedListWithDuplicatesRemoved = removeDuplicates(myLinkedListWithDuplicates);

        System.out.println("Size of Linked List With Duplicates Removed: " + myLinkedListWithDuplicatesRemoved.size());

        //
        // Build a MyLinkedList which contains no duplicates and one cycle.
        //
        final MyLinkedListNode<Integer> nodeTwo = new MyLinkedListNode<>(2);
        final MyLinkedListNode<Integer> nodeThree = new MyLinkedListNode<>(3);
        nodeTwo.next = nodeThree;
        nodeThree.next = nodeTwo;

        final MyLinkedList<Integer> myLinkedListWithCycles = new MyLinkedList<>(
                new MyLinkedListNode<>(1, nodeTwo)
        );

        System.out.println("Size of Linked List With Cycles: " + myLinkedListWithCycles.size());

        // Remove cycles.
        final MyLinkedList<Integer> myLinkedListWithCyclesRemoved = removeCycles(myLinkedListWithCycles);

        System.out.println("Size of Linked List With Cycles Removed: " + myLinkedListWithCyclesRemoved.size());
    }

    public static <T> boolean containsDuplicates(
            @Nullable final MyLinkedList<T> inputList
    ) {
        return inputList.containsDuplicates;
    }

    @Nonnull
    public static <T> MyLinkedList<T> findDuplicates(
            @Nullable final MyLinkedList<T> inputList
    ) {
        if (MyLinkedList.isEmpty(inputList)) {
            return (MyLinkedList<T>) MyLinkedList.EMPTY_LIST;
        }
        if (!inputList.containsDuplicates) {
            return (MyLinkedList<T>) MyLinkedList.EMPTY_LIST;
        }

        final List<MyLinkedListNode<T>> duplicates = inputList.nodeMap.values().stream()
                .filter(nodes -> CollectionUtils.isNotEmpty(nodes))
                .filter(nodes -> nodes.size() > 1)
                .flatMap(nodes -> nodes.stream())
                .toList();

        if (CollectionUtils.isEmpty(duplicates)) {
            return (MyLinkedList<T>) MyLinkedList.EMPTY_LIST;
        }

        final MyLinkedList<T> myLinkedList = new MyLinkedList<>();
        duplicates.forEach(node ->
                myLinkedList.add(node)
        );

        return myLinkedList;

/*
        final MyLinkedList<T> seenValues = new MyLinkedList<>();
        final MyLinkedList<T> duplicates = new MyLinkedList<>();
        MyLinkedListNode<T> firstNode = inputList.headNode;
        seenValues.add(firstNode.value);
        if (firstNode.next == null) {
            return seenValues;
        }

        MyLinkedListNode<T> currentNode = firstNode.next;
        while (currentNode != null) {
            if (!seenValues.contains(currentNode.value)) {
                seenValues.add(currentNode.value);
            }
            else {
                duplicates.add(currentNode.value);
            }

            currentNode = currentNode.next;
        }

        return duplicates;
*/
    }

    @Nonnull
    public static <T> MyLinkedList<T> removeDuplicates(
            @Nullable final MyLinkedList<T> inputList
    ) {
        if (MyLinkedList.isEmpty(inputList)) {
            return (MyLinkedList<T>) MyLinkedList.EMPTY_LIST;
        }
        if (!inputList.containsDuplicates) {
            return inputList;
        }

        final Set<T> seenValues = new HashSet<>();
        MyLinkedListNode<T> firstNode = inputList.headNode;
        seenValues.add(firstNode.value);
        if (firstNode.next == null) {
            return new MyLinkedList<>(seenValues);
        }

        final MyLinkedList<T> duplicates = new MyLinkedList<>();
        MyLinkedListNode<T> currentNode = firstNode.next;
        while (currentNode != null) {
            if (seenValues.contains(currentNode.value)) {
                duplicates.add(currentNode);
            }
            else {
                seenValues.add(currentNode.value);
            }

            currentNode = currentNode.next;
        }

        return duplicates;
    }

    public static <T> boolean containsCycles(
            @Nullable final MyLinkedList<T> inputList
    ) {
        if (
                MyLinkedList.isEmpty(inputList)
                        || inputList.headNode == null
                        || inputList.headNode.next == null
        ) {
            return false;
        }

        final Set<MyLinkedListNode<T>> seenNodes = new HashSet<>();
        MyLinkedListNode<T> currentNode = inputList.headNode;
        while (currentNode != null) {
            if (seenNodes.contains(currentNode)) {
                return true;
            }

            seenNodes.add(currentNode);

            currentNode = currentNode.next;
        }

        return false;
    }

    @Nonnull
    public static <T> MyLinkedList<T> findCycles(
            @Nullable final MyLinkedList<T> inputList
    ) {
        if (
                MyLinkedList.isEmpty(inputList)
                        || inputList.headNode == null
                        || inputList.headNode.next == null
        ) {
            return (MyLinkedList<T>) MyLinkedList.EMPTY_LIST;
        }

        final MyLinkedList<T> cycles = new MyLinkedList<>();
        final Set<MyLinkedListNode<T>> seenNodes = new HashSet<>();
        MyLinkedListNode<T> currentNode = inputList.headNode;
        while (currentNode != null) {
            if (seenNodes.contains(currentNode)) {
                cycles.add(currentNode);

                return cycles;
            }

            seenNodes.add(currentNode);

            currentNode = currentNode.next;
        }

        return cycles;
    }


    @Nonnull
    public static <T> MyLinkedList<T> removeCycles(
            @Nullable final MyLinkedList<T> inputList
    ) {
        if (
                MyLinkedList.isEmpty(inputList)
                        || inputList.headNode == null
                        || inputList.headNode.next == null
        ) {
            return (MyLinkedList<T>) MyLinkedList.EMPTY_LIST;
        }

        final Set<MyLinkedListNode<T>> seenNodes = new HashSet<>();
        MyLinkedListNode<T> priorNode = null;
        MyLinkedListNode<T> currentNode = inputList.headNode;
        while (currentNode != null) {
            if (seenNodes.contains(currentNode)) {
                priorNode.next = null;

                return new MyLinkedList<>(
                        new MyLinkedListNode<>(currentNode.value)
                );
            }

            seenNodes.add(currentNode);

            priorNode = currentNode;
            currentNode = currentNode.next;
        }

        return (MyLinkedList<T>) MyLinkedList.EMPTY_LIST;
    }
}