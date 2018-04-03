//package io.nem.xpx.builder;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.messaging.converter.StringMessageConverter;
//import org.springframework.messaging.simp.stomp.StompSessionHandler;
//import org.springframework.web.socket.client.WebSocketClient;
//import org.springframework.web.socket.client.standard.StandardWebSocketClient;
//import org.springframework.web.socket.messaging.WebSocketStompClient;
//import org.springframework.web.socket.sockjs.client.SockJsClient;
//import org.springframework.web.socket.sockjs.client.Transport;
//import org.springframework.web.socket.sockjs.client.WebSocketTransport;
//import io.nem.xpx.model.ChannelHandleModel;
//import io.nem.xpx.model.XpxSdkGlobalConstants;
//import io.nem.xpx.monitor.TransactionMonitorSessionHandler;
//import io.nem.xpx.monitor.UploadTransactionMonitor;
//import io.nem.xpx.utils.ScannerUtils;
//
//
///**
// * The Class TransactionMonitorBuilder.
// */
//public class TransactionMonitorBuilder {
//	
//	/**
//	 * Instantiates a new transaction monitor builder.
//	 */
//	private TransactionMonitorBuilder() {
//	}
//
//	/**
//	 * Inits the.
//	 *
//	 * @return the i address
//	 */
//	public static IAddress init() {
//		return new TransactionMonitorBuilder.Builder();
//	}
//
//
//	/**
//	 * The Interface IAddress.
//	 */
//	public interface IAddress {
//		
//		/**
//		 * Addresses to monitor.
//		 *
//		 * @param addresses the addresses
//		 * @return the i channel
//		 */
//		IChannel addressesToMonitor(List<String> addresses);
//
//		/**
//		 * Addresses to monitor.
//		 *
//		 * @param addresses the addresses
//		 * @return the i channel
//		 */
//		IChannel addressesToMonitor(String... addresses);
//
//		/**
//		 * Address to monitor.
//		 *
//		 * @param address the address
//		 * @return the i channel
//		 */
//		IChannel addressToMonitor(String address);
//	}
//
//	/**
//	 * The Interface IChannel.
//	 */
//	public interface IChannel {
//		
//		/**
//		 * Subscribe.
//		 *
//		 * @param channel the channel
//		 * @param handler the handler
//		 * @return the i channel
//		 */
//		IChannel subscribe(String channel, UploadTransactionMonitor handler);
//
//		/**
//		 * Monitor.
//		 */
//		void monitor();
//	}
//
//	/**
//	 * The Class Builder.
//	 */
//	public static class Builder implements  IAddress, IChannel {
//
//		/** The address. */
//		private String address;
//		
//		/** The addresses. */
//		private List<String> addresses;
//
//		/** The channel handle list. */
//		private List<ChannelHandleModel> channelHandleList = new ArrayList<ChannelHandleModel>();
//
//		/**
//		 * Instantiates a new builder.
//		 */
//		public Builder() {
//		}
//
//		/* (non-Javadoc)
//		 * @see io.nem.xpx.builder.TransactionMonitorBuilder.IChannel#subscribe(java.lang.String, io.nem.xpx.monitor.UploadTransactionMonitor)
//		 */
//		@Override
//		public IChannel subscribe(String channel, UploadTransactionMonitor handler) {
//			if (handler != null) {
//				if (this.addresses != null && !this.addresses.isEmpty()) {
//					for (String address : this.addresses) {
//						handler.setAddress(address);
//						this.channelHandleList.add(new ChannelHandleModel(channel, address, handler));
//					}
//				} else {
//					handler.setAddress(this.address);
//					this.channelHandleList.add(new ChannelHandleModel(channel, this.address, handler));
//				}
//			}
//			return this;
//		}
//
//		/* (non-Javadoc)
//		 * @see io.nem.xpx.builder.TransactionMonitorBuilder.IChannel#monitor()
//		 */
//		@Override
//		public void monitor() {
//			if (this.addresses != null && !this.addresses.isEmpty()) {
//				for (final String address : this.addresses) {
//					new Thread(new Runnable() {
//
//						@Override
//						public void run() {
//							monitor(address);
//						}
//					}).start();
//				}
//			} else {
//				monitor(this.address);
//			}
//
//		}
//
//		/**
//		 * Monitor.
//		 *
//		 * @param address the address
//		 */
//		private void monitor(String address) {
//			final String WS_URI = XpxSdkGlobalConstants.getWebsocketUri();
//			List<Transport> transports = new ArrayList<Transport>(1);
//			transports.add(new WebSocketTransport(new StandardWebSocketClient()));
//			WebSocketClient transport = new SockJsClient(transports);
//			WebSocketStompClient stompClient = new WebSocketStompClient(transport);
//			stompClient.setMessageConverter(new StringMessageConverter());
//			StompSessionHandler handler = new TransactionMonitorSessionHandler(address, this.channelHandleList);
//			stompClient.connect(WS_URI, handler);
//			// block and monitor exit action
//			ScannerUtils.monitorExit();
//		}
//
//		/* (non-Javadoc)
//		 * @see io.nem.xpx.builder.TransactionMonitorBuilder.IAddress#addressToMonitor(java.lang.String)
//		 */
//		@Override
//		public IChannel addressToMonitor(String address) {
//			this.address = address;
//			return this;
//		}
//
//
//		/* (non-Javadoc)
//		 * @see io.nem.xpx.builder.TransactionMonitorBuilder.IAddress#addressesToMonitor(java.util.List)
//		 */
//		@Override
//		public IChannel addressesToMonitor(List<String> addresses) {
//			this.addresses = addresses;
//			return this;
//		}
//
//		/* (non-Javadoc)
//		 * @see io.nem.xpx.builder.TransactionMonitorBuilder.IAddress#addressesToMonitor(java.lang.String[])
//		 */
//		@Override
//		public IChannel addressesToMonitor(String... addresses) {
//			this.addresses = new ArrayList<String>();
//			for (String address : addresses) {
//				this.addresses.add(address);
//			}
//			return this;
//		}
//	}
//}
