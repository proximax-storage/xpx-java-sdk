package io.nem.xpx.facade;

import io.nem.xpx.facade.connection.PeerConnection;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.logging.Logger;

import static io.nem.xpx.testsupport.Constants.LOCAL_HTTP_PEER_CONNECTION;
import static io.nem.xpx.testsupport.Constants.REMOTE_PEER_CONNECTION;

@RunWith(Parameterized.class)
public abstract class AbstractFacadeIntegrationTest {

    public static final Logger LOGGER = Logger.getAnonymousLogger();

    @Parameter
    public PeerConnection peerConnection;

    @Parameters(name = "{index}: {0}")
    public static Iterable<? extends Object> data() {
        return Arrays.asList(REMOTE_PEER_CONNECTION, LOCAL_HTTP_PEER_CONNECTION);
    }

    @Before
    public void logPeerConnectionUsed() {
        LOGGER.info("Testing with " + peerConnection.getClass());
    }
}
