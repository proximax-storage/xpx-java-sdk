package io.nem.xpx.facade.upload.remote;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import org.junit.Assert;
import org.junit.Test;
import org.nem.core.model.FeeUnitAwareTransactionFeeCalculator;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicFeeInformationLookup;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.mosaic.MosaicFeeInformation;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.model.primitive.Supply;
import org.nem.core.node.NodeEndpoint;
import io.nem.ApiException;
import io.nem.xpx.AbstractApiTest;
import io.nem.xpx.builder.UploadDataParameterBuilder;
import io.nem.xpx.builder.UploadFileParameterBuilder;
import io.nem.xpx.builder.UploadPathParameterBuilder;
import io.nem.xpx.facade.Upload;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.UploadDataParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.UploadFileParameter;
import io.nem.xpx.model.UploadPathParameter;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.utils.JsonUtils;


/**
 * The Class UploadTest.
 */
public class UploadRemotePathTest extends AbstractApiTest {

	/**
	 * The Class TestMonitor.
	 */

	/**
	 * Upload plain data test.
	 */
	@Test
	public void uploadPlainDataTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Upload upload = new Upload(localPeerConnection);

			UploadDataParameter parameter = UploadDataParameterBuilder
					.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey)
					.messageType(MessageTypes.PLAIN)
					.data("hello")
					.metaData(null)
					.keywords("proximax-addresses-eth")
					.build();

			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload plain file test.
	 */
	@Test
	public void uploadPlainFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));
		try {
			Upload upload = new Upload(localPeerConnection);
			UploadFileParameter parameter = UploadFileParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data(new File("src//test//resources//ProximaX-Whitepaper-v1.4.pdf"))
					.metaData(null)
					.keywords("proximax-pdf")
					.contentType("application/pdf") // make sure to put this in for files.
					.build();
			
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload plain large file test.
	 */
	@Test
	public void uploadPlainLargeFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Upload upload = new Upload(localPeerConnection);
			UploadFileParameter parameter = UploadFileParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data(new File("src//test//resources//large_file.zip"))
					.metaData(null).keywords(null).build();
			
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure data test.
	 */
	@Test
	public void uploadSecureDataTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Upload upload = new Upload(localPeerConnection);

			UploadDataParameter parameter = UploadDataParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.SECURE)
					.data("This is a Secure Test Data").metaData(null).keywords(null).build();

			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure file test.
	 */
	@Test
	public void uploadSecureFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Upload upload = new Upload(localPeerConnection);
			UploadFileParameter parameter = UploadFileParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.SECURE).data(new File("src//test//resources//small_file.txt"))
					.metaData(null).keywords(null).build();
			
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure large file test.
	 */
	@Test
	public void uploadSecureLargeFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Upload upload = new Upload(localPeerConnection);
			UploadFileParameter parameter = UploadFileParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.SECURE).data(new File("src//test//resources//large_file.zip"))
					.metaData(null).keywords(null).build();
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload plain data with mosaic test.
	 */
	@Test
	public void uploadPlainDataWithMosaicTest() {
		try {
			LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
					new NodeEndpoint("http", "104.128.226.60", 7890));
			XpxSdkGlobalConstants.setGlobalTransactionFee(
					new FeeUnitAwareTransactionFeeCalculator(Amount.fromMicroNem(50_000L), mosaicInfoLookup()));
			Upload upload = new Upload(localPeerConnection);

			UploadDataParameter parameter = UploadDataParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data("This is a test data from mosaic").metaData(null).keywords(null)
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"),
							Quantity.fromValue(0)))
					.build();

			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	/**
	 * Upload path.
	 */
//	@Test
//	public void uploadPath() {
//		try {
//			LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
//					new NodeEndpoint("http", "104.128.226.60", 7890));
//			XpxSdkGlobalConstants.setGlobalTransactionFee(
//					new FeeUnitAwareTransactionFeeCalculator(Amount.fromMicroNem(50_000L), mosaicInfoLookup()));
//			Upload upload = new Upload(localPeerConnection);
//
//			UploadPathParameter parameter = UploadPathParameterBuilder.senderPrivateKey(this.xPvkey)
//					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
//					.path("D:/Projects/eworkspace/proximaxsdks/xpx-java-sdk/src/test/resources/")
//					.metaData(null).keywords(null)
//					.mosaics(new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"),
//							Quantity.fromValue(0)))
//					.build();
//
//			String nemhash = upload.uploadPath(parameter).getNemHash();
//			LOGGER.info(nemhash);
//			Assert.assertNotNull(nemhash);
//		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}

	/**
	 * Mosaic info lookup.
	 *
	 * @return the mosaic fee information lookup
	 */
	private MosaicFeeInformationLookup mosaicInfoLookup() {
		return id -> {
			if (id.getName().equals("registry")) {
				return new MosaicFeeInformation(Supply.fromValue(8_999_999_999L), 6);
			}
			final int multiplier = Integer.parseInt(id.getName().substring(4));
			final int divisibilityChange = multiplier - 1;
			return new MosaicFeeInformation(Supply.fromValue(100_000_000 * multiplier), 3 + divisibilityChange);
		};
	}


}
