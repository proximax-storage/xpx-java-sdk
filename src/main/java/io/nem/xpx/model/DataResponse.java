package io.nem.xpx.model;

import com.google.gson.annotations.SerializedName;

import io.nem.xpx.utils.JsonUtils;




/**
 * The Class DataResponse.
 */
public class DataResponse {

	/** The data. */
	@SerializedName("data")
	private String data;

	/**
	 * Instantiates a new data response.
	 *
	 * @param data the data
	 */
	public DataResponse(String data) {
		this.data = data;
	}
	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(String data) {
		this.data = data;
	}
	
	
	/**
	 * To json.
	 *
	 * @return the string
	 */
	public String toJson() {
		return JsonUtils.toJson(this);
	}
}

