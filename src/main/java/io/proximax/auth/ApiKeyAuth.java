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


package io.proximax.auth;

import io.proximax.Pair;

import java.util.Map;
import java.util.List;




/**
 * The Class ApiKeyAuth.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-04-25T23:45:59.064-04:00")
public class ApiKeyAuth implements Authentication {
  
  /** The location. */
  private final String location;
  
  /** The param name. */
  private final String paramName;

  /** The api key. */
  private String apiKey;
  
  /** The api key prefix. */
  private String apiKeyPrefix;

  /**
   * Instantiates a new api key auth.
   *
   * @param location the location
   * @param paramName the param name
   */
  public ApiKeyAuth(String location, String paramName) {
    this.location = location;
    this.paramName = paramName;
  }

  /**
   * Gets the location.
   *
   * @return the location
   */
  public String getLocation() {
    return location;
  }

  /**
   * Gets the param name.
   *
   * @return the param name
   */
  public String getParamName() {
    return paramName;
  }

  /**
   * Gets the api key.
   *
   * @return the api key
   */
  public String getApiKey() {
    return apiKey;
  }

  /**
   * Sets the api key.
   *
   * @param apiKey the new api key
   */
  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  /**
   * Gets the api key prefix.
   *
   * @return the api key prefix
   */
  public String getApiKeyPrefix() {
    return apiKeyPrefix;
  }

  /**
   * Sets the api key prefix.
   *
   * @param apiKeyPrefix the new api key prefix
   */
  public void setApiKeyPrefix(String apiKeyPrefix) {
    this.apiKeyPrefix = apiKeyPrefix;
  }

  /* (non-Javadoc)
   * @see io.proximax.auth.Authentication#applyToParams(java.util.List, java.util.Map)
   */
  @Override
  public void applyToParams(List<Pair> queryParams, Map<String, String> headerParams) {
    if (apiKey == null) {
      return;
    }
    String value;
    if (apiKeyPrefix != null) {
      value = apiKeyPrefix + " " + apiKey;
    } else {
      value = apiKey;
    }
    if ("query".equals(location)) {
      queryParams.add(new Pair(paramName, value));
    } else if ("header".equals(location)) {
      headerParams.put(paramName, value);
    }
  }
}
