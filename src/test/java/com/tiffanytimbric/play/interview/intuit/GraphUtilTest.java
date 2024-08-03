package com.tiffanytimbric.play.interview.intuit;

import com.tiffanytimbric.play.interview.intuit.songs.JingleUtil;
import com.tiffanytimbric.play.interview.intuit.songs.MuzakUtil;
import junit.framework.TestCase;

import javax.annotation.Nonnull;

public class GraphUtilTest extends TestCase {

    public void testNewAcyclicGraph() {
        final Graph<String, String, String> acyclicGraph = newAcyclicGraph();

        assertNotNull(acyclicGraph);
    }

    public void testCyclicGraph() {
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
        return JingleUtil.newMicroJingleGraph();
    }

    @Nonnull
    public static Graph<String, String, String> newCyclicGraph() {
        return MuzakUtil.newMuzak();
    }

}
