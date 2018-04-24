package io.nem.xpx.facade.upload.local;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
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

import io.nem.api.ApiException;
import io.nem.xpx.AbstractApiTest;
import io.nem.xpx.builder.UploadBinaryParameterBuilder;
import io.nem.xpx.facade.Upload;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.UploadBinaryParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.utils.JsonUtils;

/**
 * The Class UploadTest.
 */
public class MultiThreadUploadLocalBinaryTest extends AbstractApiTest {

	public MultiThreadUploadLocalBinaryTest() {

		for (int i = 0; i < 500; i++) {
			Runnable task = () -> {

				RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

				try {
					Map<String, String> metaData = new HashMap<String, String>();
					metaData.put("key1", "value1");

					Upload upload = new Upload(remotePeerConnection);

					UploadBinaryParameter parameter = UploadBinaryParameterBuilder.senderPrivateKey(this.xPvkey)
							.recipientPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
							.data(FileUtils.readFileToByteArray(new File("src//test//resources//pdf_file.pdf")))
							.name("pdf_file2.pdf").keywords("pdf_file2").metaData(JsonUtils.toJson(metaData))
							.contentType("application/pdf") // make sure to put this in for files.
							.build();

					String nemhash = upload.uploadBinary(parameter).getNemHash();
					LOGGER.info(nemhash);
					Assert.assertNotNull(nemhash);
				} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
					e.printStackTrace();
					assertTrue(false);
				}
			};
			task.run();
		}
	}

	public static void main(String[] args) {
		new MultiThreadUploadLocalBinaryTest();
	}

}
