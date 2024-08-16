package com.tiffanytimbric.play.interview.cfs;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MyLinkedList<T> {

    public static final MyLinkedList<?> EMPTY_LIST = new MyLinkedList<>();

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
        nodeMap.put(headNode.value, new ArrayList<>(List.of(headNode)));

        if (headNode.next == null) {
            return;
        }

        MyLinkedListNode<T> currentNode = headNode.next;
        while (currentNode != null) {
            if (
                    nodeMap.containsKey(currentNode.value)
                            && nodeMap.get(currentNode.value).contains(currentNode)
            ) {
                break;
            }

            add(currentNode);

            tailNode = currentNode;
            currentNode = currentNode.next;
        }
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

    public boolean contains(@Nullable final MyLinkedListNode<T> node) {
        if (headNode == null) {
            return node == null;
        }

        final Set<MyLinkedListNode<T>> seenNodes = new HashSet<>();
        MyLinkedListNode<T> currentNode = headNode;
        while (currentNode != null && !seenNodes.contains(currentNode)) {
            if (currentNode.equals(node)) {
                return true;
            }

            seenNodes.add(currentNode);

            currentNode = currentNode.next;
        }

        return false;
    }

    public boolean contains(@Nullable final T value) {
        if (headNode == null) {
            return value == null;
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
            @Nullable final MyLinkedListNode<T> node
    ) {
        if (node == null) {
            return (MyLinkedList<T>) MyLinkedList.EMPTY_LIST;
        }

        if (headNode == null) {
            headNode = node;
            tailNode = headNode;
            nodeMap.put(node.value, new ArrayList<>(List.of(node)));

            return this;
        }

        if (headNode.next == null) {
            return this;
        }

        tailNode.next = node;

        if (MapUtils.isEmpty(nodeMap) || !nodeMap.containsKey(node.value)) {
            final ArrayList<MyLinkedListNode<T>> seenMyLinkedListNodes = new ArrayList<>();
            seenMyLinkedListNodes.add(node);
            nodeMap.put(node.value, seenMyLinkedListNodes);
        }
        else {
            containsDuplicates = true;
            nodeMap.get(node.value).add(node);
        }

        return this;
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

            return this;
        }

        List<MyLinkedListNode<T>> currentNodes;
        if (
                MapUtils.isEmpty(nodeMap) || !nodeMap.containsKey(value)
        ) {
            currentNodes = new ArrayList<>();
        }
        else {
            currentNodes = nodeMap.get(value);
            containsDuplicates = true;
        }

        currentNodes.add(tailNode);
        nodeMap.put(value, currentNodes);

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
