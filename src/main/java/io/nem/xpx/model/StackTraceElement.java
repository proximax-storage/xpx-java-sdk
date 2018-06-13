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

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;



/**
 * StackTraceElement.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-04-07T03:37:57.794-04:00")
public class StackTraceElement {
  
  /** The class name. */
  @SerializedName("className")
  private String className = null;

  /** The file name. */
  @SerializedName("fileName")
  private String fileName = null;

  /** The line number. */
  @SerializedName("lineNumber")
  private Integer lineNumber = null;

  /** The method name. */
  @SerializedName("methodName")
  private String methodName = null;

  /** The native method. */
  @SerializedName("nativeMethod")
  private Boolean nativeMethod = null;

  /**
   * Class name.
   *
   * @param className the class name
   * @return the stack trace element
   */
  public StackTraceElement className(String className) {
    this.className = className;
    return this;
  }

   /**
    * Get className.
    *
    * @return className
    */
  @ApiModelProperty(value = "")
  public String getClassName() {
    return className;
  }

  /**
   * Sets the class name.
   *
   * @param className the new class name
   */
  public void setClassName(String className) {
    this.className = className;
  }

  /**
   * File name.
   *
   * @param fileName the file name
   * @return the stack trace element
   */
  public StackTraceElement fileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

   /**
    * Get fileName.
    *
    * @return fileName
    */
  @ApiModelProperty(value = "")
  public String getFileName() {
    return fileName;
  }

  /**
   * Sets the file name.
   *
   * @param fileName the new file name
   */
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  /**
   * Line number.
   *
   * @param lineNumber the line number
   * @return the stack trace element
   */
  public StackTraceElement lineNumber(Integer lineNumber) {
    this.lineNumber = lineNumber;
    return this;
  }

   /**
    * Get lineNumber.
    *
    * @return lineNumber
    */
  @ApiModelProperty(value = "")
  public Integer getLineNumber() {
    return lineNumber;
  }

  /**
   * Sets the line number.
   *
   * @param lineNumber the new line number
   */
  public void setLineNumber(Integer lineNumber) {
    this.lineNumber = lineNumber;
  }

  /**
   * Method name.
   *
   * @param methodName the method name
   * @return the stack trace element
   */
  public StackTraceElement methodName(String methodName) {
    this.methodName = methodName;
    return this;
  }

   /**
    * Get methodName.
    *
    * @return methodName
    */
  @ApiModelProperty(value = "")
  public String getMethodName() {
    return methodName;
  }

  /**
   * Sets the method name.
   *
   * @param methodName the new method name
   */
  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  /**
   * Native method.
   *
   * @param nativeMethod the native method
   * @return the stack trace element
   */
  public StackTraceElement nativeMethod(Boolean nativeMethod) {
    this.nativeMethod = nativeMethod;
    return this;
  }

   /**
    * Get nativeMethod.
    *
    * @return nativeMethod
    */
  @ApiModelProperty(value = "")
  public Boolean getNativeMethod() {
    return nativeMethod;
  }

  /**
   * Sets the native method.
   *
   * @param nativeMethod the new native method
   */
  public void setNativeMethod(Boolean nativeMethod) {
    this.nativeMethod = nativeMethod;
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
    StackTraceElement stackTraceElement = (StackTraceElement) o;
    return Objects.equals(this.className, stackTraceElement.className) &&
        Objects.equals(this.fileName, stackTraceElement.fileName) &&
        Objects.equals(this.lineNumber, stackTraceElement.lineNumber) &&
        Objects.equals(this.methodName, stackTraceElement.methodName) &&
        Objects.equals(this.nativeMethod, stackTraceElement.nativeMethod);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return Objects.hash(className, fileName, lineNumber, methodName, nativeMethod);
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StackTraceElement {\n");
    
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
    sb.append("    lineNumber: ").append(toIndentedString(lineNumber)).append("\n");
    sb.append("    methodName: ").append(toIndentedString(methodName)).append("\n");
    sb.append("    nativeMethod: ").append(toIndentedString(nativeMethod)).append("\n");
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

