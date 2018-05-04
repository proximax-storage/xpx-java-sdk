package io.nem.xpx.websockets;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;



/**
 * The Class UnconfirmedTransactionWebSocketClient.
 */
public class UnconfirmedTransactionWebSocketClient extends WebSocketClient {

	/**
	 * Instantiates a new unconfirmed transaction web socket client.
	 *
	 * @param serverUri the server uri
	 */
	public UnconfirmedTransactionWebSocketClient(URI serverUri) {
		super(serverUri);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.java_websocket.client.WebSocketClient#onClose(int, java.lang.String, boolean)
	 */
	@Override
	public void onClose(int arg0, String arg1, boolean arg2) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see org.java_websocket.client.WebSocketClient#onError(java.lang.Exception)
	 */
	@Override
	public void onError(Exception arg0) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see org.java_websocket.client.WebSocketClient#onMessage(java.lang.String)
	 */
	@Override
	public void onMessage(String arg0) {
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see org.java_websocket.client.WebSocketClient#onOpen(org.java_websocket.handshake.ServerHandshake)
	 */
	@Override
	public void onOpen(ServerHandshake arg0) {
		// TODO Auto-generated method stub
	}

}
