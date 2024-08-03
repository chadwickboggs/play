package com.tiffanytimbric.play.interview.intuit;

import java.util.Map;

public record Graph<Q, R, S>(
        int id,
        String name,
        Map<String, Q> properties,
        Node<R, S> startNode
) {
}
