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

package io.proximax.xpx.facade.upload.remote;

import io.proximax.xpx.facade.upload.Upload;
import io.proximax.xpx.facade.upload.UploadException;
import io.proximax.xpx.facade.upload.UploadPathParameter;
import org.junit.Before;
import org.junit.Test;

import static io.proximax.xpx.testsupport.Constants.*;



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
    @Test(expected = UploadException.class)
    public void failBecauseNotSupported() throws Exception{
        UploadPathParameter parameter = UploadPathParameter.create()
                .senderPrivateKey(TEST_PRIVATE_KEY)
                .receiverPublicKey(TEST_PUBLIC_KEY)
                .path("src//test//resources//")
                .build();

        unitUnderTest.uploadPath(parameter);

    }

}
