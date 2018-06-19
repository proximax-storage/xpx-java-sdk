package io.nem.xpx.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.URL;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class IpfsGatewaySyncServiceTest {

    private String SAMPLE_GATEWAY = "https://ipfs.io/ipfs";
    private String SAMPLE_DATA_HASH = "abcdef123456789";

    @Mock
    private HttpClient mockHttpClient;

    @Captor
    private ArgumentCaptor<URL> urlArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldDoNothingWhenNoGateway() throws Exception {
        doNothing().when(mockHttpClient).head(any());

        new IpfsGatewaySyncService(emptyList(), mockHttpClient).syncToGatewaysAsynchronously(SAMPLE_DATA_HASH);

        Thread.sleep(200L);
        verify(mockHttpClient, times(0)).head(any());
    }

    @Test
    public void shouldCallHttpHeadForDataHash() throws Exception {
        doNothing().when(mockHttpClient).head(urlArgumentCaptor.capture());

        new IpfsGatewaySyncService(asList(SAMPLE_GATEWAY), mockHttpClient).syncToGatewaysAsynchronously(SAMPLE_DATA_HASH);

        Thread.sleep(200L);
        assertThat(urlArgumentCaptor.getValue().toString(), is(SAMPLE_GATEWAY + "/ipfs/" + SAMPLE_DATA_HASH));

    }
}
