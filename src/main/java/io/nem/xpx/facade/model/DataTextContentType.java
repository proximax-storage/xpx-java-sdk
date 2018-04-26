package io.nem.xpx.facade.model;

public enum DataTextContentType {
	
	TEXT_PLAIN("text/plain"),
	TEXT_XML("text/xml"),
	TEXT_HTML("text/html"),
	APPLICATION_JSON("application/json"),
	APPLICATION_XML("application/xml");
	
	private String value;

	/**
	 * Creates a NIS API id.
	 *
	 * @param value The string representation.
	 */
	DataTextContentType(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
