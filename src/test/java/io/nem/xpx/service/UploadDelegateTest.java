package io.nem.xpx.service;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import io.nem.xpx.service.UploadDelegate.ResourceHashMessageWrapper;
import io.nem.xpx.service.intf.UploadApi;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

public class UploadDelegateTest {

    public static final byte[] SAMPLE_DATA = "The quick brown".getBytes();
    public static final String SAMPLE_NAME = "test string";
    public static final String SAMPLE_CONTENT_TYPE = "text/plain";
    public static final String SAMPLE_ENCODING = "UTF-8";
    public static final String SAMPLE_KEYWORDS = "test keywords";
    public static final String SAMPLE_METADATA = "metadata";
    public static final String SAMPLE_PATH = "/path";
    public static final String SAMPLE_MULTIHASH = "ABCDEF123456789";

    private UploadDelegate unitUnderTest;

    @Mock
    private UploadApi mockUploadApi;

    @Captor
    private ArgumentCaptor<String> multihashArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        unitUnderTest = new UploadDelegate(mockUploadApi);
    }

    @Test
    public void shouldDelegateTextUpload() throws Exception {
        final byte[] sampleHashMessageBytes = Base64.decode("GAAAABQAKAAkACAAHAAYABQAAAAIAAQAFAAAACQAAAAY18kQZAEAAAAAAAAkAAAANAAAADgAAAA8AAAAbAAAA" +
                "AkAAABpbWFnZS9wbmcAAAANAAAAYmxhY2tfb3BzLnBuZwAAAAIAAAAiIgAAAAAAAAAAAAAuAAAAUW1iSmJNa0dRTG9WM0VWdGJpZ" +
                "UZSOThQU1ZOQ0FuSHhDak1tWjh4Z042bko4YwAAQAAAADhiYTM5YTdhZTg4ZWM0NTBiMmVjYmNjMTRkZWQ3OWUzODc0NjU1NWMyMW" +
                "JhZWI5YTAyYmM3ODUxMzk5NDM0YWEAAAAA");
        given(mockUploadApi.uploadText(SAMPLE_DATA, SAMPLE_NAME, SAMPLE_CONTENT_TYPE,
                SAMPLE_ENCODING, SAMPLE_KEYWORDS, SAMPLE_METADATA)).willReturn(sampleHashMessageBytes);

        final ResourceHashMessageWrapper result = unitUnderTest.uploadTextToIpfs(SAMPLE_DATA, SAMPLE_NAME, SAMPLE_CONTENT_TYPE,
                SAMPLE_ENCODING, SAMPLE_KEYWORDS, SAMPLE_METADATA);

        assertThat(result.getData(), is(sampleHashMessageBytes));
    }

    @Test
    public void shouldDelegateBinaryUpload() throws Exception {
        final byte[] sampleHashMessageBytes = Base64.decode("GAAAABQAKAAkACAAHAAYABQAAAAIAAQAFAAAACQAAAAY18kQZAEAAAAAAAAkAAAANAAAADgAAAA8AAAAbAAAA" +
                "AkAAABpbWFnZS9wbmcAAAANAAAAYmxhY2tfb3BzLnBuZwAAAAIAAAAiIgAAAAAAAAAAAAAuAAAAUW1iSmJNa0dRTG9WM0VWdGJpZ" +
                "UZSOThQU1ZOQ0FuSHhDak1tWjh4Z042bko4YwAAQAAAADhiYTM5YTdhZTg4ZWM0NTBiMmVjYmNjMTRkZWQ3OWUzODc0NjU1NWMyMW" +
                "JhZWI5YTAyYmM3ODUxMzk5NDM0YWEAAAAA");
        given(mockUploadApi.uploadBytesBinary(SAMPLE_DATA, SAMPLE_NAME, SAMPLE_CONTENT_TYPE,
                SAMPLE_KEYWORDS, SAMPLE_METADATA)).willReturn(sampleHashMessageBytes);

        final ResourceHashMessageWrapper result = unitUnderTest.uploadBinaryToIpfs(SAMPLE_DATA, SAMPLE_NAME, SAMPLE_CONTENT_TYPE,
                SAMPLE_KEYWORDS, SAMPLE_METADATA);

        assertThat(result.getData(), is(sampleHashMessageBytes));
    }

    @Test
    public void shouldDelegatePathUpload() throws Exception {
        final byte[] sampleHashMessageBytes = Base64.decode("GAAAABQAKAAkACAAHAAYABQAAAAIAAQAFAAAACQAAAAY18kQZAEAAAAAAAAkAAAANAAAADgAAAA8AAAAbAAAA" +
                "AkAAABpbWFnZS9wbmcAAAANAAAAYmxhY2tfb3BzLnBuZwAAAAIAAAAiIgAAAAAAAAAAAAAuAAAAUW1iSmJNa0dRTG9WM0VWdGJpZ" +
                "UZSOThQU1ZOQ0FuSHhDak1tWjh4Z042bko4YwAAQAAAADhiYTM5YTdhZTg4ZWM0NTBiMmVjYmNjMTRkZWQ3OWUzODc0NjU1NWMyMW" +
                "JhZWI5YTAyYmM3ODUxMzk5NDM0YWEAAAAA");
        given(mockUploadApi.uploadPath(SAMPLE_PATH, SAMPLE_NAME,
                SAMPLE_KEYWORDS, SAMPLE_METADATA)).willReturn(sampleHashMessageBytes);

        final ResourceHashMessageWrapper result = unitUnderTest.uploadPathToIpfs(SAMPLE_PATH, SAMPLE_NAME,
                SAMPLE_KEYWORDS, SAMPLE_METADATA);

        assertThat(result.getData(), is(sampleHashMessageBytes));
    }

    @Test
    public void shouldDelegateDeletePinnedUpload() throws Exception {
        doNothing().when(mockUploadApi).deletePinnedContent(multihashArgumentCaptor.capture());

        unitUnderTest.deletePinnedContent(SAMPLE_MULTIHASH);

        assertThat(multihashArgumentCaptor.getValue(), is(SAMPLE_MULTIHASH));
    }
}
