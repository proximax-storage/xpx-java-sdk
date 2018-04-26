package io.nem.xpx.facade.upload.local;

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
import io.nem.xpx.service.model.PeerConnectionNotFoundException;
import io.nem.xpx.service.model.UploadDataParameter;
import io.nem.xpx.service.model.UploadException;
import io.nem.xpx.service.model.UploadFileParameter;
import io.nem.xpx.service.model.UploadPathParameter;
import io.nem.xpx.service.model.XpxSdkGlobalConstants;
import io.nem.xpx.utils.JsonUtils;


/**
 * The Class UploadTest.
 */
public class UploadLocalPathTest extends AbstractApiTest {

	/**
	 * Upload path.
	 */
	@Test
	public void uploadPath() {
		try {
			LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
					new NodeEndpoint("http", "104.128.226.60", 7890));
			XpxSdkGlobalConstants.setGlobalTransactionFee(
					new FeeUnitAwareTransactionFeeCalculator(Amount.fromMicroNem(50_000L), mosaicInfoLookup()));
			Upload upload = new Upload(localPeerConnection);

			UploadPathParameter parameter = UploadPathParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.path("D:/Projects/eworkspace/proximaxsdks/xpx-java-sdk/src/test/resources/")
					.metaData(null).keywords(null)
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"),
							Quantity.fromValue(0)))
					.build();

			String nemhash = upload.uploadPath(parameter).getNemHash();
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
