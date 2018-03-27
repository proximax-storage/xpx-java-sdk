package io.nem.xpx.builder;

import java.util.ArrayList;
import java.util.List;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import io.nem.xpx.model.ChannelHandleModel;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.monitor.TransactionMonitorSessionHandler;
import io.nem.xpx.monitor.UploadTransactionMonitor;
import io.nem.xpx.utils.ScannerUtils;

public class TransactionMonitorBuilder {
	private TransactionMonitorBuilder() {
	}

	public static IAddress init() {
		return new TransactionMonitorBuilder.Builder();
	}


	public interface IAddress {
		IChannel addressesToMonitor(List<String> addresses);

		IChannel addressesToMonitor(String... addresses);

		IChannel addressToMonitor(String address);
	}

	public interface IChannel {
		IChannel subscribe(String channel, UploadTransactionMonitor handler);

		void monitor();
	}

	public static class Builder implements  IAddress, IChannel {

		private String address;
		private List<String> addresses;

		private List<ChannelHandleModel> channelHandleList = new ArrayList<ChannelHandleModel>();

		public Builder() {
		}

		@Override
		public IChannel subscribe(String channel, UploadTransactionMonitor handler) {
			if (handler != null) {
				if (this.addresses != null && !this.addresses.isEmpty()) {
					for (String address : this.addresses) {
						handler.setAddress(address);
						this.channelHandleList.add(new ChannelHandleModel(channel, address, handler));
					}
				} else {
					handler.setAddress(this.address);
					this.channelHandleList.add(new ChannelHandleModel(channel, this.address, handler));
				}
			}
			return this;
		}

		@Override
		public void monitor() {
			if (this.addresses != null && !this.addresses.isEmpty()) {
				for (final String address : this.addresses) {
					new Thread(new Runnable() {

						@Override
						public void run() {
							monitor(address);
						}
					}).start();
				}
			} else {
				monitor(this.address);
			}

		}

		private void monitor(String address) {
			final String WS_URI = XpxSdkGlobalConstants.getWebsocketUri();
			List<Transport> transports = new ArrayList<Transport>(1);
			transports.add(new WebSocketTransport(new StandardWebSocketClient()));
			WebSocketClient transport = new SockJsClient(transports);
			WebSocketStompClient stompClient = new WebSocketStompClient(transport);
			stompClient.setMessageConverter(new StringMessageConverter());
			StompSessionHandler handler = new TransactionMonitorSessionHandler(address, this.channelHandleList);
			stompClient.connect(WS_URI, handler);
			// block and monitor exit action
			ScannerUtils.monitorExit();
		}

		@Override
		public IChannel addressToMonitor(String address) {
			this.address = address;
			return this;
		}


		@Override
		public IChannel addressesToMonitor(List<String> addresses) {
			this.addresses = addresses;
			return this;
		}

		@Override
		public IChannel addressesToMonitor(String... addresses) {
			this.addresses = new ArrayList<String>();
			for (String address : addresses) {
				this.addresses.add(address);
			}
			return this;
		}
	}
}
