package io.nem.xpx.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ParameterValidationUtilsTest {

    @Test
    public void shouldThrowExceptionWhenNotValid() {
        try {
            ParameterValidationUtils.checkParameter(false, "INVALID");
            fail("should have thrown exception");
        } catch (Exception e) {
            assertEquals(IllegalArgumentException.class, e.getClass());
            assertEquals("INVALID", e.getMessage());
        }
    }

    @Test
    public void shouldDoNothingWhenValid() {
        ParameterValidationUtils.checkParameter(true, "INVALID");
    }
}
