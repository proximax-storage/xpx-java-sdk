package io.nem.xpx.facade;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
import io.nem.xpx.facade.Upload;
import io.nem.xpx.facade.connection.LocalPeerConnection;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.XpxSdkGlobalConstants;

/**
 * The Class UploadTest.
 */
public class UploadLocalTest extends AbstractApiTest {

	/**
	 * Upload plain data test.
	 */
	@Test
	public void uploadPlainDataTest() {
		LocalPeerConnection localPeerConnection = new LocalPeerConnection(new NodeEndpoint("http", "104.128.226.60", 7890));
		
		try {
			Upload upload = new Upload(localPeerConnection);
			String nemhash = upload.uploadData(MessageTypes.PLAIN, this.xPvkey, this.xPubkey, "This is a test data",
					null, "alvinreyes", null).getNemHash();
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
		LocalPeerConnection localPeerConnection = new LocalPeerConnection(new NodeEndpoint("http", "104.128.226.60", 7890));
		
		try {
			Upload upload = new Upload(localPeerConnection);
			String nemhash = upload.uploadFile(MessageTypes.PLAIN, this.xPvkey, this.xPubkey,
					new File("src//test//resources//small_file.txt"), null, null).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException  e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload plain large file test.
	 */
	@Test
	public void uploadPlainLargeFileTest() {
		LocalPeerConnection localPeerConnection = new LocalPeerConnection(new NodeEndpoint("http", "104.128.226.60", 7890));
		
		try {
			Upload upload = new Upload(localPeerConnection);
			String nemhash = upload.uploadFile(MessageTypes.PLAIN, this.xPvkey, this.xPubkey,
					new File("src//test//resources//large_file.zip"), null, null).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException  e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure data test.
	 */
	@Test
	public void uploadSecureDataTest() {
		LocalPeerConnection localPeerConnection = new LocalPeerConnection(new NodeEndpoint("http", "104.128.226.60", 7890));
		
		try {
			Upload upload = new Upload(localPeerConnection);
			String nemhash = upload.uploadData(MessageTypes.SECURE, this.xPvkey, this.xPubkey,
					"This is a Secure Test Data", null, null, null).getNemHash();
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
		LocalPeerConnection localPeerConnection = new LocalPeerConnection(new NodeEndpoint("http", "104.128.226.60", 7890));
		
		try {
			Upload upload = new Upload(localPeerConnection);
			String nemhash = upload.uploadFile(MessageTypes.SECURE, this.xPvkey, this.xPubkey,
					new File("src//test//resources//small_file.txt"), null, null).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException  e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure large file test.
	 */
	@Test
	public void uploadSecureLargeFileTest() {
		LocalPeerConnection localPeerConnection = new LocalPeerConnection(new NodeEndpoint("http", "104.128.226.60", 7890));
		
		try {
			Upload upload = new Upload(localPeerConnection);
			String nemhash = upload.uploadFile(MessageTypes.SECURE, this.xPvkey, this.xPubkey,
					new File("src//test//resources//large_file.zip"), null, null).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException  e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void uploadPlainDataWithMosaicTest() {
		try {
			LocalPeerConnection localPeerConnection = new LocalPeerConnection(new NodeEndpoint("http", "104.128.226.60", 7890));
			XpxSdkGlobalConstants.setGlobalTransactionFee(new FeeUnitAwareTransactionFeeCalculator(Amount.fromMicroNem(50_000L), mosaicInfoLookup()));
			Upload upload = new Upload(localPeerConnection);
			String nemhash = upload.uploadData(
					MessageTypes.PLAIN, 
					this.xPvkey, this.xPubkey, 
					"This is a test data from mosaic",
					null, "alvinreyes",null, 
					new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"), Quantity.fromValue(0))).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
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
