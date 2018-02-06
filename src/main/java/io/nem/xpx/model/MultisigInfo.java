/*
 * Proximax REST API
 * Proximax REST API
 *
 * OpenAPI spec version: v0.0.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.nem.xpx.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * MultisigInfo
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-02-06T23:01:14.896+08:00")
public class MultisigInfo {
  @SerializedName("cosignatoriesCount")
  private Integer cosignatoriesCount = null;

  @SerializedName("minCosignatories")
  private Integer minCosignatories = null;

  public MultisigInfo cosignatoriesCount(Integer cosignatoriesCount) {
    this.cosignatoriesCount = cosignatoriesCount;
    return this;
  }

   /**
   * Get cosignatoriesCount
   * @return cosignatoriesCount
  **/
  @ApiModelProperty(value = "")
  public Integer getCosignatoriesCount() {
    return cosignatoriesCount;
  }

  public void setCosignatoriesCount(Integer cosignatoriesCount) {
    this.cosignatoriesCount = cosignatoriesCount;
  }

  public MultisigInfo minCosignatories(Integer minCosignatories) {
    this.minCosignatories = minCosignatories;
    return this;
  }

   /**
   * Get minCosignatories
   * @return minCosignatories
  **/
  @ApiModelProperty(value = "")
  public Integer getMinCosignatories() {
    return minCosignatories;
  }

  public void setMinCosignatories(Integer minCosignatories) {
    this.minCosignatories = minCosignatories;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MultisigInfo multisigInfo = (MultisigInfo) o;
    return Objects.equals(this.cosignatoriesCount, multisigInfo.cosignatoriesCount) &&
        Objects.equals(this.minCosignatories, multisigInfo.minCosignatories);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cosignatoriesCount, minCosignatories);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MultisigInfo {\n");
    
    sb.append("    cosignatoriesCount: ").append(toIndentedString(cosignatoriesCount)).append("\n");
    sb.append("    minCosignatories: ").append(toIndentedString(minCosignatories)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

