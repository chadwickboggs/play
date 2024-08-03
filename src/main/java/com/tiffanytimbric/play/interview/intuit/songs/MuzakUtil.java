package com.tiffanytimbric.play.interview.intuit.songs;

import com.tiffanytimbric.play.interview.intuit.Edge;
import com.tiffanytimbric.play.interview.intuit.Graph;
import com.tiffanytimbric.play.interview.intuit.Node;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

import static com.tiffanytimbric.play.interview.intuit.GraphUtil.*;

public final class MuzakUtil {

    public static final String LABEL_MUZAK = "Muzak";

    @Nonnull
    public static Graph<String, String, String> newMuzak(
            @Nonnull final String name,
            @Nonnull final String description,
            @Nonnull final Map<String, String> graphProperties
    ) {
        return new Graph<>(
                0,
                LABEL_MUZAK + ": " + name,
                description,
                graphProperties,
                newIntroNode()
        );
    }

    @Nonnull
    public static Node<String, String> newIntroNode() {
        final Node<String, String> introNode = newStartNode(
                0, "Intro"
        );
        introNode.setRelationshipsOutbound(
                newIntroNodeRelationships(introNode)
        );

        return introNode;
    }

    @Nonnull
    public static Map<String, Edge<String, String>> newIntroNodeRelationships(
            @Nonnull final Node<String, String> introNode
    ) {
        final Map<String, Edge<String, String>> introNodeRelationships = new HashMap<>();
        introNodeRelationships.put(
                "hook-one",
                new Edge<>(
                        1, "hook-one", new HashMap<>(), introNode, newHookOneNode()
                )
        );

        return introNodeRelationships;
    }

    @Nonnull
    public static Node<String, String> newHookOneNode() {
        final Node<String, String> hookOneNode = newIntermediateNode(
                1, "Hook One"
        );
        hookOneNode.setRelationshipsOutbound(
                newHookOneNodeRelationships(hookOneNode)
        );

        return hookOneNode;
    }

    @Nonnull
    public static Map<String, Edge<String, String>> newHookOneNodeRelationships(
            @Nonnull final Node<String, String> hookOneNode
    ) {
        final Map<String, Edge<String, String>> hookOneNodeRelationships = new HashMap<>();
        hookOneNodeRelationships.put(
                "hook-two",
                new Edge<>(
                        2, "hook-two", new HashMap<>(),
                        hookOneNode, newHookTwoNode(hookOneNode)
                )
        );

        return hookOneNodeRelationships;
    }

    @Nonnull
    public static Node<String, String> newHookTwoNode(
            @Nonnull final Node<String, String> hookOneNode
    ) {
        final Node<String, String> hookTwoNode = newIntermediateNode(
                1, "Hook Two"
        );
        hookTwoNode.setRelationshipsOutbound(
                newHookTwoNodeRelationships(hookOneNode, hookTwoNode)
        );

        return hookTwoNode;
    }

    @Nonnull
    public static Map<String, Edge<String, String>> newHookTwoNodeRelationships(
            @Nonnull final Node<String, String> hookOneNode,
            @Nonnull final Node<String, String> hookTwoNode
    ) {
        final Map<String, Edge<String, String>> hookTwoNodeRelationships = new HashMap<>();
        hookTwoNodeRelationships.put(
                "hool-one",
                new Edge<>(
                        2, "hook-one", new HashMap<>(), hookTwoNode, hookOneNode
                )
        );
        hookTwoNodeRelationships.put(
                "tie-up",
                new Edge<>(
                        2, "tie-up", new HashMap<>(), hookTwoNode, newTieUpNode()
                )
        );

        return hookTwoNodeRelationships;
    }

    @Nonnull
    public static Node<String, String> newTieUpNode() {
        return newEndNode(3, "Tie-Up");
    }
}
