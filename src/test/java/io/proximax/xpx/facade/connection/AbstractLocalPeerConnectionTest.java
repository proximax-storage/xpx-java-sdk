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

package io.proximax.xpx.facade.connection;

import io.ipfs.api.IPFS;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.nem.core.node.NodeEndpoint;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class AbstractLocalPeerConnectionTest {

    public static final String SAMPLE_SYNC_GATEWAY_USER_PROVIDED_1 = "https://test.ipfs.io/ipfs/";
    public static final String SAMPLE_SYNC_GATEWAY_USER_PROVIDED_2 = "https://dev.ipfs.io/ipfs/";

    @Mock
    private NodeEndpoint mockNodeEndpoint;

    @Mock
    private IPFS mockIpfs;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldHaveNoSyncGatewaysByDefault() {

        final AbstractLocalPeerConnection unitUnderTest =
                new AbstractLocalPeerConnection(mockNodeEndpoint, mockIpfs, null) {};

        assertThat(unitUnderTest.getSyncGateways(), is(notNullValue()));
        assertThat(unitUnderTest.getSyncGateways().size(), is(0));
    }

    @Test
    public void shouldPopulateSyncGatewaysFromUserProvided() {
        final AbstractLocalPeerConnection unitUnderTest =
                new AbstractLocalPeerConnection(mockNodeEndpoint, mockIpfs,
                        asList(SAMPLE_SYNC_GATEWAY_USER_PROVIDED_1, SAMPLE_SYNC_GATEWAY_USER_PROVIDED_2)) {};

        assertThat(unitUnderTest.getSyncGateways(), is(notNullValue()));
        assertThat(unitUnderTest.getSyncGateways().size(), is(2));
        assertThat(unitUnderTest.getSyncGateways().get(0), is(SAMPLE_SYNC_GATEWAY_USER_PROVIDED_1));
        assertThat(unitUnderTest.getSyncGateways().get(1), is(SAMPLE_SYNC_GATEWAY_USER_PROVIDED_2));
    }
}
