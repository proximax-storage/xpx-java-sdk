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
import io.proximax.xpx.service.intf.PublishAndSubscribeApi;




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
	 * @see io.proximax.xpx.service.intf.PublishAndSubscribeApi#sendToTopicUsingGET(java.lang.String, java.lang.String)
	 */
	@Override
	public Object sendToTopicUsingGET(String topic, String message) throws Exception {
		return proximaxIfpsConnection.pubsub.pub(topic, message);
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.service.intf.PublishAndSubscribeApi#publishTopicUsingGET(java.lang.String, java.lang.String)
	 */
	@Override
	public Object publishTopicUsingGET(String topic, String message) throws Exception {
		return proximaxIfpsConnection.pubsub.pub(topic, message);
	}

}
