/*
 * Proximax P2P Storage REST API
 * Proximax P2P Storage REST API
 *
 * OpenAPI spec version: v0.0.1
 * Contact: alvin.reyes@botmill.io
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
 * Amount
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-03-12T15:01:06.336-04:00")
public class Amount {
  @SerializedName("numMicroNem")
  private Long numMicroNem = null;

  @SerializedName("numNem")
  private Long numNem = null;

  public Amount numMicroNem(Long numMicroNem) {
    this.numMicroNem = numMicroNem;
    return this;
  }

   /**
   * Get numMicroNem
   * @return numMicroNem
  **/
  @ApiModelProperty(value = "")
  public Long getNumMicroNem() {
    return numMicroNem;
  }

  public void setNumMicroNem(Long numMicroNem) {
    this.numMicroNem = numMicroNem;
  }

  public Amount numNem(Long numNem) {
    this.numNem = numNem;
    return this;
  }

   /**
   * Get numNem
   * @return numNem
  **/
  @ApiModelProperty(value = "")
  public Long getNumNem() {
    return numNem;
  }

  public void setNumNem(Long numNem) {
    this.numNem = numNem;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Amount amount = (Amount) o;
    return Objects.equals(this.numMicroNem, amount.numMicroNem) &&
        Objects.equals(this.numNem, amount.numNem);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numMicroNem, numNem);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Amount {\n");
    
    sb.append("    numMicroNem: ").append(toIndentedString(numMicroNem)).append("\n");
    sb.append("    numNem: ").append(toIndentedString(numNem)).append("\n");
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

