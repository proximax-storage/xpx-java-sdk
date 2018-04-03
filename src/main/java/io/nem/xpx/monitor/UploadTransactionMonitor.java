//package io.nem.xpx.monitor;
//
//import java.lang.reflect.Type;
//import org.springframework.messaging.simp.stomp.StompFrameHandler;
//import org.springframework.messaging.simp.stomp.StompHeaders;
//
//
///**
// * The Class UploadTransactionMonitor.
// */
//public class UploadTransactionMonitor implements StompFrameHandler {
//	
//	/** The address. */
//	private String address;
//	
//	/**
//	 * Sets the address.
//	 *
//	 * @param address the new address
//	 */
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.springframework.messaging.simp.stomp.StompFrameHandler#getPayloadType(org.springframework.messaging.simp.stomp.StompHeaders)
//	 */
//	@Override
//	public Type getPayloadType(StompHeaders headers) {
//		return String.class;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.springframework.messaging.simp.stomp.StompFrameHandler#handleFrame(org.springframework.messaging.simp.stomp.StompHeaders, java.lang.Object)
//	 */
//	@Override
//	public void handleFrame(StompHeaders headers, Object payload) {
//		//	handle curl
//	}
//
//}
