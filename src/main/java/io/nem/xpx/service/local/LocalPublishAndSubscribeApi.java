package io.nem.xpx.service.local;

import io.ipfs.api.IPFS;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.service.intf.PublishAndSubscribeApi;




/**
 * The Class LocalPublishAndSubscribeApi.
 */
public class LocalPublishAndSubscribeApi implements PublishAndSubscribeApi {

	/** The proximax ifps connection. */
	private final IPFS proximaxIfpsConnection;

	/**
	 * Instantiates a new local publish and subscribe api.
	 *
	 * @param proximaxIfpsConnection the proximax ifps connection
	 */
	public LocalPublishAndSubscribeApi(final IPFS proximaxIfpsConnection) {
		this.proximaxIfpsConnection = proximaxIfpsConnection;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.PublishAndSubscribeApi#sendToTopicUsingGET(java.lang.String, java.lang.String)
	 */
	@Override
	public Object sendToTopicUsingGET(String topic, String message) throws Exception {
		return proximaxIfpsConnection.pubsub.pub(topic, message);
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.PublishAndSubscribeApi#publishTopicUsingGET(java.lang.String, java.lang.String)
	 */
	@Override
	public Object publishTopicUsingGET(String topic, String message) throws Exception {
		return proximaxIfpsConnection.pubsub.pub(topic, message);
	}

}
