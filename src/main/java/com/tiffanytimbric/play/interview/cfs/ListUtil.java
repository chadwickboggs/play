package com.tiffanytimbric.play.interview.cfs;

import org.apache.commons.collections4.CollectionUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public final class ListUtil {

    public static void main(String[] args) {
        final List<Integer> listClean = Arrays.asList(
                1, 2, 3, 4, 5, 6
        );
        final List linkedListClean = new LinkedList(listClean);

        System.out.println("Size of Linked List Clean: " + linkedListClean.size());

        final List linkedListWithDuplicates = new LinkedList();
        linkedListWithDuplicates.addAll(listClean);

        System.out.println("Size of Linked List With Duplicates: " + linkedListWithDuplicates.size());

        final List linkedListWithDuplicatesRemoved = removeDuplicates(linkedListWithDuplicates);

        System.out.println("Size of Linked List With Duplicates Removed: " + linkedListWithDuplicatesRemoved.size());

        final MyLinkedList linkedListWithCycles = new MyLinkedList(
                new MyLinkedListNode(
                        1,
                        new MyLinkedListNode<>(
                                2,
                                new MyLinkedListNode<>(
                                        3,
                                        null
                                )
                        )
                )
        );

        System.out.println("Size of Linked List With Cycles: " + linkedListWithCycles.size());
    }

    @Nonnull
    public static List removeDuplicates(@Nullable final List inputList) {
        if (CollectionUtils.isEmpty(inputList)) {
            return new ArrayList();
        }

        final List listWithoutDuplicates = new ArrayList();
        MyLinkedListNode firstNode = (MyLinkedListNode) inputList.get(0);
        listWithoutDuplicates.add(firstNode);

        final HashSet<MyLinkedListNode> seenNodes = new HashSet<>();
        MyLinkedListNode currentNode = firstNode;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }

        // TODO: Implement.

        return listWithoutDuplicates;
    }

    static class MyLinkedList<T> {

        public MyLinkedListNode<T> head;

        public MyLinkedList() {
            this(null);
        }

        public MyLinkedList(@Nullable final MyLinkedListNode<T> head) {
            this.head = head;
        }

        public long size() {
            if (head == null) {
                return 0;
            }

            MyLinkedListNode<T> currentNode = head;
            long size = 1;
            while (currentNode.next != null) {
                size++;

                currentNode = currentNode.next;
            }

            return size;
        }
    }

    static class MyLinkedListNode<T> {

        public T value;
        public MyLinkedListNode<T> next;

        public MyLinkedListNode(
                @Nonnull final T value,
                @Nullable final MyLinkedListNode<T> next
        ) {
            this.value = value;
            this.next = next;
        }

    }

}
