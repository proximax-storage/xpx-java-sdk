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

/*
 * Proximax P2P Storage REST API
 * Proximax P2P Storage REST API
 *
 * OpenAPI spec version: v0.0.1
 * Contact: proximax.storage@proximax.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.proximax.xpx.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;



/**
 * RequestAnnounceDataSignature.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-04-25T23:45:59.064-04:00")
public class RequestAnnounceDataSignature {
  
  /** The data. */
  @SerializedName("data")
  private String data = null;

  /** The signature. */
  @SerializedName("signature")
  private String signature = null;

  /**
   * Data.
   *
   * @param data the data
   * @return the request announce data signature
   */
  public RequestAnnounceDataSignature data(String data) {
    this.data = data;
    return this;
  }

   /**
    * Get data.
    *
    * @return data
    */
  @ApiModelProperty(value = "")
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
   * Signature.
   *
   * @param signature the signature
   * @return the request announce data signature
   */
  public RequestAnnounceDataSignature signature(String signature) {
    this.signature = signature;
    return this;
  }

   /**
    * Get signature.
    *
    * @return signature
    */
  @ApiModelProperty(value = "")
  public String getSignature() {
    return signature;
  }

  /**
   * Sets the signature.
   *
   * @param signature the new signature
   */
  public void setSignature(String signature) {
    this.signature = signature;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestAnnounceDataSignature requestAnnounceDataSignature = (RequestAnnounceDataSignature) o;
    return Objects.equals(this.data, requestAnnounceDataSignature.data) &&
        Objects.equals(this.signature, requestAnnounceDataSignature.signature);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return Objects.hash(data, signature);
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequestAnnounceDataSignature {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    signature: ").append(toIndentedString(signature)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   *
   * @param o the o
   * @return the string
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

