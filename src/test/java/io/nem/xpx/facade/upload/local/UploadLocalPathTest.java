package io.nem.xpx.facade.upload.local;

import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.facade.upload.UploadException;
import io.nem.xpx.facade.upload.UploadPathParameter;
import io.nem.xpx.remote.AbstractApiTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;

import static org.junit.Assert.assertTrue;



/**
 * The Class UploadTest.
 */
@Category(RemoteIntegrationTest.class)
@Ignore
public class UploadLocalPathTest extends AbstractApiTest {

	/**
	 * Upload path.
	 */
	@Test
	@Ignore
	public void uploadPath() {
		try {
			LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
					ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
					ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
					);
			Upload upload = new Upload(localPeerConnection);

			UploadPathParameter parameter = UploadPathParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.path("src/test/resources/")
					.metadata(null)
					.keywords(null)
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"),
							Quantity.fromValue(0)))
					.build();

			String nemhash = upload.uploadPath(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
