/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.utils;

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
