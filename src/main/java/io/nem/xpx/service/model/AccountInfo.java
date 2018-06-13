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


package io.nem.xpx.service.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;


/**
 * AccountInfo.
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-04-25T23:45:59.064-04:00")
public class AccountInfo {
  
  /** The address. */
  @SerializedName("address")
  private Address address = null;

  /** The balance. */
  @SerializedName("balance")
  private Amount balance = null;

  /** The importance. */
  @SerializedName("importance")
  private Double importance = null;

  /** The key pair. */
  @SerializedName("keyPair")
  private KeyPair keyPair = null;

  /** The label. */
  @SerializedName("label")
  private String label = null;

  /** The multisig info. */
  @SerializedName("multisigInfo")
  private MultisigInfo multisigInfo = null;

  /** The num harvested blocks. */
  @SerializedName("numHarvestedBlocks")
  private BlockAmount numHarvestedBlocks = null;

  /** The vested balance. */
  @SerializedName("vestedBalance")
  private Amount vestedBalance = null;

  /**
   * Address.
   *
   * @param address the address
   * @return the account info
   */
  public AccountInfo address(Address address) {
    this.address = address;
    return this;
  }

   /**
    * Get address.
    *
    * @return address
    */
  @ApiModelProperty(value = "")
  public Address getAddress() {
    return address;
  }

  /**
   * Sets the address.
   *
   * @param address the new address
   */
  public void setAddress(Address address) {
    this.address = address;
  }

  /**
   * Balance.
   *
   * @param balance the balance
   * @return the account info
   */
  public AccountInfo balance(Amount balance) {
    this.balance = balance;
    return this;
  }

   /**
    * Get balance.
    *
    * @return balance
    */
  @ApiModelProperty(value = "")
  public Amount getBalance() {
    return balance;
  }

  /**
   * Sets the balance.
   *
   * @param balance the new balance
   */
  public void setBalance(Amount balance) {
    this.balance = balance;
  }

  /**
   * Importance.
   *
   * @param importance the importance
   * @return the account info
   */
  public AccountInfo importance(Double importance) {
    this.importance = importance;
    return this;
  }

   /**
    * Get importance.
    *
    * @return importance
    */
  @ApiModelProperty(value = "")
  public Double getImportance() {
    return importance;
  }

  /**
   * Sets the importance.
   *
   * @param importance the new importance
   */
  public void setImportance(Double importance) {
    this.importance = importance;
  }

  /**
   * Key pair.
   *
   * @param keyPair the key pair
   * @return the account info
   */
  public AccountInfo keyPair(KeyPair keyPair) {
    this.keyPair = keyPair;
    return this;
  }

   /**
    * Get keyPair.
    *
    * @return keyPair
    */
  @ApiModelProperty(value = "")
  public KeyPair getKeyPair() {
    return keyPair;
  }

  /**
   * Sets the key pair.
   *
   * @param keyPair the new key pair
   */
  public void setKeyPair(KeyPair keyPair) {
    this.keyPair = keyPair;
  }

  /**
   * Label.
   *
   * @param label the label
   * @return the account info
   */
  public AccountInfo label(String label) {
    this.label = label;
    return this;
  }

   /**
    * Get label.
    *
    * @return label
    */
  @ApiModelProperty(value = "")
  public String getLabel() {
    return label;
  }

  /**
   * Sets the label.
   *
   * @param label the new label
   */
  public void setLabel(String label) {
    this.label = label;
  }

  /**
   * Multisig info.
   *
   * @param multisigInfo the multisig info
   * @return the account info
   */
  public AccountInfo multisigInfo(MultisigInfo multisigInfo) {
    this.multisigInfo = multisigInfo;
    return this;
  }

   /**
    * Get multisigInfo.
    *
    * @return multisigInfo
    */
  @ApiModelProperty(value = "")
  public MultisigInfo getMultisigInfo() {
    return multisigInfo;
  }

  /**
   * Sets the multisig info.
   *
   * @param multisigInfo the new multisig info
   */
  public void setMultisigInfo(MultisigInfo multisigInfo) {
    this.multisigInfo = multisigInfo;
  }

  /**
   * Num harvested blocks.
   *
   * @param numHarvestedBlocks the num harvested blocks
   * @return the account info
   */
  public AccountInfo numHarvestedBlocks(BlockAmount numHarvestedBlocks) {
    this.numHarvestedBlocks = numHarvestedBlocks;
    return this;
  }

   /**
    * Get numHarvestedBlocks.
    *
    * @return numHarvestedBlocks
    */
  @ApiModelProperty(value = "")
  public BlockAmount getNumHarvestedBlocks() {
    return numHarvestedBlocks;
  }

  /**
   * Sets the num harvested blocks.
   *
   * @param numHarvestedBlocks the new num harvested blocks
   */
  public void setNumHarvestedBlocks(BlockAmount numHarvestedBlocks) {
    this.numHarvestedBlocks = numHarvestedBlocks;
  }

  /**
   * Vested balance.
   *
   * @param vestedBalance the vested balance
   * @return the account info
   */
  public AccountInfo vestedBalance(Amount vestedBalance) {
    this.vestedBalance = vestedBalance;
    return this;
  }

   /**
    * Get vestedBalance.
    *
    * @return vestedBalance
    */
  @ApiModelProperty(value = "")
  public Amount getVestedBalance() {
    return vestedBalance;
  }

  /**
   * Sets the vested balance.
   *
   * @param vestedBalance the new vested balance
   */
  public void setVestedBalance(Amount vestedBalance) {
    this.vestedBalance = vestedBalance;
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

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return Objects.hash(address, balance, importance, keyPair, label, multisigInfo, numHarvestedBlocks, vestedBalance);
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
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
