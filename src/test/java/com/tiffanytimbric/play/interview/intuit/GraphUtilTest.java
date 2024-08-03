package com.tiffanytimbric.play.interview.intuit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.tiffanytimbric.play.interview.intuit.songs.JingleUtil;
import com.tiffanytimbric.play.interview.intuit.songs.MuzakUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Nonnull;
import java.util.HashMap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GraphUtilTest {

    @Test
    public void testNewBidirectionalAcyclicGraph() {
        final Graph<String, String, String> graph = newAcyclicGraph();

        assertNotNull(graph);
    }

    @Test
    public void testToJsonOfNewDirectedCyclicGraph() throws JsonProcessingException {
        final Graph<String, String, String> graph = newAcyclicGraph();

        testToJsonOfGraph(graph);
    }

    @Test
    public void testNewBidirectionalCyclicGraph() {
        final Graph<String, String, String> graph = newCyclicGraph();

        assertNotNull(graph);
    }

    /*
     This test is ignored because the cycle will cause JSON generation to
     produce an infinite recursion.
    */
    @Ignore
    @Test
    public void testToJsonOfNewBidirectionalCyclicGraph() throws JsonProcessingException {
        final Graph<String, String, String> graph = newCyclicGraph();

        testToJsonOfGraph(graph);
    }

    @Test
    public void testRemoveDuplicates() {
        // TODO: Implement.
    }

    @Test
    public void testRemoveCycles() {
        // TODO: Implement.
    }

    private static void testToJsonOfGraph(
            @Nonnull final Graph<String, String, String> graph
    ) throws JsonProcessingException {
        assertNotNull(graph);

        final String jsonValue = new JsonMapper().writeValueAsString(graph);

        assertTrue(StringUtils.isNoneBlank(jsonValue));

        System.out.println(jsonValue);
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
