package io.nem.xpx.model;

import java.io.Serializable;
import org.nem.core.model.mosaic.Mosaic;
import io.nem.xpx.monitor.UploadTransactionMonitor;

public class UploadPathParameter implements Serializable {

	
	private int messageType;
	private String senderPrivateKey;
	private String recipientPublicKey;
	private String path;
	private String name;
	private String keywords;
	private String metaData;
	private Mosaic[] mosaics;
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	public String getSenderPrivateKey() {
		return senderPrivateKey;
	}
	public void setSenderPrivateKey(String senderPrivateKey) {
		this.senderPrivateKey = senderPrivateKey;
	}
	public String getRecipientPublicKey() {
		return recipientPublicKey;
	}
	public void setRecipientPublicKey(String recipientPublicKey) {
		this.recipientPublicKey = recipientPublicKey;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getMetaData() {
		return metaData;
	}
	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}
	public Mosaic[] getMosaics() {
		return mosaics;
	}
	public void setMosaics(Mosaic[] mosaics) {
		this.mosaics = mosaics;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
