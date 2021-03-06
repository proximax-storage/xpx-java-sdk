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
 * Contact: alvin.reyes@botmill.io
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
 * Resource.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-04-07T03:37:57.794-04:00")
public class Resource {
  
  /** The description. */
  @SerializedName("description")
  private String description = null;

  /** The file. */
  @SerializedName("file")
  private java.io.File file = null;

  /** The filename. */
  @SerializedName("filename")
  private String filename = null;

  /** The input stream. */
  @SerializedName("inputStream")
  private InputStream inputStream = null;

  /** The open. */
  @SerializedName("open")
  private Boolean open = null;

  /** The readable. */
  @SerializedName("readable")
  private Boolean readable = null;

  /** The uri. */
  @SerializedName("uri")
  private URI uri = null;

  /** The url. */
  @SerializedName("url")
  private URL url = null;

  /**
   * Description.
   *
   * @param description the description
   * @return the resource
   */
  public Resource description(String description) {
    this.description = description;
    return this;
  }

   /**
    * Get description.
    *
    * @return description
    */
  @ApiModelProperty(value = "")
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description.
   *
   * @param description the new description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * File.
   *
   * @param file the file
   * @return the resource
   */
  public Resource file(java.io.File file) {
    this.file = file;
    return this;
  }

   /**
    * Get file.
    *
    * @return file
    */
  @ApiModelProperty(value = "")
  public java.io.File getFile() {
    return file;
  }

  /**
   * Sets the file.
   *
   * @param file the new file
   */
  public void setFile(java.io.File file) {
    this.file = file;
  }

  /**
   * Filename.
   *
   * @param filename the filename
   * @return the resource
   */
  public Resource filename(String filename) {
    this.filename = filename;
    return this;
  }

   /**
    * Get filename.
    *
    * @return filename
    */
  @ApiModelProperty(value = "")
  public String getFilename() {
    return filename;
  }

  /**
   * Sets the filename.
   *
   * @param filename the new filename
   */
  public void setFilename(String filename) {
    this.filename = filename;
  }

  /**
   * Input stream.
   *
   * @param inputStream the input stream
   * @return the resource
   */
  public Resource inputStream(InputStream inputStream) {
    this.inputStream = inputStream;
    return this;
  }

   /**
    * Get inputStream.
    *
    * @return inputStream
    */
  @ApiModelProperty(value = "")
  public InputStream getInputStream() {
    return inputStream;
  }

  /**
   * Sets the input stream.
   *
   * @param inputStream the new input stream
   */
  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  /**
   * Open.
   *
   * @param open the open
   * @return the resource
   */
  public Resource open(Boolean open) {
    this.open = open;
    return this;
  }

   /**
    * Get open.
    *
    * @return open
    */
  @ApiModelProperty(value = "")
  public Boolean getOpen() {
    return open;
  }

  /**
   * Sets the open.
   *
   * @param open the new open
   */
  public void setOpen(Boolean open) {
    this.open = open;
  }

  /**
   * Readable.
   *
   * @param readable the readable
   * @return the resource
   */
  public Resource readable(Boolean readable) {
    this.readable = readable;
    return this;
  }

   /**
    * Get readable.
    *
    * @return readable
    */
  @ApiModelProperty(value = "")
  public Boolean getReadable() {
    return readable;
  }

  /**
   * Sets the readable.
   *
   * @param readable the new readable
   */
  public void setReadable(Boolean readable) {
    this.readable = readable;
  }

  /**
   * Uri.
   *
   * @param uri the uri
   * @return the resource
   */
  public Resource uri(URI uri) {
    this.uri = uri;
    return this;
  }

   /**
    * Get uri.
    *
    * @return uri
    */
  @ApiModelProperty(value = "")
  public URI getUri() {
    return uri;
  }

  /**
   * Sets the uri.
   *
   * @param uri the new uri
   */
  public void setUri(URI uri) {
    this.uri = uri;
  }

  /**
   * Url.
   *
   * @param url the url
   * @return the resource
   */
  public Resource url(URL url) {
    this.url = url;
    return this;
  }

   /**
    * Get url.
    *
    * @return url
    */
  @ApiModelProperty(value = "")
  public URL getUrl() {
    return url;
  }

  /**
   * Sets the url.
   *
   * @param url the new url
   */
  public void setUrl(URL url) {
    this.url = url;
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
    Resource resource = (Resource) o;
    return Objects.equals(this.description, resource.description) &&
        Objects.equals(this.file, resource.file) &&
        Objects.equals(this.filename, resource.filename) &&
        Objects.equals(this.inputStream, resource.inputStream) &&
        Objects.equals(this.open, resource.open) &&
        Objects.equals(this.readable, resource.readable) &&
        Objects.equals(this.uri, resource.uri) &&
        Objects.equals(this.url, resource.url);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return Objects.hash(description, file, filename, inputStream, open, readable, uri, url);
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Resource {\n");
    
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    filename: ").append(toIndentedString(filename)).append("\n");
    sb.append("    inputStream: ").append(toIndentedString(inputStream)).append("\n");
    sb.append("    open: ").append(toIndentedString(open)).append("\n");
    sb.append("    readable: ").append(toIndentedString(readable)).append("\n");
    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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

