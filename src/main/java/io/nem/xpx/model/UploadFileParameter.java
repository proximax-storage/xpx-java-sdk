package io.nem.xpx.model;

import java.io.File;
import java.io.Serializable;
import org.nem.core.model.mosaic.Mosaic;
import io.nem.xpx.monitor.UploadTransactionMonitor;

public class UploadFileParameter implements Serializable {

	
	private int messageType;
	private String senderPrivateKey;
	private String recipientPublicKey;
	private File data;
	private String name;
	private String keywords;
	private String metaData;
	private Mosaic[] mosaics;
	private UploadTransactionMonitor confirmedTransactionHandler;
	private UploadTransactionMonitor unconfirmedTransactionHandler;
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
	
	public File getData() {
		return data;
	}
	public void setData(File data) {
		this.data = data;
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
	public UploadTransactionMonitor getConfirmedTransactionHandler() {
		return confirmedTransactionHandler;
	}
	public void setConfirmedTransactionHandler(UploadTransactionMonitor confirmedTransactionHandler) {
		this.confirmedTransactionHandler = confirmedTransactionHandler;
	}
	public UploadTransactionMonitor getUnconfirmedTransactionHandler() {
		return unconfirmedTransactionHandler;
	}
	public void setUnconfirmedTransactionHandler(UploadTransactionMonitor unconfirmedTransactionHandler) {
		this.unconfirmedTransactionHandler = unconfirmedTransactionHandler;
	}
	
}
