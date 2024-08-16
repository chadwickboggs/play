package com.tiffanytimbric.play.interview.cfs;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MyLinkedListNode<T> {

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
