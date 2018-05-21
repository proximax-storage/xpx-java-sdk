package io.nem.xpx.facade;

import io.nem.xpx.facade.connection.PeerConnection;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.nem.xpx.testsupport.Constants.LOCAL_HTTP_PEER_CONNECTION;
import static io.nem.xpx.testsupport.Constants.REMOTE_PEER_CONNECTION;

@RunWith(Parameterized.class)
public abstract class AbstractFacadeIntegrationTest {

    public static final Logger LOGGER = Logger.getAnonymousLogger();

    @Parameter
    public PeerConnection peerConnection;

    @Parameters(name = "{index}: {0}")
    public static Iterable<? extends Object> data() {
        return Stream.of(REMOTE_PEER_CONNECTION, LOCAL_HTTP_PEER_CONNECTION)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Before
    public void logPeerConnectionUsed() {
        LOGGER.info("Testing with " + peerConnection.getClass());
    }
}
