package io.nem.xpx.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * The Class ParameterValidationUtilsTest.
 */
public class ParameterValidationUtilsTest {

    /**
     * Should throw exception when not valid.
     */
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

    /**
     * Should do nothing when valid.
     */
    @Test
    public void shouldDoNothingWhenValid() {
        ParameterValidationUtils.checkParameter(true, "INVALID");
    }
}
