package io.nem.xpx.service.intf;

import io.nem.xpx.exceptions.ApiException;




/**
 * The Interface PublishAndSubscribeApi.
 */
public interface PublishAndSubscribeApi {
	
	/**
	 * Send to topic using GET.
	 *
	 * @param topic the topic
	 * @param message the message
	 * @return the object
	 * @throws Exception the exception
	 */
	public Object sendToTopicUsingGET(String topic, String message) throws Exception;
	
	/**
	 * Publish topic using GET.
	 *
	 * @param topic the topic
	 * @param message the message
	 * @return the object
	 * @throws Exception the exception
	 */
	public Object publishTopicUsingGET(String topic, String message) throws Exception;
}
