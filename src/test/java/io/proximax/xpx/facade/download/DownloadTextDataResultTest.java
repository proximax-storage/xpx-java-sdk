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

package io.proximax.xpx.facade.download;

import io.proximax.xpx.model.NemMessageType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


/**
 * The Class DownloadTextDataResultTest.
 */
public class DownloadTextDataResultTest {

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Should encode data to string as utf 8 by default.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Test
    public void shouldEncodeDataToStringAsUtf8ByDefault() throws IOException {
        final String testString = "The quick brown fox jumps over the lazy dog";
        final DownloadTextDataResult unitUnderTest = DownloadTextDataResult.fromDownloadResult(
                new DownloadResult(null, testString.getBytes(), NemMessageType.PLAIN));

        assertEquals(testString, unitUnderTest.getString());
    }

}
