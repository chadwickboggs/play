package com.tiffanytimbric.play.interview.intuit;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public record Edge<R, S>(
        int id,
        String label,
        Map<String, S> properties,
        @JsonIgnore Node<R, S> source,
        Node<R, S> target
) {
}
