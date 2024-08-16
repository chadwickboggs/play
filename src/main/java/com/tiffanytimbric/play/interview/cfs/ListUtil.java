package com.tiffanytimbric.play.interview.cfs;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public final class ListUtil {

    public static void main(String[] args) {
        final List<Integer> listClean = Arrays.asList(
                1, 2, 3
        );
        final List<Integer> linkedListClean = new LinkedList<>(listClean);

        System.out.println("Size of Linked List Clean: " + linkedListClean.size());

        final MyLinkedList<Integer> linkedListWithDuplicates = new MyLinkedList<>(listClean)
                .addAll(listClean);

        System.out.println("Size of Linked List With Duplicates: " + linkedListWithDuplicates.size());

        final MyLinkedList<Integer> linkedListWithDuplicatesRemoved = removeDuplicates(linkedListWithDuplicates);

        System.out.println("Size of Linked List With Duplicates Removed: " + linkedListWithDuplicatesRemoved.size());

        // TODO: Implement.
/*
        final MyLinkedList<Integer> linkedListWithCycles = new MyLinkedList<>();

        System.out.println("Size of Linked List With Cycles: " + linkedListWithCycles.size());
*/
    }

    public static <T> boolean containsDuplicates(
            @Nullable final MyLinkedList<T> inputList
    ) {
        return !findDuplicates(inputList).isEmpty();
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

        final MyLinkedList<T> seenValues = new MyLinkedList<>();
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

            currentNode = currentNode.next;
        }

        return seenValues;
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

        MyLinkedListNode<T> currentNode = inputList.headNode;
        while (currentNode != null) {
            if (inputList.nodeMap.containsKey(currentNode.value)) {
                final List<MyLinkedListNode<T>> nodes = inputList.nodeMap.get(currentNode.value);

                final int currentNodeHashCode = currentNode.hashCode();

                return nodes.stream()
                        .map(MyLinkedListNode::hashCode)
                        .anyMatch(nodeHashCode ->
                                nodeHashCode.equals(currentNodeHashCode)
                        );
            }

            currentNode = currentNode.next;
        }

        return false;
    }

}
