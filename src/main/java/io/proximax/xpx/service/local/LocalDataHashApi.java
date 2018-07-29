
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

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.proximax.xpx.exceptions.ApiException;
import io.proximax.xpx.model.DataHashByteArrayEntity;
import io.proximax.xpx.model.PublishResult;
import io.proximax.xpx.service.intf.DataHashApi;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;






/**
 * The Class LocalDataHashApi.
 */
public class LocalDataHashApi implements DataHashApi {

	/** The proximax ifps connection. */
	private final IPFS proximaxIfpsConnection;

	/**
	 * Instantiates a new local data hash api.
	 *
	 * @param proximaxIfpsConnection the proximax ifps connection
	 */
	public LocalDataHashApi(final IPFS proximaxIfpsConnection) {
		this.proximaxIfpsConnection = proximaxIfpsConnection;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.DataHashApiInterface#generateHashAndExposeDataToNetworkUsingPOST(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String generateHashForDataOnlyUsingPOST(byte[] data) throws ApiException, IOException, NoSuchAlgorithmException {

		
		
		DataHashByteArrayEntity dataHashByteArrayEntity = new DataHashByteArrayEntity();
		dataHashByteArrayEntity.setFile(data);
		dataHashByteArrayEntity.setName(Math.abs(System.currentTimeMillis()) + "");

		
		PublishResult spfsBlockResult = getBinaryHashOnly(dataHashByteArrayEntity.getName(),
				dataHashByteArrayEntity.getFile());

		String multiHashString = spfsBlockResult.getMerkleNode().get(0).hash.toBase58();
		return multiHashString;
		

	}

	/**
	 * Gets the binary hash only.
	 *
	 * @param name the name
	 * @param binary the binary
	 * @return the binary hash only
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 */
	private PublishResult getBinaryHashOnly(String name, byte[] binary) throws IOException, ApiException {
		PublishResult result = null;

		// store it in ipfs
		result = new PublishResult();
		NamedStreamable.ByteArrayWrapper byteArrayWrapper = new NamedStreamable.ByteArrayWrapper(name, binary);
		List<MerkleNode> node = proximaxIfpsConnection.add(byteArrayWrapper,false,true);
		result.setMerkleNode(node);

		return result;
	}
	
	
}
