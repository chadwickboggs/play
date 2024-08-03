package com.tiffanytimbric.play.interview.intuit;

import com.tiffanytimbric.play.interview.intuit.songs.JingleUtil;
import com.tiffanytimbric.play.interview.intuit.songs.MuzakUtil;
import junit.framework.TestCase;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class GraphUtilTest extends TestCase {

    public void testNewBidirectionalAcyclicGraph() {
        final Graph<String, String, String> acyclicGraph = newAcyclicGraph();

        assertNotNull(acyclicGraph);
    }

    public void testNewDirectedCyclicGraph() {
        final Graph<String, String, String> cyclicGraph = newCyclicGraph();

        assertNotNull(cyclicGraph);
    }

    public void testRemoveDuplicates() {
        // TODO: Implement.
    }

    public void testRemoveCycles() {
        // TODO: Implement.
    }

    @Nonnull
    public static Graph<String, String, String> newAcyclicGraph() {
        return JingleUtil.newMicroJingleGraph(
                "Intuit",
                "Standard radio jingle for Intuit.",
                new HashMap<>()
        );
    }

    @Nonnull
    public static Graph<String, String, String> newCyclicGraph() {
        return MuzakUtil.newMuzak(
                "Flow",
                "Standard lobby music for Intuit.",
                new HashMap<>()
        );
    }

}
