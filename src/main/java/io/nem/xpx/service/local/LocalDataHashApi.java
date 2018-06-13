
package io.nem.xpx.service.local;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.service.model.DataHashByteArrayEntity;
import io.nem.xpx.service.model.PublishResult;
import io.nem.xpx.service.intf.DataHashApi;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;





/**
 * The Class LocalDataHashApi.
 */
public class LocalDataHashApi implements DataHashApi {

	private final IPFS proximaxIfpsConnection;

	public LocalDataHashApi(final IPFS proximaxIfpsConnection) {
		this.proximaxIfpsConnection = proximaxIfpsConnection;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.DataHashApiInterface#generateHashAndExposeDataToNetworkUsingPOST(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
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
