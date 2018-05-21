package io.nem.xpx.facade.upload.remote;

import io.nem.xpx.exceptions.PathUploadNotSupportedException;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.facade.upload.UploadPathParameter;
import io.nem.xpx.integration.tests.IntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static io.nem.xpx.testsupport.Constants.*;


@Category(IntegrationTest.class)
public class Upload_uploadPathRemoteIntegrationTest {

    private Upload unitUnderTest;

    @Before
    public void setUp() {
        unitUnderTest = new Upload(REMOTE_PEER_CONNECTION);
    }


    @Test(expected = PathUploadNotSupportedException.class)
    public void failBecauseNotSupported() throws Exception{
        UploadPathParameter parameter = UploadPathParameter.create()
                .senderOrReceiverPrivateKey(TEST_PRIVATE_KEY_2)
                .receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
                .path("src//test//resources//")
                .build();

        unitUnderTest.uploadPath(parameter);

    }

}
