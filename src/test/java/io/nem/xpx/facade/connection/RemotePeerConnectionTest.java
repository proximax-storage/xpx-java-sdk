package io.nem.xpx.facade.connection;

import io.nem.xpx.model.NodeInfo;
import io.nem.xpx.service.remote.RemoteNodeApi;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

public class RemotePeerConnectionTest {

    public static final String SAMPLE_REMOTE_BASE_URL = "http://dev-gateway.internal.proximax.io:8881";
    public static final String SAMPLE_SYNC_GATEWAY_1 = "https://ipfs.io/ipfs/";
    public static final String SAMPLE_SYNC_GATEWAY_2 = "https://gateway.ipfs.io/ipfs/";
    public static final String SAMPLE_SYNC_GATEWAY_USER_PROVIDED_1 = "https://test.ipfs.io/ipfs/";
    public static final String SAMPLE_SYNC_GATEWAY_USER_PROVIDED_2 = "https://dev.ipfs.io/ipfs/";
    public static final String SAMPLE_NEM_NETWORK = "testnet";
    public static final String SAMPLE_NEM_NETWORK_ADDRESS = "23.228.67.85";
    public static final String SAMPLE_NEM_NETWORK_PORT = "7890";

    @Mock
    private RemoteNodeApi mockRemoteNodeApi;

    @Mock
    private NodeInfo mockNodeInfo;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPopulateSyncGatewaysFromPlatform() throws Exception{
        given(mockRemoteNodeApi.getNodeInfoUsingGET()).willReturn(mockNodeInfo);
        given(mockNodeInfo.getNetwork()).willReturn(SAMPLE_NEM_NETWORK);
        given(mockNodeInfo.getNetworkAddress()).willReturn(SAMPLE_NEM_NETWORK_ADDRESS);
        given(mockNodeInfo.getNetworkPort()).willReturn("7890");
        given(mockNodeInfo.getSyncGateways()).willReturn(asList(SAMPLE_SYNC_GATEWAY_1, SAMPLE_SYNC_GATEWAY_2));

        final RemotePeerConnection unitUnderTest = new RemotePeerConnection(SAMPLE_REMOTE_BASE_URL, null, mockRemoteNodeApi);

        assertThat(unitUnderTest.getSyncGateways(), is(notNullValue()));
        assertThat(unitUnderTest.getSyncGateways().size(), is(2));
        assertThat(unitUnderTest.getSyncGateways().get(0), is(SAMPLE_SYNC_GATEWAY_1));
        assertThat(unitUnderTest.getSyncGateways().get(1), is(SAMPLE_SYNC_GATEWAY_2));
    }

    @Test
    public void shouldPopulateSyncGatewaysFromUserProvided() throws Exception{
        given(mockRemoteNodeApi.getNodeInfoUsingGET()).willReturn(mockNodeInfo);
        given(mockNodeInfo.getNetwork()).willReturn(SAMPLE_NEM_NETWORK);
        given(mockNodeInfo.getNetworkAddress()).willReturn(SAMPLE_NEM_NETWORK_ADDRESS);
        given(mockNodeInfo.getNetworkPort()).willReturn(SAMPLE_NEM_NETWORK_PORT);
        given(mockNodeInfo.getSyncGateways()).willReturn(Collections.emptyList());

        final RemotePeerConnection unitUnderTest = new RemotePeerConnection(
                SAMPLE_REMOTE_BASE_URL, asList(SAMPLE_SYNC_GATEWAY_USER_PROVIDED_1, SAMPLE_SYNC_GATEWAY_USER_PROVIDED_2),
                mockRemoteNodeApi);

        assertThat(unitUnderTest.getSyncGateways(), is(notNullValue()));
        assertThat(unitUnderTest.getSyncGateways().size(), is(2));
        assertThat(unitUnderTest.getSyncGateways().get(0), is(SAMPLE_SYNC_GATEWAY_USER_PROVIDED_1));
        assertThat(unitUnderTest.getSyncGateways().get(1), is(SAMPLE_SYNC_GATEWAY_USER_PROVIDED_2));
    }

    @Test
    public void shouldPopulateSyncGatewaysFromBothPlatformAndUserProvided() throws Exception{
        given(mockRemoteNodeApi.getNodeInfoUsingGET()).willReturn(mockNodeInfo);
        given(mockNodeInfo.getNetwork()).willReturn(SAMPLE_NEM_NETWORK);
        given(mockNodeInfo.getNetworkAddress()).willReturn(SAMPLE_NEM_NETWORK_ADDRESS);
        given(mockNodeInfo.getNetworkPort()).willReturn("7890");
        given(mockNodeInfo.getSyncGateways()).willReturn(asList(SAMPLE_SYNC_GATEWAY_1, SAMPLE_SYNC_GATEWAY_2));

        final RemotePeerConnection unitUnderTest = new RemotePeerConnection(
                SAMPLE_REMOTE_BASE_URL, asList(SAMPLE_SYNC_GATEWAY_USER_PROVIDED_1, SAMPLE_SYNC_GATEWAY_USER_PROVIDED_2),
                mockRemoteNodeApi);

        assertThat(unitUnderTest.getSyncGateways(), is(notNullValue()));
        assertThat(unitUnderTest.getSyncGateways().size(), is(4));
        assertThat(unitUnderTest.getSyncGateways().get(0), is(SAMPLE_SYNC_GATEWAY_USER_PROVIDED_1));
        assertThat(unitUnderTest.getSyncGateways().get(1), is(SAMPLE_SYNC_GATEWAY_USER_PROVIDED_2));
        assertThat(unitUnderTest.getSyncGateways().get(2), is(SAMPLE_SYNC_GATEWAY_1));
        assertThat(unitUnderTest.getSyncGateways().get(3), is(SAMPLE_SYNC_GATEWAY_2));
    }

}
