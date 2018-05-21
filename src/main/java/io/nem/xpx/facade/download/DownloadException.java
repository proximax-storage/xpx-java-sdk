package io.nem.xpx.facade.download;




public class DownloadException extends Exception {

	private static final long serialVersionUID = 1L;

	public DownloadException(Exception exception) {

		super(exception);
	}

	public DownloadException(String message, Exception exception) {

		super(message, exception);
	}

	public DownloadException(String message) {

		super(message);
	}

}
