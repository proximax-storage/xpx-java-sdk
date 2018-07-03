package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.nem.xpx.testsupport.Constants.*;
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

        LOGGER.info("Plain Privacy Strategy uploaded nem hash: " + uploadResult.getNemHash());
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

        LOGGER.info("Secured With NEM Keys Privacy Strategy uploaded nem hash: " + uploadResult.getNemHash());
    }

    @Test
    public void shouldUploadWithSecuredWithSenderNemKeysPrivacyStrategy() throws Exception{

        UploadBinaryParameter parameter = UploadBinaryParameter.create()
                .senderPrivateKey(TEST_PRIVATE_KEY)
                .receiverPublicKey(TEST_PUBLIC_KEY_2)
                .data(FileUtils.readFileToByteArray(PDF_FILE1))
                .securedWithSenderNemKeysPrivacyStrategy()
                .build();

        final UploadResult uploadResult = unitUnderTest.uploadBinary(parameter);

        assertThat(uploadResult, is(notNullValue()));
        assertThat(uploadResult.getNemHash(), is(notNullValue()));
        assertThat(uploadResult.getDataMessage(), is(notNullValue()));
        assertThat(uploadResult.getDataMessage().hash(), is(notNullValue()));
        assertThat(uploadResult.getDataMessage().digest(), is(notNullValue()));

        LOGGER.info("Secured With Sender NEM Keys Privacy Strategy uploaded nem hash: " + uploadResult.getNemHash());
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

        LOGGER.info("Secured with Password Privacy Strategy uploaded nem hash: " + uploadResult.getNemHash());
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

        LOGGER.info("Secured with Shamir Secret Sharing Privacy Strategy uploaded nem hash: " + uploadResult.getNemHash());
    }
}
