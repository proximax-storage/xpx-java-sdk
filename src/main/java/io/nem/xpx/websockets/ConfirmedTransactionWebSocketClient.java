package io.nem.xpx.websockets;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;


/**
 * The Class ConfirmedTransactionWebSocketClient.
 */
public class ConfirmedTransactionWebSocketClient extends WebSocketClient {

	/** The logger. */
	protected Logger LOGGER = Logger.getAnonymousLogger();

	/**
	 * Instantiates a new confirmed transaction web socket client.
	 *
	 * @param serverUri
	 *            the server uri
	 */
	public ConfirmedTransactionWebSocketClient(URI serverUri) {
		super(serverUri);
	}

	/**
	 * Instantiates a new confirmed transaction web socket client.
	 *
	 * @param serverUri
	 *            the server uri
	 * @param draft
	 *            the draft
	 */
	public ConfirmedTransactionWebSocketClient(URI serverUri, Draft draft) {
		super(serverUri, draft);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.java_websocket.client.WebSocketClient#onClose(int, java.lang.String,
	 * boolean)
	 */
	@Override
	public void onClose(int arg0, String arg1, boolean arg2) {
		LOGGER.info("onClose connection " + arg0);
		LOGGER.info("onClose connection " + arg1);
		LOGGER.info("onClose connection " + arg2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.java_websocket.client.WebSocketClient#onError(java.lang.Exception)
	 */
	@Override
	public void onError(Exception arg0) {
		LOGGER.info("onError connection" + arg0.getMessage());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.java_websocket.client.WebSocketClient#onMessage(java.lang.String)
	 */
	@Override
	public void onMessage(String arg0) {
		LOGGER.info("onMessage connection");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.java_websocket.client.WebSocketClient#onOpen(org.java_websocket.handshake
	 * .ServerHandshake)
	 */
	@Override
	public void onOpen(ServerHandshake arg0) {
		LOGGER.info("opened connection");
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void main(String[] args) throws URISyntaxException, InterruptedException {
		// host("23.228.67.85").port("7890").wsPort("7778")

		ConfirmedTransactionWebSocketClient c = new ConfirmedTransactionWebSocketClient(
				new URI("ws://23.228.67.85:7778/w/api/account/get"));
		// ConfirmedTransactionWebSocketClient c = new
		// ConfirmedTransactionWebSocketClient(new URI("ws://localhost:8887"));
		// c.send("TDZQB4XV6ZQ3X7PXGWYL4KWEY7DY2RGSLIN7PA3F");
		// "{\"account\":\"TDZQB4XV6ZQ3X7PXGWYL4KWEY7DY2RGSLIN7PA3F\"}"
		c.connect();
		c.send("{\"account\":\"TDZQB4XV6ZQ3X7PXGWYL4KWEY7DY2RGSLIN7PA3F\"}");

	}

}
