package io.nem.xpx.facade;

import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.apache.commons.codec.binary.Base64;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.mosaic.Mosaic;

import java.nio.ByteBuffer;
import java.util.Iterator;



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
	protected ResourceHashMessage deserializeResourceMessageHash(byte[] object) {
		ResourceHashMessage resourceMessage = ResourceHashMessage
				.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(object)));
		return resourceMessage;
	}

	/**
	 * Check if txn have XPX mosaic.
	 *
	 * @param transaction the transaction
	 * @return true, if successful
	 */
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

}
