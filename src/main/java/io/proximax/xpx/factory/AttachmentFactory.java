/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.factory;

import org.nem.core.model.Message;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.mosaic.Mosaic;





/**
 * A factory for creating Attachment objects.
 */
public class AttachmentFactory {

	/**
	 * Creates a new Attachment object.
	 *
	 * @return the transfer transaction attachment
	 */
	public static TransferTransactionAttachment createTransferTransactionAttachment() {
		return new TransferTransactionAttachment();
	}

	/**
	 * Creates a new Attachment object.
	 *
	 * @param message
	 *            the message
	 * @return the transfer transaction attachment
	 */
	public static TransferTransactionAttachment createTransferTransactionAttachmentMessage(Message message) {
		return new TransferTransactionAttachment(message);
	}

	/**
	 * Creates a new Attachment object.
	 *
	 * @param mosaic the mosaic
	 * @return the transfer transaction attachment
	 */
	public static TransferTransactionAttachment createTransferTransactionAttachmentMosaic(Mosaic mosaic) {
		TransferTransactionAttachment attachment = new TransferTransactionAttachment();
		attachment.addMosaic(mosaic);
		return attachment;
	}

}
