package io.nem.xpx.monitor;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import io.nem.xpx.model.ChannelHandleModel;

public class TransactionMonitorSessionHandler implements StompSessionHandler {

	private String address = null;
	private List<ChannelHandleModel> channelHandleModels;

	public TransactionMonitorSessionHandler(String address) {
			this.address = address;
		}

	public TransactionMonitorSessionHandler(String address, List<ChannelHandleModel> channelHandleModels) {
			this.address = address;
			this.channelHandleModels = channelHandleModels;
		}

	@Override
	public Type getPayloadType(StompHeaders headers) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void handleFrame(StompHeaders arg0, Object arg1) {
		System.out.println("handleFrame");

	}

	@Override
	public void afterConnected(StompSession session, StompHeaders arg1) {
		String account = "{\"account\":\"" + this.address + "\"}";
		session.send("/w/api/account/get", account);
		for (ChannelHandleModel channelHandleModel : this.channelHandleModels) {
			session.subscribe(channelHandleModel.getChannel() + "/" + this.address,
					channelHandleModel.getFrameHandler());
		}

	}

	@Override
	public void handleException(StompSession arg0, StompCommand arg1, StompHeaders arg2, byte[] arg3, Throwable arg4) {
		System.out.println("Exception");
		arg4.printStackTrace();

	}

	@Override
	public void handleTransportError(StompSession arg0, Throwable arg1) {
		System.out.println("Error");

	}

}
