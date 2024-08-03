package com.tiffanytimbric.play.interview.intuit.songs;

import com.tiffanytimbric.play.interview.intuit.Edge;
import com.tiffanytimbric.play.interview.intuit.Graph;
import com.tiffanytimbric.play.interview.intuit.Node;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

import static com.tiffanytimbric.play.interview.intuit.GraphUtil.*;

public final class JingleUtil {

    public static final String LABEL_MICRO_JINGLE = "MicroJingle";

    @Nonnull
    public static Graph<String, String, String> newMicroJingleGraph(
            @Nonnull final String name,
            @Nonnull final String description,
            @Nonnull final Map<String, String> graphProperties
    ) {
        return new Graph<>(
                0,
                LABEL_MICRO_JINGLE + ": " + name,
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
                newIntroNodeRelationshipsOutbound(introNode)
        );

        return introNode;
    }

    @Nonnull
    public static Map<String, Edge<String, String>> newIntroNodeRelationshipsOutbound(
            @Nonnull final Node<String, String> introNode
    ) {
        final Map<String, Edge<String, String>> introNodeRelationshipsOutbound = new HashMap<>();
        introNodeRelationshipsOutbound.put(
                "hook",
                new Edge<>(
                        1, "hook", new HashMap<>(), introNode, newHookNode(introNode)
                )
        );

        return introNodeRelationshipsOutbound;
    }

    @Nonnull
    public static Node<String, String> newHookNode(
            @Nonnull final Node<String, String> introNode
    ) {
        final Node<String, String> hookNode = newIntermediateNode(
                1, "Hook"
        );
        hookNode.setRelationshipsInbound(
                newHookNodeRelationshipsInbound(introNode, hookNode)
        );
        hookNode.setRelationshipsOutbound(
                newHookNodeRelationshipsOutbound(hookNode)
        );

        return hookNode;
    }

    @Nonnull
    private static Map<String, Edge<String, String>> newHookNodeRelationshipsInbound(
            @Nonnull final Node<String, String> introNode,
            @Nonnull final Node<String, String> hookNode
    ) {
        final Map<String, Edge<String, String>> hookNodeRelationshipsInbound = new HashMap<>();
        hookNodeRelationshipsInbound.put(
                "intro",
                new Edge<>(
                        3, "intro", new HashMap<>(), hookNode, introNode
                )
        );

        return hookNodeRelationshipsInbound;
    }

    @Nonnull
    public static Map<String, Edge<String, String>> newHookNodeRelationshipsOutbound(
            @Nonnull final Node<String, String> hookNode
    ) {
        final Map<String, Edge<String, String>> hookNodeRelationshipsOutbound = new HashMap<>();
        hookNodeRelationshipsOutbound.put(
                "tie-up",
                new Edge<>(
                        2,
                        "tie-up",
                        new HashMap<>(), hookNode,
                        newTieUpNode(hookNode)
                )
        );

        return hookNodeRelationshipsOutbound;
    }

    @Nonnull
    public static Node<String, String> newTieUpNode(
            @Nonnull final Node<String, String> hookNode
    ) {
        final Node<String, String> tieUpNode = newEndNode(4, "Tie-Up");
        tieUpNode.setRelationshipsInbound(
                newTieUpNodeRelationshipsInbound(tieUpNode, hookNode)
        );

        return tieUpNode;
    }

    @Nonnull
    private static Map<String, Edge<String, String>> newTieUpNodeRelationshipsInbound(
            @Nonnull final Node<String, String> hookNode,
            @Nonnull final Node<String, String> tieUpNode
    ) {
        final Map<String, Edge<String, String>> tieUpNodeRelationshipsInbound = new HashMap<>();
        tieUpNodeRelationshipsInbound.put(
                "hook",
                new Edge<>(
                        3, "hook", new HashMap<>(), tieUpNode, hookNode
                )
        );

        return tieUpNodeRelationshipsInbound;
    }

}
