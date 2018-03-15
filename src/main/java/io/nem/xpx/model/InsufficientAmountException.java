package io.nem.xpx.model;

public class InsufficientAmountException extends Exception {
	private static final long serialVersionUID = 1L;

	public InsufficientAmountException(Exception exception) {
		
		super(exception);
	}
	
	public InsufficientAmountException(String message) {
		
		super(message);
	}
	
}
