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

package io.proximax.xpx.model;

import com.google.gson.annotations.SerializedName;
import io.proximax.xpx.utils.JsonUtils;





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

