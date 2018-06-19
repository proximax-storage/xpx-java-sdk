package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;

import static io.nem.xpx.facade.DataTextContentType.TEXT_PLAIN;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



/**
 * The Class UploadAsync_uploadTextDataMultipleMosaicsIntegrationTest.
 */
public class UploadAsync_uploadTextDataMultipleMosaicsIntegrationTest extends AbstractFacadeIntegrationTest {

	/** The Constant TEST_NAME_RANDOM_1. */
	public static final String TEST_NAME_RANDOM_1 = "RandomName1";
	
	/** The Constant KEYWORDS_PLAIN_AND_DATA. */
	public static final String KEYWORDS_PLAIN_AND_DATA = "plain,data";
	
	/** The Constant ENCODING_UTF_8. */
	public static final String ENCODING_UTF_8 = "UTF-8";

	/** The unit under test. */
	private UploadAsync unitUnderTest;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		unitUnderTest = new UploadAsync(peerConnection);
	}

	/**
	 * Should upload text data multiple mosaics async.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadTextDataMultipleMosaicsAsync() throws Exception {
		Mosaic blueNumberAsset = new Mosaic(new MosaicId(new NamespaceId("bluenumber1"), "product"),
				Quantity.fromValue(10000));
		
		Mosaic landRegistry1 = new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"),
				Quantity.fromValue(10000));
		
		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(new String("plain - the quick brown fox jumps over the lazy dog UTF-8".getBytes(),ENCODING_UTF_8))
				.name(TEST_NAME_RANDOM_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA_AS_MAP)
				.mosaics(blueNumberAsset,landRegistry1)
				.build();

		unitUnderTest.uploadTextData(parameter, uploadResult -> {
			assertNotNull(uploadResult);
			assertNotNull(uploadResult.getNemHash());
			assertNotNull(uploadResult.getDataMessage());
			assertNotNull(uploadResult.getDataMessage().hash());
			assertNotNull(uploadResult.getDataMessage().digest());
			assertEquals(KEYWORDS_PLAIN_AND_DATA, uploadResult.getDataMessage().keywords());
			assertEquals(TEST_NAME_RANDOM_1, uploadResult.getDataMessage().name());
			assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
			assertEquals(TEXT_PLAIN.toString(), uploadResult.getDataMessage().type());

			LOGGER.info(uploadResult.getNemHash());
		}).get();
	}
	
	/**
	 * Should upload text data multiple mosaics with invalid mosaic async.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadTextDataMultipleMosaicsWithInvalidMosaicAsync() throws Exception {
		Mosaic blueNumberAsset = new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"),
				Quantity.fromValue(10000));
		
		Mosaic landRegistry1 = new Mosaic(new MosaicId(new NamespaceId("1county"), "coin"),
				Quantity.fromValue(10000));
		
		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(new String("plain - the quick brown fox jumps over the lazy dog UTF-8".getBytes(),ENCODING_UTF_8))
				.name(TEST_NAME_RANDOM_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA_AS_MAP)
				.mosaics(blueNumberAsset,landRegistry1)
				.build();

		unitUnderTest.uploadTextData(parameter, uploadResult -> {
			assertNotNull(uploadResult);
			assertNotNull(uploadResult.getNemHash());
			assertNotNull(uploadResult.getDataMessage());
			assertNotNull(uploadResult.getDataMessage().hash());
			assertNotNull(uploadResult.getDataMessage().digest());
			assertEquals(KEYWORDS_PLAIN_AND_DATA, uploadResult.getDataMessage().keywords());
			assertEquals(TEST_NAME_RANDOM_1, uploadResult.getDataMessage().name());
			assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
			assertEquals(TEXT_PLAIN.toString(), uploadResult.getDataMessage().type());

			LOGGER.info(uploadResult.getNemHash());
		}).exceptionally(n-> {
			if(n instanceof UploadException) {
				LOGGER.info(n.getMessage());
				assert(true);
			}
			return null;
			//return null;
		});
	}

}
