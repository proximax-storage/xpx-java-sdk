/*
 * 
 */
package io.nem.xpx.facade.connection;

import io.nem.xpx.service.NemAccountApi;
import io.nem.xpx.service.NemTransactionApi;
import io.nem.xpx.service.intf.*;
import io.nem.xpx.utils.TransactionSender;
import org.nem.core.node.NodeEndpoint;


/**
 * The Interface PeerConnection.
 */
public interface PeerConnection {

    /**
     * Gets the node endpoint.
     *
     * @return the node endpoint
     */
    NodeEndpoint getNodeEndpoint();

    /**
     * Checks if is local.
     *
     * @return true, if is local
     */
    boolean isLocal();

    /**
     * Gets the account api.
     *
     * @return the account api
     */
    AccountApi getAccountApi();

    /**
     * Gets the data hash api.
     *
     * @return the data hash api
     */
    DataHashApi getDataHashApi();

    /**
     * Gets the directory load api.
     *
     * @return the directory load api
     */
    DirectoryLoadApi getDirectoryLoadApi();

    /**
     * Gets the download api.
     *
     * @return the download api
     */
    DownloadApi getDownloadApi();

    /**
     * Gets the node api.
     *
     * @return the node api
     */
    NodeApi getNodeApi();

    /**
     * Gets the publish and subscribe api.
     *
     * @return the publish and subscribe api
     */
    PublishAndSubscribeApi getPublishAndSubscribeApi();

    /**
     * Gets the search api.
     *
     * @return the search api
     */
    SearchApi getSearchApi();

    /**
     * Gets the transaction and announce api.
     *
     * @return the transaction and announce api
     */
    TransactionAndAnnounceApi getTransactionAndAnnounceApi();

    /**
     * Gets the upload api.
     *
     * @return the upload api
     */
    UploadApi getUploadApi();

    /**
     * Gets the nem transaction api.
     *
     * @return the nem transaction api
     */
    NemTransactionApi getNemTransactionApi();

    /**
     * Gets the nem account api.
     *
     * @return the nem account api
     */
    NemAccountApi getNemAccountApi();

    /**
     * Gets the transaction sender.
     *
     * @return the transaction sender
     */
    TransactionSender getTransactionSender();

}
