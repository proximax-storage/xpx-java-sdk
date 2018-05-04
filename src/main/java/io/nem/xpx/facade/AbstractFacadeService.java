package io.nem.xpx.facade;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Iterator;

import org.apache.commons.codec.binary.Base64;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.mosaic.Mosaic;
import org.pmw.tinylog.Logger;

import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;

/**
 * The Class AbstractFacadeService.
 */
public abstract class AbstractFacadeService {

	/**
	 * Byte to serial object.
	 *
	 * @param object
	 *            the object
	 * @return the resource hash message
	 */
	protected ResourceHashMessage byteToSerialObject(byte[] object) {
		ResourceHashMessage resourceMessage = ResourceHashMessage
				.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(object)));
		return resourceMessage;
	}

	protected boolean checkIfTxnHaveXPXMosaic(Transaction transaction) {

		if (transaction instanceof TransferTransaction) {
			Iterator<Mosaic> mosaicIter = ((TransferTransaction) transaction).getMosaics().iterator();
			while (mosaicIter.hasNext()) {
				Mosaic mosaic = mosaicIter.next();
				if (mosaic.getMosaicId().getNamespaceId().getRoot().toString().equals("prx")
						&& mosaic.getMosaicId().getName().equals("xpx")) {
					return true;
				}
			}

		}
		return false;
	}

	/**
	 * Safe async to gateways.
	 *
	 * @param resource
	 *            the resource
	 */
	protected void safeAsyncToGateways(ResourceHashMessage resource) {
		Runnable task = () -> {
			for (String s : XpxSdkGlobalConstants.GLOBAL_GATEWAYS) {
				HttpURLConnection conn = null;
				try {
					conn = (HttpURLConnection) new URL(s + "/ipfs/" + resource.hash()).openConnection();
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					conn.setRequestMethod("HEAD");
					conn.setDoOutput(true);
					conn.setUseCaches(false);

					DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
					wr.close();
				} catch (IOException e) {
					// we don't throw any error. Just Log.
					Logger.error("Error calling gateways");

				} finally {
					conn.disconnect();
				}
			}
		};
		Thread thread = new Thread(task);
		thread.start();
	}
}
