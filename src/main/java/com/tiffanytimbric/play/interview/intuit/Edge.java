package com.tiffanytimbric.play.interview.intuit;

import java.util.Map;

public record Edge<S>(
        int id,
        String label,
        Map<String, S> properties,
        Node source,
        Node target
) {
}
