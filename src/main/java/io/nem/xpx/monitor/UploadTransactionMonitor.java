package io.nem.xpx.monitor;

import java.lang.reflect.Type;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

public class UploadTransactionMonitor implements StompFrameHandler {
	
	private String address;
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public Type getPayloadType(StompHeaders headers) {
		return String.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		//	handle curl
	}

}
