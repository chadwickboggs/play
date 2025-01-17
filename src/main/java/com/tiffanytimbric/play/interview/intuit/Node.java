package com.tiffanytimbric.play.interview.intuit;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public final class Node<R, S> {

    private int id;
    private String label;
    private NodeType type;
    private Map<String, R> properties;
    private Map<String, Edge<S>> relationships;

    public Node(
            int id,
            @Nonnull final String label,
            @Nonnull final NodeType type
    ) {
        this(id, label, type, null, null);
    }

    public Node(
            int id,
            @Nonnull final String label,
            @Nonnull final NodeType type,
            @Nullable final Map<String, R> properties,
            @Nullable final Map<String, Edge<S>> relationships
    ) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.properties = properties;
        this.relationships = relationships;
    }

    @Nonnull
    public int getId() {
        return id;
    }

    @Nonnull
    public String getLabel() {
        return label;
    }

    @Nonnull
    public NodeType getType() {
        return type;
    }

    @Nullable
    public Map<String, R> getProperties() {
        return properties;
    }

    public void setProperties(
            @Nullable final Map<String, R> properties
    ) {
        this.properties = properties;
    }

    @Nullable
    public Map<String, Edge<S>> getRelationships() {
        return relationships;
    }

    public void setRelationships(
            @Nullable final Map<String, Edge<S>> relationships
    ) {
        this.relationships = relationships;
    }

    @Override
    public boolean equals(
            @Nullable final Object obj
    ) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Node rhs = (Node) obj;
        return new EqualsBuilder()
                .append(this.id, rhs.id)
                .append(this.label, rhs.label)
                .append(this.type, rhs.type)
                .append(this.properties, rhs.properties)
                .append(this.relationships, rhs.relationships)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("label", label)
                .append("type", type)
                .append("properties", properties)
                .append("relationships", relationships)
                .toString();
    }
}
