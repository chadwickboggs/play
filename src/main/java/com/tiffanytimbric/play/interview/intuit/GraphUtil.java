package com.tiffanytimbric.play.interview.intuit;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public final class GraphUtil {

    public static Graph removeDuplicates(
            @Nonnull Graph graph
    ) {
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
