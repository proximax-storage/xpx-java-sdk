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

package io.proximax.xpx.strategy.privacy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * The Class PlainPrivacyStrategyTest.
 */
public class PlainPrivacyStrategyTest {

    /** The Constant INPUT_DATA. */
    public static final byte[] INPUT_DATA = "the quick brown fox jumps over the lazy dog".getBytes();

    /** The unit under test. */
    private PlainPrivacyStrategy unitUnderTest;

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        unitUnderTest = new PlainPrivacyStrategy();
    }

    /**
     * Return same data on encrypt.
     */
    @Test
    public void returnSameDataOnEncrypt() {
        final byte[] encrypted = unitUnderTest.encrypt(INPUT_DATA);

        assertEquals(INPUT_DATA, encrypted);
    }

    /**
     * Return same data on decrypt.
     */
    @Test
    public void returnSameDataOnDecrypt() {
        final byte[] decrypted = unitUnderTest.decrypt(INPUT_DATA, null, null);

        assertEquals(INPUT_DATA, decrypted);
    }
}
