//package io.nem.xpx.monitor;
//
//import java.lang.reflect.Type;
//import java.util.List;
//
//import org.springframework.messaging.simp.stomp.StompCommand;
//import org.springframework.messaging.simp.stomp.StompHeaders;
//import org.springframework.messaging.simp.stomp.StompSession;
//import org.springframework.messaging.simp.stomp.StompSessionHandler;
//
//import io.nem.xpx.model.ChannelHandleModel;
//
//
///**
// * The Class TransactionMonitorSessionHandler.
// */
//public class TransactionMonitorSessionHandler implements StompSessionHandler {
//
//	/** The address. */
//	private String address = null;
//	
//	/** The channel handle models. */
//	private List<ChannelHandleModel> channelHandleModels;
//
//	/**
//	 * Instantiates a new transaction monitor session handler.
//	 *
//	 * @param address the address
//	 */
//	public TransactionMonitorSessionHandler(String address) {
//			this.address = address;
//		}
//
//	/**
//	 * Instantiates a new transaction monitor session handler.
//	 *
//	 * @param address the address
//	 * @param channelHandleModels the channel handle models
//	 */
//	public TransactionMonitorSessionHandler(String address, List<ChannelHandleModel> channelHandleModels) {
//			this.address = address;
//			this.channelHandleModels = channelHandleModels;
//		}
//
//	/* (non-Javadoc)
//	 * @see org.springframework.messaging.simp.stomp.StompFrameHandler#getPayloadType(org.springframework.messaging.simp.stomp.StompHeaders)
//	 */
//	@Override
//	public Type getPayloadType(StompHeaders headers) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	/* (non-Javadoc)
//	 * @see org.springframework.messaging.simp.stomp.StompFrameHandler#handleFrame(org.springframework.messaging.simp.stomp.StompHeaders, java.lang.Object)
//	 */
//	@Override
//	public void handleFrame(StompHeaders arg0, Object arg1) {
//		System.out.println("handleFrame");
//
//	}
//
//	/* (non-Javadoc)
//	 * @see org.springframework.messaging.simp.stomp.StompSessionHandler#afterConnected(org.springframework.messaging.simp.stomp.StompSession, org.springframework.messaging.simp.stomp.StompHeaders)
//	 */
//	@Override
//	public void afterConnected(StompSession session, StompHeaders arg1) {
//		String account = "{\"account\":\"" + this.address + "\"}";
//		session.send("/w/api/account/get", account);
//		for (ChannelHandleModel channelHandleModel : this.channelHandleModels) {
//			session.subscribe(channelHandleModel.getChannel() + "/" + this.address,
//					channelHandleModel.getFrameHandler());
//		}
//
//	}
//
//	/* (non-Javadoc)
//	 * @see org.springframework.messaging.simp.stomp.StompSessionHandler#handleException(org.springframework.messaging.simp.stomp.StompSession, org.springframework.messaging.simp.stomp.StompCommand, org.springframework.messaging.simp.stomp.StompHeaders, byte[], java.lang.Throwable)
//	 */
//	@Override
//	public void handleException(StompSession arg0, StompCommand arg1, StompHeaders arg2, byte[] arg3, Throwable arg4) {
//		System.out.println("Exception");
//		arg4.printStackTrace();
//
//	}
//
//	/* (non-Javadoc)
//	 * @see org.springframework.messaging.simp.stomp.StompSessionHandler#handleTransportError(org.springframework.messaging.simp.stomp.StompSession, java.lang.Throwable)
//	 */
//	@Override
//	public void handleTransportError(StompSession arg0, Throwable arg1) {
//		System.out.println("Error");
//
//	}
//
//}
