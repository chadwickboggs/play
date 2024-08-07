package com.tiffanytimbric.play.interview.intuit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public final class GraphUtil {

    private static final ThreadLocal<Set<Integer>> seenNodes = new ThreadLocal<>() {
        @Override
        protected Set<Integer> initialValue() {
            return new HashSet<>();
        }
    };

    /**
     * The current implementation of this method requires no joining paths.
     * All paths through the graph must diverge from each other and never join.
     */
    @Nonnull
    public static <Q, R, S> List<Node<R, S>> findDuplicates(
            @Nonnull final Graph<Q, R, S> graph
    ) {
        return findDuplicates(graph.startNode());
    }

    @Nonnull
    public static <R, S> List<Node<R, S>> findDuplicates(
            @Nonnull final Node<R, S> currentNode
    ) {
        final List<Node<R, S>> duplicates = new ArrayList<>();
        if (currentNode == null) {
            return duplicates;
        }

        boolean isDuplicate = seenNodes.get().add(currentNode.getId());
        if (isDuplicate) {
            duplicates.add(currentNode);
        }

        final Map<String, Edge<R, S>> relationshipsOutbound = currentNode.getRelationshipsOutbound();
        if (MapUtils.isEmpty(relationshipsOutbound)) {
            return duplicates;
        }

        relationshipsOutbound.values().stream()
                .map(Edge::target)
                .filter(Objects::nonNull)
                .flatMap(targetNode ->
                        findDuplicates(targetNode).stream()
                )
                .forEach(duplicates::add);

        return duplicates;
    }

    @Nonnull
    public static <Q, R, S> List<Node<R, S>> findCycles(
            @Nonnull final Graph<Q, R, S> graph
    ) {
        // TODO: Implement.

        return new ArrayList<>();
    }

    @Nonnull
    public static <Q, R, S> Graph<Q, R, S> removeDuplicates(
            @Nonnull final Graph<Q, R, S> graph
    ) {
        // TODO: Implement.

        return graph;
    }

    @Nonnull
    public static <Q, R, S> Graph<Q, R, S> removeCycles(
            @Nonnull final Graph<Q, R, S> graph
    ) {
        // TODO: Implement.

        return graph;
    }

    @Nonnull
    public static <R, S> Node<R, S> newStartNode(
            int id,
            @Nonnull final String name
    ) {
        return new Node<>(
                id, name, NodeType.START, null, null, null
        );
    }

    @Nonnull
    public static <R, S> Node<R, S> newStartNode(
            int id,
            @Nonnull final String name,
            @Nullable final Map<String, R> properties,
            @Nullable final Map<String, Edge<R, S>> relationshipsOutbound
    ) {
        return new Node<>(
                id, name, NodeType.START, properties, null, relationshipsOutbound
        );
    }

    @Nonnull
    public static <R, S> Node<R, S> newIntermediateNode(
            int id,
            @Nonnull final String name
    ) {
        return new Node<>(
                id, name, NodeType.INTERMEDIATE, null, null, null
        );
    }

    @Nonnull
    public static <R, S> Node<R, S> newIntermediateNode(
            int id,
            @Nonnull final String name,
            @Nullable final Map<String, R> properties,
            @Nullable final Map<String, Edge<R, S>> relationshipsOutbound
    ) {
        return new Node<>(
                id, name, NodeType.INTERMEDIATE, properties, null, relationshipsOutbound
        );
    }

    @Nonnull
    public static <R, S> Node<R, S> newEndNode(
            int id,
            @Nonnull final String name
    ) {
        return new Node<>(
                id, name, NodeType.END, null, null, null
        );
    }

    @Nonnull
    public static <R, S> Node<R, S> newEndNode(
            int id,
            @Nonnull final String name,
            @Nullable final Map<String, R> properties
    ) {
        return new Node<>(
                id, name, NodeType.END, properties, null, null
        );
    }

    @Nonnull
    public static <R, S> Node<R, S> newNode(
            int id,
            @Nonnull final String name,
            @Nonnull final NodeType type,
            @Nullable final Map<String, R> properties,
            @Nullable final Map<String, Edge<R, S>> relationshipsOutbound
    ) {
        return new Node<>(
                id, name, type, properties, null, relationshipsOutbound
        );
    }
}
