package com.tiffanytimbric.play.hello;


import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class HelloWorldFunctionTest {

    @Test
    public void testApply_Success() {
        final ResultStatus resultStatus = new HelloWorldFunction().apply(List.of("Tiffany"));

        assertNotNull(
                "The result status was null.",
                resultStatus
        );
        assertEquals(
                "The result status was non-success.",
                ResultStatus.Success, resultStatus
        );
        assertEquals(
                "Success", resultStatus.getMessage()
        );
    }

    @Test
    public void testApply_Failure() {
        final ResultStatus resultStatus = new HelloWorldFunction().apply(List.of(""));

        assertNotNull(
                "The result status was null.",
                resultStatus
        );
        assertEquals(
                "The result status was non-success.",
                ResultStatus.Failure, resultStatus
        );
        assertEquals(
                "Failure: At least one argument must be provided.",
                resultStatus.getMessage()
        );
    }

}