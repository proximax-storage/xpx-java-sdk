package io.nem.xpx.service;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.GeneratedAccount;
import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Account;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.ncc.AccountMetaDataPair;
import org.nem.core.model.ncc.HarvestInfo;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.Deserializer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;





/**
 * The Class NemAccountApi.
 */
public class NemAccountApi {

	/** The node endpoint. */
	private final NodeEndpoint nodeEndpoint;

	/** The async nem connector. */
	private DefaultAsyncNemConnector<ApiId> asyncNemConnector;

	/**
	 * Instantiates a new nem account api.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param asyncNemConnector the async nem connector
	 */
	public NemAccountApi(NodeEndpoint nodeEndpoint, DefaultAsyncNemConnector<ApiId> asyncNemConnector) {
		this.nodeEndpoint = nodeEndpoint;
		this.asyncNemConnector = asyncNemConnector;
	}

	/**
	 * Gets the account by address.
	 *
	 * @param address            the address
	 * @return the account by address
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 * @throws ApiException the api exception
	 */
	public AccountMetaDataPair getAccountByAddress(String address)
			throws InterruptedException, ExecutionException, ApiException {
		Deserializer des;
		des = asyncNemConnector
				.getAsync(nodeEndpoint, NisApiId.NIS_REST_ACCOUNT_LOOK_UP, "address=" + address)
				.exceptionally(fn -> {
					fn.printStackTrace();
					return null;
				}).get();
		return new AccountMetaDataPair(des);
	}

	/**
	 * Gets the account owned mosaic.
	 *
	 * @param address            the address
	 * @return the account owned mosaic
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 * @throws ApiException the api exception
	 */
	public List<Mosaic> getAccountOwnedMosaic(String address) throws InterruptedException, ExecutionException, ApiException {
		Deserializer des;
		List<Mosaic> list;
		des = asyncNemConnector.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_ACCOUNT_MOSAIC_OWNED, "address=" + address).get();
		list = (ArrayList<Mosaic>) des.readObjectArray("data", Mosaic::new);
		return list;
	}

	/**
	 * Get the list of Harvest Info for the account.
	 *
	 * @param address the address
	 * @return the account harvest info
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 * @throws ApiException the api exception
	 */
	public List<HarvestInfo> getAccountHarvestInfo(String address)
			throws InterruptedException, ExecutionException, ApiException {
		Deserializer des;
		List<HarvestInfo> list;
		des = asyncNemConnector
				.getAsync(nodeEndpoint, NisApiId.NIS_REST_ACCOUNT_HARVESTS, "address=" + address)
				.get();
		list = (ArrayList<HarvestInfo>) des.readObjectArray("data", HarvestInfo::new);

		return list;
	}

	/**
	 * Generate account.
	 *
	 * @return the key pair view model
	 */
	// TODO - unused
	public GeneratedAccount generateAccount() {
		GeneratedAccount ga = new GeneratedAccount();
		final KeyPair kp = new KeyPair();
		final Account account = new Account(kp);
		ga.setKeyPair(kp);
		ga.setAccount(account);
		ga.setEncodedAddress(account.getAddress().getEncoded());
		ga.setEncodedPrivateKey(kp.getPrivateKey().toString());
		ga.setEncodedPublicKey(kp.getPublicKey().toString());
		return ga;
	}

}
