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

package io.proximax.xpx.facade;

import io.proximax.xpx.facade.connection.PeerConnection;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.proximax.xpx.testsupport.Constants.LOCAL_HTTP_PEER_CONNECTION;
import static io.proximax.xpx.testsupport.Constants.REMOTE_PEER_CONNECTION;


/**
 * The Class AbstractFacadeIntegrationTest.
 */
@RunWith(Parameterized.class)
public abstract class AbstractFacadeIntegrationTest {

    /** The Constant LOGGER. */
    public static final Logger LOGGER = Logger.getAnonymousLogger();

    /** The peer connection. */
    @Parameter
    public PeerConnection peerConnection;

    /**
     * Data.
     *
     * @return the {@link Iterable}
     */
    @Parameters(name = "{index}: {0}")
    public static Iterable<? extends Object> data() {
        return Stream.of(REMOTE_PEER_CONNECTION, LOCAL_HTTP_PEER_CONNECTION)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * Log peer connection used.
     */
    @Before
    public void logPeerConnectionUsed() {
        LOGGER.info("Testing with " + peerConnection.getClass());
    }
}
