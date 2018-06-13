package io.nem.xpx.facade.upload.remote;

import io.nem.xpx.exceptions.PathUploadNotSupportedException;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.facade.upload.UploadPathParameter;
import org.junit.Before;
import org.junit.Test;

import static io.nem.xpx.testsupport.Constants.*;



/**
 * The Class Upload_uploadPathRemoteIntegrationTest.
 */
public class Upload_uploadPathRemoteIntegrationTest {

    /** The unit under test. */
    private Upload unitUnderTest;

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        unitUnderTest = new Upload(REMOTE_PEER_CONNECTION);
    }


    /**
     * Fail because not supported.
     *
     * @throws Exception the exception
     */
    @Test(expected = PathUploadNotSupportedException.class)
    public void failBecauseNotSupported() throws Exception{
        UploadPathParameter parameter = UploadPathParameter.create()
                .senderPrivateKey(TEST_PRIVATE_KEY)
                .receiverPublicKey(TEST_PUBLIC_KEY)
                .path("src//test//resources//")
                .build();

        unitUnderTest.uploadPath(parameter);

    }

}
