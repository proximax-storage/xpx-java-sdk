package io.nem.xpx.facade;




/**
 * The Enum DataTextContentType.
 */
public enum DataTextContentType {
	
	/** The text plain. */
	TEXT_PLAIN("text/plain"),
	
	/** The text xml. */
	TEXT_XML("text/xml"),
	
	/** The text html. */
	TEXT_HTML("text/html"),
	
	/** The application json. */
	APPLICATION_JSON("application/json"),
	
	/** The application xml. */
	APPLICATION_XML("application/xml"),

	/** The application zip. */
	APPLICATION_ZIP("application/zip"),

	/** The application pdf. */
	APPLICATION_PDF("application/pdf"),

	/** The video mp4. */
	VIDEO_MP4("video/mp4"),

	/** The video quicktime. */
	VIDEO_QUICKTIME("video/quicktime");

	/** The value. */
	private String value;

	/**
	 * Creates a NIS API id.
	 *
	 * @param value The string representation.
	 */
	DataTextContentType(final String value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.value;
	}
}
