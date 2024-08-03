package com.tiffanytimbric.play.interview.intuit.songs;

import com.tiffanytimbric.play.interview.intuit.Edge;
import com.tiffanytimbric.play.interview.intuit.Graph;
import com.tiffanytimbric.play.interview.intuit.Node;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

import static com.tiffanytimbric.play.interview.intuit.GraphUtil.*;

public final class JingleUtil {

    @Nonnull
    public static Graph<String, String, String> newMicroJingleGraph() {
        final Map<String, String> graphProperties = new HashMap<>();

        return new Graph<>(
                0, "MicroJingle", graphProperties, newIntroNode()
        );
    }

    @Nonnull
    public static Node<String, String> newIntroNode() {
        final Node<String, String> introNode = newStartNode(
                0, "Intro"
        );
        introNode.setRelationships(
                newIntroNodeRelationships(introNode)
        );

        return introNode;
    }

    @Nonnull
    public static Map<String, Edge<String>> newIntroNodeRelationships(
            @Nonnull final Node<String, String> introNode
    ) {
        final Map<String, Edge<String>> introNodeRelationships = new HashMap<>();
        introNodeRelationships.put(
                "hook",
                new Edge<>(
                        1, "hook", new HashMap<>(), introNode, newHookNode()
                )
        );

        return introNodeRelationships;
    }

    @Nonnull
    public static Node<String, String> newHookNode() {
        final Node<String, String> hookNode = newIntermediateNode(
                1, "Hook"
        );
        hookNode.setRelationships(
                newHookNodeRelationships(hookNode)
        );

        return hookNode;
    }

    @Nonnull
    public static Map<String, Edge<String>> newHookNodeRelationships(
            @Nonnull final Node<String, String> hookNode
    ) {
        final Map<String, Edge<String>> hookNodeRelationships = new HashMap<>();
        hookNodeRelationships.put(
                "tie-up",
                new Edge<>(
                        2, "tie-up", new HashMap<>(), hookNode, newTieUpNode()
                )
        );

        return hookNodeRelationships;
    }

    @Nonnull
    public static Node<String, String> newTieUpNode() {
        return newEndNode(3, "Tie-Up");
    }
}
