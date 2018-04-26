package io.nem.xpx.facade.upload.remote;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
import io.nem.xpx.builder.UploadFileParameterBuilder;
import io.nem.xpx.facade.Upload;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.service.model.PeerConnectionNotFoundException;
import io.nem.xpx.service.model.UploadException;
import io.nem.xpx.service.model.UploadFileParameter;
import io.nem.xpx.service.model.XpxSdkGlobalConstants;
import io.nem.xpx.utils.JsonUtils;


/**
 * The Class UploadTest.
 */
public class UploadRemoteFileTest extends AbstractApiTest {

	
	/**
	 * Upload plain file test.
	 */
	@Test
	public void uploadPlainFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);

		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);
			UploadFileParameter parameter = UploadFileParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data(new File("src//test//resources//pdf_file.pdf"))
					.contentType("application/pdf")
					.metaData(JsonUtils.toJson(metaData)) // one level map to json
					.keywords("plain,test")
					.build();
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void uploadPlainPdfFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);

		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);
			UploadFileParameter parameter = UploadFileParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data(new File("src//test//resources//pdf_file.pdf"))
					.metaData(JsonUtils.toJson(metaData)) // one level map to json
					.keywords("plain,pdf,test")
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
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);

		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);
			UploadFileParameter parameter = UploadFileParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data(new File("src//test//resources//large_file.zip"))
					.metaData(JsonUtils.toJson(metaData)) // one level map to json
					.keywords("plain,large,test")
					.build();
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	/**
	 * Upload secure file test.
	 */
	@Test
	public void uploadSecureFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);

		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);
			UploadFileParameter parameter = UploadFileParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.SECURE)
					.data(new File("src//test//resources//small_file.txt"))
					.contentType("text/plain")
					.metaData(JsonUtils.toJson(metaData)) // one level map to json
					.keywords("secure,test")
					.build();
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	
	@Test
	public void uploadPlainFileWithMosaicTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);
		
		try {
			Upload upload = new Upload(remotePeerConnection);
			XpxSdkGlobalConstants.setGlobalTransactionFee(
					new FeeUnitAwareTransactionFeeCalculator(Amount.fromMicroNem(50_000L), mosaicInfoLookup()));
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			
			UploadFileParameter parameter = UploadFileParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data(new File("src//test//resources//large_file.zip"))
					.metaData(JsonUtils.toJson(metaData))
					.keywords("plain,data,wmosaics")
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"),
							Quantity.fromValue(0)))
					.build();

			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	
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
