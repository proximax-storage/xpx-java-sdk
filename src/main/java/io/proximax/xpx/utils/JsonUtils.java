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

package io.proximax.xpx.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;






/**
 * Utility class for handling JSON serialization and deserialization.
 * 
 */
public class JsonUtils {

	/**
	 * Gson which handles the JSON conversion.
	 */
	private static Gson gson;

	/**
	 * Instantiates a new JsonUtils.
	 */
	private JsonUtils() {
	}

	/**
	 * Initializes the current Gson object if null and returns it. The Gson
	 * object has custom adapters to manage datatypes according to Facebook
	 * formats.
	 * 
	 * @return the current instance of Gson.
	 */
	private static Gson getGson() {
		if (gson == null) {
			// Creates the Gson object which will manage the information
			// received
			GsonBuilder builder = new GsonBuilder();

			gson = builder.create();
		}
		return gson;
	}

	/**
	 * From json.
	 *
	 * @param <T>
	 *            the generic type
	 * @param json
	 *            the string from which the object is to be deserialized.
	 * @param T
	 *            the type of the desired object.
	 * @return an object of type T from the string. Returns null if json is
	 *         null.
	 * @see Gson#fromJson(String, Class)
	 */
	public static <T> T fromJson(String json, Class<T> T) {
		return getGson().fromJson(json, T);
	}

	/**
	 * To json.
	 *
	 * @param src
	 *            the object for which Json representation is to be created
	 *            setting for Gson .
	 * @return Json representation of src.
	 * @see Gson#toJson(Object)
	 */
	public static String toJson(Object src) {
		return getGson().toJson(src);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JsonUtils []";
	}

}
