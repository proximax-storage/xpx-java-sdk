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

package io.proximax.xpx.service.local;

import io.proximax.xpx.exceptions.ApiException;
import io.proximax.xpx.model.RequestAnnounceDataSignature;
import io.proximax.xpx.service.NemTransactionApi;
import io.proximax.xpx.service.intf.TransactionAndAnnounceApi;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.serialization.JsonSerializer;
import org.nem.core.utils.HexEncoder;

import java.util.concurrent.ExecutionException;




/**
 * The Class LocalTransactionAndAnnounceApi.
 */
public class LocalTransactionAndAnnounceApi implements TransactionAndAnnounceApi {

	/** The proximax transaction api. */
	private final NemTransactionApi nemTransactionApi;

	/**
	 * Instantiates a new local transaction and announce api.
	 *
	 * @param nemTransactionApi the proximax transaction api
	 */
	public LocalTransactionAndAnnounceApi(NemTransactionApi nemTransactionApi) {
		this.nemTransactionApi = nemTransactionApi;
	}


	/* (non-Javadoc)
	 * @see io.proximax.xpx.service.intf.TransactionAndAnnounceApi#announceRequestPublishDataSignatureUsingPOST(io.proximax.xpx.model.RequestAnnounceDataSignature)
	 */
	@Override
	public String announceRequestPublishDataSignatureUsingPOST(
			RequestAnnounceDataSignature requestAnnounceDataSignature) throws ApiException, InterruptedException, ExecutionException {
		
		NemAnnounceResult announceResult = nemTransactionApi.sendTransferTransaction(
				HexEncoder.getBytes(requestAnnounceDataSignature.getData()),
				HexEncoder.getBytes(requestAnnounceDataSignature.getSignature()));
		
		TransactionMetaDataPair transactionMpair = nemTransactionApi
				.getTransaction(announceResult.getTransactionHash().toString());
		TransferTransaction transferTransaction = ((TransferTransaction) transactionMpair.getEntity());
		return JsonSerializer.serializeToJson(transferTransaction).toJSONString();
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.service.intf.TransactionAndAnnounceApi#getXPXTransactionUsingGET(java.lang.String)
	 */
	@Override
	public String getXPXTransactionUsingGET(String nemHash) throws ApiException, InterruptedException, ExecutionException {
		return JsonSerializer.serializeToJson(nemTransactionApi.getTransaction(nemHash)).toJSONString();
	}

}
