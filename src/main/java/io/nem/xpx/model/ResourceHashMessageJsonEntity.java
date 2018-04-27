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
 * ResourceHashMessageJsonEntity.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-04-25T23:45:59.064-04:00")
public class ResourceHashMessageJsonEntity {
  
  /** The digest. */
  @SerializedName("digest")
  private String digest = null;

  /** The hash. */
  @SerializedName("hash")
  private String hash = null;

  /** The keywords. */
  @SerializedName("keywords")
  private String keywords = null;

  /** The meta data. */
  @SerializedName("metaData")
  private String metaData = null;

  /** The name. */
  @SerializedName("name")
  private String name = null;

  /** The size. */
  @SerializedName("size")
  private Integer size = null;

  /** The timestamp. */
  @SerializedName("timestamp")
  private Long timestamp = null;

  /** The type. */
  @SerializedName("type")
  private String type = null;

  /**
   * Digest.
   *
   * @param digest the digest
   * @return the resource hash message json entity
   */
  public ResourceHashMessageJsonEntity digest(String digest) {
    this.digest = digest;
    return this;
  }

   /**
    * Get digest.
    *
    * @return digest
    */
  @ApiModelProperty(value = "")
  public String getDigest() {
    return digest;
  }

  /**
   * Sets the digest.
   *
   * @param digest the new digest
   */
  public void setDigest(String digest) {
    this.digest = digest;
  }

  /**
   * Hash.
   *
   * @param hash the hash
   * @return the resource hash message json entity
   */
  public ResourceHashMessageJsonEntity hash(String hash) {
    this.hash = hash;
    return this;
  }

   /**
    * Get hash.
    *
    * @return hash
    */
  @ApiModelProperty(value = "")
  public String getHash() {
    return hash;
  }

  /**
   * Sets the hash.
   *
   * @param hash the new hash
   */
  public void setHash(String hash) {
    this.hash = hash;
  }

  /**
   * Keywords.
   *
   * @param keywords the keywords
   * @return the resource hash message json entity
   */
  public ResourceHashMessageJsonEntity keywords(String keywords) {
    this.keywords = keywords;
    return this;
  }

   /**
    * Get keywords.
    *
    * @return keywords
    */
  @ApiModelProperty(value = "")
  public String getKeywords() {
    return keywords;
  }

  /**
   * Sets the keywords.
   *
   * @param keywords the new keywords
   */
  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  /**
   * Meta data.
   *
   * @param metaData the meta data
   * @return the resource hash message json entity
   */
  public ResourceHashMessageJsonEntity metaData(String metaData) {
    this.metaData = metaData;
    return this;
  }

   /**
    * Get metaData.
    *
    * @return metaData
    */
  @ApiModelProperty(value = "")
  public String getMetaData() {
    return metaData;
  }

  /**
   * Sets the meta data.
   *
   * @param metaData the new meta data
   */
  public void setMetaData(String metaData) {
    this.metaData = metaData;
  }

  /**
   * Name.
   *
   * @param name the name
   * @return the resource hash message json entity
   */
  public ResourceHashMessageJsonEntity name(String name) {
    this.name = name;
    return this;
  }

   /**
    * Get name.
    *
    * @return name
    */
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Size.
   *
   * @param size the size
   * @return the resource hash message json entity
   */
  public ResourceHashMessageJsonEntity size(Integer size) {
    this.size = size;
    return this;
  }

   /**
    * Get size.
    *
    * @return size
    */
  @ApiModelProperty(value = "")
  public Integer getSize() {
    return size;
  }

  /**
   * Sets the size.
   *
   * @param size the new size
   */
  public void setSize(Integer size) {
    this.size = size;
  }

  /**
   * Timestamp.
   *
   * @param timestamp the timestamp
   * @return the resource hash message json entity
   */
  public ResourceHashMessageJsonEntity timestamp(Long timestamp) {
    this.timestamp = timestamp;
    return this;
  }

   /**
    * Get timestamp.
    *
    * @return timestamp
    */
  @ApiModelProperty(value = "")
  public Long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the timestamp.
   *
   * @param timestamp the new timestamp
   */
  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Type.
   *
   * @param type the type
   * @return the resource hash message json entity
   */
  public ResourceHashMessageJsonEntity type(String type) {
    this.type = type;
    return this;
  }

   /**
    * Get type.
    *
    * @return type
    */
  @ApiModelProperty(value = "")
  public String getType() {
    return type;
  }

  /**
   * Sets the type.
   *
   * @param type the new type
   */
  public void setType(String type) {
    this.type = type;
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
    ResourceHashMessageJsonEntity resourceHashMessageJsonEntity = (ResourceHashMessageJsonEntity) o;
    return Objects.equals(this.digest, resourceHashMessageJsonEntity.digest) &&
        Objects.equals(this.hash, resourceHashMessageJsonEntity.hash) &&
        Objects.equals(this.keywords, resourceHashMessageJsonEntity.keywords) &&
        Objects.equals(this.metaData, resourceHashMessageJsonEntity.metaData) &&
        Objects.equals(this.name, resourceHashMessageJsonEntity.name) &&
        Objects.equals(this.size, resourceHashMessageJsonEntity.size) &&
        Objects.equals(this.timestamp, resourceHashMessageJsonEntity.timestamp) &&
        Objects.equals(this.type, resourceHashMessageJsonEntity.type);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return Objects.hash(digest, hash, keywords, metaData, name, size, timestamp, type);
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceHashMessageJsonEntity {\n");
    
    sb.append("    digest: ").append(toIndentedString(digest)).append("\n");
    sb.append("    hash: ").append(toIndentedString(hash)).append("\n");
    sb.append("    keywords: ").append(toIndentedString(keywords)).append("\n");
    sb.append("    metaData: ").append(toIndentedString(metaData)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

