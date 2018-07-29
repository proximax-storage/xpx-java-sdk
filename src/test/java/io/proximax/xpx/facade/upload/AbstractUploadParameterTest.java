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

package io.proximax.xpx.facade.upload;

import io.proximax.xpx.exceptions.KeywordsAboveMaxLengthLimitException;
import io.proximax.xpx.exceptions.MetadataAboveMaxLengthLimitException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * The Class AbstractUploadParameterTest.
 */
public class AbstractUploadParameterTest {

    /** The unit under test. */
    private AbstractUploadParameter unitUnderTest;

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        unitUnderTest = new AbstractUploadParameter() {
        };
    }

    /**
     * Fail when keywords exceed maximum length.
     */
    @Test(expected = KeywordsAboveMaxLengthLimitException.class)
    public void failWhenKeywordsExceedMaximumLength() {
        unitUnderTest.setKeywords("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed aliquet nulla ac urna lobortis auctor.");
    }

    /**
     * Should set keywords when within acceptable length.
     */
    @Test
    public void shouldSetKeywordsWhenWithinAcceptableLength() {
        final String expected = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";

        unitUnderTest.setKeywords(expected);

        assertThat(unitUnderTest.getKeywords(), is(expected));
    }

    /**
     * Fail when metadata exceed maximum length.
     */
    @Test(expected = MetadataAboveMaxLengthLimitException.class)
    public void failWhenMetadataExceedMaximumLength() {
        final Map<String, String> metadataMap = new HashMap<>();
        metadataMap.put("key1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        metadataMap.put("key2", "Sed aliquet nulla ac urna lobortis auctor.");
        metadataMap.put("key3", "Nulla posuere blandit elementum.");
        metadataMap.put("key4", "Nam felis neque, vehicula et tincidunt in, varius sit amet lacus.");
        metadataMap.put("key5", "Praesent sit amet quam consequat, dapibus tortor ac, cursus nisi.");
        metadataMap.put("key6", "Suspendisse nec velit vestibulum, scelerisque magna et, dictum sem.");
        metadataMap.put("key7", "Nam scelerisque rutrum libero, non volutpat ipsum iaculis at.");
        metadataMap.put("key8", "Nulla pulvinar enim in dapibus dictum.");

        unitUnderTest.setMetaData(metadataMap);    }

    /**
     * Should set metadata when within acceptable length.
     */
    @Test
    public void shouldSetMetadataWhenWithinAcceptableLength() {
        final String expected = "{\"key1\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\",\"key2\":\"Sed aliquet nulla ac urna lobortis auctor.\",\"key5\":\"Praesent sit amet quam consequat, dapibus tortor ac, cursus nisi.\",\"key6\":\"Suspendisse nec velit vestibulum, scelerisque magna et, dictum sem.\",\"key3\":\"Nulla posuere blandit elementum.\",\"key4\":\"Nam felis neque, vehicula et tincidunt in, varius sit amet lacus.\"}";

        final Map<String, String> metadataMap = new HashMap<>();
        metadataMap.put("key1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        metadataMap.put("key2", "Sed aliquet nulla ac urna lobortis auctor.");
        metadataMap.put("key3", "Nulla posuere blandit elementum.");
        metadataMap.put("key4", "Nam felis neque, vehicula et tincidunt in, varius sit amet lacus.");
        metadataMap.put("key5", "Praesent sit amet quam consequat, dapibus tortor ac, cursus nisi.");
        metadataMap.put("key6", "Suspendisse nec velit vestibulum, scelerisque magna et, dictum sem.");

        unitUnderTest.setMetaData(metadataMap);

        assertThat(unitUnderTest.getMetaDataAsString(), is(expected));
    }
}
