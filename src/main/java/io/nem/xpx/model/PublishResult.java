package io.nem.xpx.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.nem.core.model.ncc.NemAnnounceResult;
import io.ipfs.api.MerkleNode;
import io.ipfs.multihash.Multihash;




/**
 * The Class PublishResult.
 */
public class PublishResult implements Serializable {

	/** The id. */
	private String id;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The merkle node. */
	private List<MerkleNode> merkleNode;
	
	/** The multi hash. */
	private List<Multihash> multiHash;

	/** The nem announce result. */
	private NemAnnounceResult nemAnnounceResult;

	/** The challenge message. */
	private String challengeMessage;

	/** The created date. */
	private Date createdDate = new Date();

	/** The asset id. */
	private String assetId;

	/** The block id. */
	private String blockId;

	/** The bd transaction. */
	private String bdTransaction;

	/** The db hash. */
	private String dbHash;

	/**
	 * Gets the multi hash.
	 *
	 * @return the multi hash
	 */
	public List<Multihash> getMultiHash() {
		return multiHash;
	}

	/**
	 * Sets the multi hash.
	 *
	 * @param multiHash the new multi hash
	 */
	public void setMultiHash(List<Multihash> multiHash) {
		this.multiHash = multiHash;
	}

	/**
	 * Gets the asset id.
	 *
	 * @return the asset id
	 */
	public String getAssetId() {
		return assetId;
	}

	/**
	 * Sets the asset id.
	 *
	 * @param assetId
	 *            the new asset id
	 */
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	/**
	 * Gets the block id.
	 *
	 * @return the block id
	 */
	public String getBlockId() {
		return blockId;
	}

	/**
	 * Sets the block id.
	 *
	 * @param blockId
	 *            the new block id
	 */
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	/**
	 * Gets the bd transaction.
	 *
	 * @return the bd transaction
	 */
	public String getBdTransaction() {
		return bdTransaction;
	}

	/**
	 * Sets the bd transaction.
	 *
	 * @param bdTransaction
	 *            the new bd transaction
	 */
	public void setBdTransaction(String bdTransaction) {
		this.bdTransaction = bdTransaction;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate
	 *            the new created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the db hash.
	 *
	 * @return the db hash
	 */
	public String getDbHash() {
		return dbHash;
	}

	/**
	 * Sets the db hash.
	 *
	 * @param dbHash
	 *            the new db hash
	 */
	public void setDbHash(String dbHash) {
		this.dbHash = dbHash;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the challenge message.
	 *
	 * @return the challenge message
	 */
	public String getChallengeMessage() {
		return challengeMessage;
	}

	/**
	 * Sets the challenge message.
	 *
	 * @param challengeMessage
	 *            the new challenge message
	 */
	public void setChallengeMessage(String challengeMessage) {
		this.challengeMessage = challengeMessage;
	}

	/**
	 * Gets the nem announce result.
	 *
	 * @return the nem announce result
	 */
	public NemAnnounceResult getNemAnnounceResult() {
		return nemAnnounceResult;
	}

	/**
	 * Sets the nem announce result.
	 *
	 * @param nemAnnounceResult
	 *            the new nem announce result
	 */
	public void setNemAnnounceResult(NemAnnounceResult nemAnnounceResult) {
		this.nemAnnounceResult = nemAnnounceResult;
	}

	/**
	 * Gets the merkle node.
	 *
	 * @return the merkle node
	 */
	public List<MerkleNode> getMerkleNode() {
		return merkleNode;
	}

	/**
	 * Sets the merkle node.
	 *
	 * @param merkleNode
	 *            the new merkle node
	 */
	public void setMerkleNode(List<MerkleNode> merkleNode) {
		this.merkleNode = merkleNode;
	}
}
