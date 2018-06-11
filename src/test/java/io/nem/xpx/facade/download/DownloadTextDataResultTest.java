package io.nem.xpx.facade.download;

import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;


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