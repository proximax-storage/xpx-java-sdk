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

package io.proximax.xpx.facade.multisigupload;

import io.proximax.xpx.callback.ServiceAsyncCallback;
import io.proximax.xpx.exceptions.PeerConnectionNotFoundException;
import io.proximax.xpx.facade.AbstractAsyncFacadeService;
import io.proximax.xpx.facade.connection.PeerConnection;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;



/**
 * The Class MultisigUploadAsync.
 */
public class MultisigUploadAsync extends AbstractAsyncFacadeService {

	/** The multisig upload. */
	private MultisigUpload multisigUpload;

	/**
	 * Instantiates a new upload.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 *             the peer connection not found exception
	 */
	public MultisigUploadAsync(PeerConnection peerConnection) {
		this.multisigUpload = new MultisigUpload(peerConnection);
	}

	/**
	 * Upload data on multisig transaction.
	 *
	 * @param parameters the parameters
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<MultisigUploadResult> uploadDataOnMultisigTransaction(MultisigUploadTextDataParameter parameters,
			ServiceAsyncCallback<MultisigUploadResult> callback) {

		return runAsync(
				() -> {
					try {
						return multisigUpload.uploadDataOnMultisigTransaction(parameters);
					} catch (Exception e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

	/**
	 * Upload file on multisig transaction.
	 *
	 * @param parameters the parameters
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<MultisigUploadResult> uploadFileOnMultisigTransaction(MultisigUploadFileParameter parameters,
			ServiceAsyncCallback<MultisigUploadResult> callback) {

		return runAsync(
				() -> {
					try {
						return multisigUpload.uploadFileOnMultisigTransaction(parameters);
					} catch (Exception e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

	/**
	 * Upload binary on multisig transaction.
	 *
	 * @param parameters the parameters
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<MultisigUploadResult> uploadBinaryOnMultisigTransaction(
			MultisigUploadBinaryParameter parameters, ServiceAsyncCallback<MultisigUploadResult> callback) {

		return runAsync(
				() -> {
					try {
						return multisigUpload.uploadBinaryOnMultisigTransaction(parameters);
					} catch (Exception e) {
						throw new CompletionException(e);
					}
				}, callback);

	}

}
