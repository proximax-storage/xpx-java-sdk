package io.nem.xpx.model;

import org.springframework.messaging.simp.stomp.StompFrameHandler;

public class ChannelHandleModel {
	private String channel;
	private String address;
	private StompFrameHandler frameHandler;
	
	public ChannelHandleModel() {
	}
	
	public ChannelHandleModel(String channel, String address, StompFrameHandler frameHandler) {
		this.channel = channel;
		this.address = address;
		this.frameHandler = frameHandler;
	}
	public ChannelHandleModel(String channel, StompFrameHandler frameHandler) {
		this.channel = channel;
		this.frameHandler = frameHandler;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public StompFrameHandler getFrameHandler() {
		return frameHandler;
	}

	public void setFrameHandler(StompFrameHandler frameHandler) {
		this.frameHandler = frameHandler;
	}
	
	
	
}
