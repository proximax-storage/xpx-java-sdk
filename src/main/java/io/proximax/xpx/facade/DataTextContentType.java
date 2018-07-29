/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.facade;




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
	VIDEO_QUICKTIME("video/quicktime"),

	IMAGE_PNG("image/png");

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
