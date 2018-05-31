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

public class DownloadFileResultTest {

    @Mock
    private ResourceHashMessage resourceHashMessage;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSaveFile() throws IOException {
        final byte[] testData = "test string data".getBytes();
        final File tempFile = File.createTempFile("tmp" + System.currentTimeMillis(), "tmp");

        final DownloadFileResult unitUnderTest = DownloadFileResult.fromDownloadResult(
                new DownloadResult(null, testData, NemMessageType.PLAIN));

        unitUnderTest.saveToFile(tempFile);
        final byte[] downloadedFileData = FileUtils.readFileToByteArray(tempFile);

        assertArrayEquals(testData, downloadedFileData);
    }

    @Test
    public void shouldReturnTypeOfResourceMessageHashAsContentType() throws IOException {
        given(resourceHashMessage.type()).willReturn("application/pdf");

        final DownloadFileResult unitUnderTest = DownloadFileResult.fromDownloadResult(
                new DownloadResult(resourceHashMessage, null, NemMessageType.PLAIN));

        final String contentType = unitUnderTest.getContentType();

        assertEquals("application/pdf", contentType);
    }

    @Test
    public void shouldReturnNameOfResourceMessageHashAsFileName() throws IOException {
        given(resourceHashMessage.name()).willReturn("johndoe.txt");

        final DownloadFileResult unitUnderTest = DownloadFileResult.fromDownloadResult(
                new DownloadResult(resourceHashMessage, null, NemMessageType.PLAIN));

        final String fileName = unitUnderTest.getFileName();

        assertEquals("johndoe.txt", fileName);
    }

}
