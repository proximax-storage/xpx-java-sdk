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
import io.nem.xpx.model.Address;
import io.nem.xpx.model.Amount;
import io.nem.xpx.model.BlockAmount;
import io.nem.xpx.model.KeyPair;
import io.nem.xpx.model.MultisigInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * AccountInfo
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-02-13T18:03:17.277+08:00")
public class AccountInfo {
  @SerializedName("address")
  private Address address = null;

  @SerializedName("balance")
  private Amount balance = null;

  @SerializedName("importance")
  private Double importance = null;

  @SerializedName("keyPair")
  private KeyPair keyPair = null;

  @SerializedName("label")
  private String label = null;

  @SerializedName("multisigInfo")
  private MultisigInfo multisigInfo = null;

  @SerializedName("numHarvestedBlocks")
  private BlockAmount numHarvestedBlocks = null;

  @SerializedName("vestedBalance")
  private Amount vestedBalance = null;

  public AccountInfo address(Address address) {
    this.address = address;
    return this;
  }

   /**
   * Get address
   * @return address
  **/
  @ApiModelProperty(value = "")
  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public AccountInfo balance(Amount balance) {
    this.balance = balance;
    return this;
  }

   /**
   * Get balance
   * @return balance
  **/
  @ApiModelProperty(value = "")
  public Amount getBalance() {
    return balance;
  }

  public void setBalance(Amount balance) {
    this.balance = balance;
  }

  public AccountInfo importance(Double importance) {
    this.importance = importance;
    return this;
  }

   /**
   * Get importance
   * @return importance
  **/
  @ApiModelProperty(value = "")
  public Double getImportance() {
    return importance;
  }

  public void setImportance(Double importance) {
    this.importance = importance;
  }

  public AccountInfo keyPair(KeyPair keyPair) {
    this.keyPair = keyPair;
    return this;
  }

   /**
   * Get keyPair
   * @return keyPair
  **/
  @ApiModelProperty(value = "")
  public KeyPair getKeyPair() {
    return keyPair;
  }

  public void setKeyPair(KeyPair keyPair) {
    this.keyPair = keyPair;
  }

  public AccountInfo label(String label) {
    this.label = label;
    return this;
  }

   /**
   * Get label
   * @return label
  **/
  @ApiModelProperty(value = "")
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public AccountInfo multisigInfo(MultisigInfo multisigInfo) {
    this.multisigInfo = multisigInfo;
    return this;
  }

   /**
   * Get multisigInfo
   * @return multisigInfo
  **/
  @ApiModelProperty(value = "")
  public MultisigInfo getMultisigInfo() {
    return multisigInfo;
  }

  public void setMultisigInfo(MultisigInfo multisigInfo) {
    this.multisigInfo = multisigInfo;
  }

  public AccountInfo numHarvestedBlocks(BlockAmount numHarvestedBlocks) {
    this.numHarvestedBlocks = numHarvestedBlocks;
    return this;
  }

   /**
   * Get numHarvestedBlocks
   * @return numHarvestedBlocks
  **/
  @ApiModelProperty(value = "")
  public BlockAmount getNumHarvestedBlocks() {
    return numHarvestedBlocks;
  }

  public void setNumHarvestedBlocks(BlockAmount numHarvestedBlocks) {
    this.numHarvestedBlocks = numHarvestedBlocks;
  }

  public AccountInfo vestedBalance(Amount vestedBalance) {
    this.vestedBalance = vestedBalance;
    return this;
  }

   /**
   * Get vestedBalance
   * @return vestedBalance
  **/
  @ApiModelProperty(value = "")
  public Amount getVestedBalance() {
    return vestedBalance;
  }

  public void setVestedBalance(Amount vestedBalance) {
    this.vestedBalance = vestedBalance;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountInfo accountInfo = (AccountInfo) o;
    return Objects.equals(this.address, accountInfo.address) &&
        Objects.equals(this.balance, accountInfo.balance) &&
        Objects.equals(this.importance, accountInfo.importance) &&
        Objects.equals(this.keyPair, accountInfo.keyPair) &&
        Objects.equals(this.label, accountInfo.label) &&
        Objects.equals(this.multisigInfo, accountInfo.multisigInfo) &&
        Objects.equals(this.numHarvestedBlocks, accountInfo.numHarvestedBlocks) &&
        Objects.equals(this.vestedBalance, accountInfo.vestedBalance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, balance, importance, keyPair, label, multisigInfo, numHarvestedBlocks, vestedBalance);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountInfo {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    importance: ").append(toIndentedString(importance)).append("\n");
    sb.append("    keyPair: ").append(toIndentedString(keyPair)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    multisigInfo: ").append(toIndentedString(multisigInfo)).append("\n");
    sb.append("    numHarvestedBlocks: ").append(toIndentedString(numHarvestedBlocks)).append("\n");
    sb.append("    vestedBalance: ").append(toIndentedString(vestedBalance)).append("\n");
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

