package com.tiffanytimbric.play.interview.cfs;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

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

    @Nullable
    public static <T> MyLinkedList<T> removeDuplicates(
            @Nullable final MyLinkedList<T> inputList
    ) {
        if (MyLinkedList.isEmpty(inputList)) {
            return inputList;
        }
        if (!inputList.containsDuplicates) {
            return inputList;
        }

        final MyLinkedList<T> listWithoutDuplicates = new MyLinkedList<>();
        MyLinkedListNode<T> firstNode = inputList.headNode;
        listWithoutDuplicates.add(firstNode.value);
        if (firstNode.next == null) {
            return listWithoutDuplicates;
        }

        MyLinkedListNode<T> currentNode = firstNode.next;
        while (currentNode != null) {
            if (listWithoutDuplicates.contains(currentNode.value)) {
                currentNode = currentNode.next;

                continue;
            }

            listWithoutDuplicates.add(currentNode.value);

            currentNode = currentNode.next;
        }

        return listWithoutDuplicates;
    }


    @Nullable
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

    static class MyLinkedList<T> {

        public MyLinkedListNode<T> headNode;
        public MyLinkedListNode<T> tailNode;
        public HashMap<T, List<MyLinkedListNode<T>>> nodeMap = new HashMap<>();
        public boolean containsDuplicates;

        public MyLinkedList() {
        }

        public MyLinkedList(
                @Nonnull final MyLinkedListNode<T> headNode
        ) {
            if (headNode == null) {
                throw new IllegalArgumentException("Parameter `headNode` must be non-null.");
            }

            this.headNode = headNode;
            this.tailNode = headNode;

            nodeMap.put(headNode.value, List.of(headNode));
        }

        public MyLinkedList(
                @Nullable final List<T> list
        ) {
            if (CollectionUtils.isEmpty(list)) {
                return;
            }

            addAll(list);
        }

        public static boolean isEmpty(
                @Nullable final MyLinkedList<?> list
        ) {
            return list == null || list.isEmpty();
        }

        public boolean isEmpty() {
            return headNode == null;
        }

        public boolean contains(@Nullable final T value) {
            if (value == null) {
                return false;
            }

            return nodeMap.containsKey(value);
        }

        public long size() {
            if (!containsDuplicates) {
                return nodeMap.size();
            }

            if (headNode == null) {
                return 0;
            }

            final AtomicLong size = new AtomicLong(1);
            MyLinkedListNode<?> curentNode = headNode;
            while (curentNode.next != null) {
                size.getAndIncrement();

                curentNode = curentNode.next;
            }

            return size.get();
        }

        @Nonnull
        public MyLinkedList<T> add(
                @Nonnull final T value
        ) {
            if (value == null) {
                throw new IllegalArgumentException("Parameter `value` must be non-null.");
            }

            if (this.headNode == null) {
                this.headNode = new MyLinkedListNode<>(value);
                this.tailNode = this.headNode;

                return this;
            }

            tailNode.next = new MyLinkedListNode<>(value);
            tailNode = tailNode.next;

            if (nodeMap.containsKey(value)) {
                containsDuplicates = true;
                final List<MyLinkedListNode<T>> currentNodes = nodeMap.get(value);
                currentNodes.add(tailNode);
            }
            else {
                final ArrayList<MyLinkedListNode<T>> currentNodes = new ArrayList<>();
                currentNodes.add(tailNode);
                nodeMap.put(value, currentNodes);
            }

            return this;
        }

        @Nonnull
        public MyLinkedList<T> addAll(@Nullable final List<T> list) {
            if (CollectionUtils.isEmpty(list)) {
                return this;
            }

            list.forEach(this::add);

            return this;
        }
    }

    static class MyLinkedListNode<T> {

        public T value;
        public MyLinkedListNode<T> next;

        public MyLinkedListNode(
                @Nonnull final T value
        ) {
            this.value = value;
        }

        public MyLinkedListNode(
                @Nonnull final T value,
                @Nullable final MyLinkedListNode<T> next
        ) {
            this.value = value;
            this.next = next;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }
            MyLinkedListNode rhs = (MyLinkedListNode) obj;
            return new EqualsBuilder()
                    .append(this.value, rhs.value)
                    .append(this.next, rhs.next)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder()
                    .append(value)
                    .toHashCode();
        }
    }

}
