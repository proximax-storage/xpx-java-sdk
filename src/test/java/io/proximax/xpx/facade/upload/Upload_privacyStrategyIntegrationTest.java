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

package io.proximax.xpx.facade.upload;

import io.proximax.xpx.facade.AbstractFacadeIntegrationTest;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.proximax.xpx.testsupport.Constants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class Upload_privacyStrategyIntegrationTest extends AbstractFacadeIntegrationTest {

    private Upload unitUnderTest;

    @Before
    public void setUp() {
        unitUnderTest = new Upload(peerConnection);
    }

    @Test
    public void shouldUploadWithPlainPrivacyStrategy() throws Exception{

        UploadBinaryParameter parameter = UploadBinaryParameter.create()
                .senderPrivateKey(TEST_PRIVATE_KEY)
                .receiverPublicKey(TEST_PUBLIC_KEY)
                .data(FileUtils.readFileToByteArray(PDF_FILE1))
                .plainPrivacy()
                .build();

        final UploadResult uploadResult = unitUnderTest.uploadBinary(parameter);

        assertThat(uploadResult, is(notNullValue()));
        assertThat(uploadResult.getNemHash(), is(notNullValue()));
        assertThat(uploadResult.getDataMessage(), is(notNullValue()));
        assertThat(uploadResult.getDataMessage().hash(), is(notNullValue()));
        assertThat(uploadResult.getDataMessage().digest(), is(notNullValue()));

        LOGGER.info("Plain Privacy Strategy uploaded proximax NEM hash: " + uploadResult.getNemHash());
        LOGGER.info("Plain Privacy Strategy uploaded proximax IPFS hash: " + uploadResult.getIpfsHash());
    }

    @Test
    public void shouldUploadWithSecuredWithNemKeysPrivacyStrategy() throws Exception{

        UploadBinaryParameter parameter = UploadBinaryParameter.create()
                .senderPrivateKey(TEST_PRIVATE_KEY)
                .receiverPublicKey(TEST_PUBLIC_KEY)
                .data(FileUtils.readFileToByteArray(PDF_FILE1))
                .securedWithNemKeysPrivacyStrategy()
                .build();

        final UploadResult uploadResult = unitUnderTest.uploadBinary(parameter);

        assertThat(uploadResult, is(notNullValue()));
        assertThat(uploadResult.getNemHash(), is(notNullValue()));
        assertThat(uploadResult.getDataMessage(), is(notNullValue()));
        assertThat(uploadResult.getDataMessage().hash(), is(notNullValue()));
        assertThat(uploadResult.getDataMessage().digest(), is(notNullValue()));

        LOGGER.info("Secured With NEM Keys Privacy Strategy uploaded proximax NEM hash: " + uploadResult.getNemHash());
        LOGGER.info("Secured With NEM Keys Privacy Strategy uploaded proximax IPFS hash: " + uploadResult.getIpfsHash());
    }

    @Test
    public void shouldUploadWithSecuredWithPasswordPrivacyStrategy() throws Exception{

        UploadBinaryParameter parameter = UploadBinaryParameter.create()
                .senderPrivateKey(TEST_PRIVATE_KEY)
                .receiverPublicKey(TEST_PUBLIC_KEY)
                .data(FileUtils.readFileToByteArray(PDF_FILE1))
                .securedWithPasswordPrivacyStrategy(SECURE_PASSWORD)
                .build();

        final UploadResult uploadResult = unitUnderTest.uploadBinary(parameter);

        assertThat(uploadResult, is(notNullValue()));
        assertThat(uploadResult.getNemHash(), is(notNullValue()));
        assertThat(uploadResult.getDataMessage(), is(notNullValue()));
        assertThat(uploadResult.getDataMessage().hash(), is(notNullValue()));
        assertThat(uploadResult.getDataMessage().digest(), is(notNullValue()));

        LOGGER.info("Secured with Password Privacy Strategy uploaded proximax NEM hash: " + uploadResult.getNemHash());
        LOGGER.info("Secured with Password Privacy Strategy uploaded proximax IPFS hash: " + uploadResult.getIpfsHash());
    }

    @Test
    public void shouldUploadWithSecuredWithShamirSecretSharingPrivacyStrategy() throws Exception{

        final Map<Integer, byte[]> minimumSecretParts = new HashMap<>();
        minimumSecretParts.put(2, SHAMIR_SECRET_PARTS.get(2));
        minimumSecretParts.put(5, SHAMIR_SECRET_PARTS.get(5));
        minimumSecretParts.put(9, SHAMIR_SECRET_PARTS.get(9));

        UploadBinaryParameter parameter = UploadBinaryParameter.create()
                .senderPrivateKey(TEST_PRIVATE_KEY)
                .receiverPublicKey(TEST_PUBLIC_KEY)
                .data(FileUtils.readFileToByteArray(PDF_FILE1))
                .securedWithShamirSecretSharingPrivacyStrategy(SHAMIR_SECRET_TOTAL_PART_COUNT, SHAMIR_SECRET_MINIMUM_PART_COUNT_TO_BUILD,
                        minimumSecretParts)
                .build();

        final UploadResult uploadResult = unitUnderTest.uploadBinary(parameter);

        assertThat(uploadResult, is(notNullValue()));
        assertThat(uploadResult.getNemHash(), is(notNullValue()));
        assertThat(uploadResult.getDataMessage(), is(notNullValue()));
        assertThat(uploadResult.getDataMessage().hash(), is(notNullValue()));
        assertThat(uploadResult.getDataMessage().digest(), is(notNullValue()));

        LOGGER.info("Secured with Shamir Secret Sharing Privacy Strategy uploaded proximax NEM hash: " + uploadResult.getNemHash());
        LOGGER.info("Secured with Shamir Secret Sharing Privacy Strategy uploaded proximax IPFS hash: " + uploadResult.getIpfsHash());
    }
}
