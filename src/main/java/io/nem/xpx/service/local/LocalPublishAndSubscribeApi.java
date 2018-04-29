package io.nem.xpx.service.local;

import io.nem.ApiException;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.service.intf.PublishAndSubscribeApi;


/**
 * The Class LocalPublishAndSubscribeApi.
 */
public class LocalPublishAndSubscribeApi implements PublishAndSubscribeApi {

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.PublishAndSubscribeApi#sendToTopicUsingGET(java.lang.String, java.lang.String)
	 */
	@Override
	public Object sendToTopicUsingGET(String topic, String message) throws Exception {
		return XpxSdkGlobalConstants.getProximaxConnection().pubsub.pub(topic, message);
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.PublishAndSubscribeApi#publishTopicUsingGET(java.lang.String, java.lang.String)
	 */
	@Override
	public Object publishTopicUsingGET(String topic, String message) throws Exception {
		// TODO Auto-generated method stub
		return XpxSdkGlobalConstants.getProximaxConnection().pubsub.pub(topic, message);
	}

}
