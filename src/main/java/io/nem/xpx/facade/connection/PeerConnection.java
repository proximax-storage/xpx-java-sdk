/*
 * 
 */
package io.nem.xpx.facade.connection;

import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.service.*;
import io.nem.xpx.service.intf.*;
import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;



/**
 * The Class PeerConnection.
 */
public abstract class PeerConnection {

    /** The node endpoint. */
    protected NodeEndpoint nodeEndpoint;

    /** The async nem connector. */
    private DefaultAsyncNemConnector<ApiId> asyncNemConnector;

    /** The nem transaction api. */
    private NemTransactionApi nemTransactionApi;

    /** The nem account api. */
    private NemAccountApi nemAccountApi;

    /** The nem namespace and mosaics api. */
    private NemNamespaceAndMosaicsApi nemNamespaceAndMosaicsApi;

    /** The transaction sender. */
    private TransactionSender transactionSender;

    /** The transaction fee calculators. */
    private TransactionFeeCalculators transactionFeeCalculators;

    /** The transaction announcer. */
    private TransactionAnnouncer transactionAnnouncer;

    /**
     * Checks if is local.
     *
     * @return true, if is local
     */
    public abstract boolean isLocal();

    /**
     * Gets the account api.
     *
     * @return the account api
     */
    public abstract AccountApi getAccountApi();

    /**
     * Gets the data hash api.
     *
     * @return the data hash api
     */
    public abstract DataHashApi getDataHashApi();

    /**
     * Gets the directory load api.
     *
     * @return the directory load api
     */
    public abstract DirectoryLoadApi getDirectoryLoadApi();

    /**
     * Gets the download api.
     *
     * @return the download api
     */
    public abstract DownloadApi getDownloadApi();

    /**
     * Gets the node api.
     *
     * @return the node api
     */
    public abstract NodeApi getNodeApi();

    /**
     * Gets the publish and subscribe api.
     *
     * @return the publish and subscribe api
     */
    public abstract PublishAndSubscribeApi getPublishAndSubscribeApi();

    /**
     * Gets the search api.
     *
     * @return the search api
     */
    public abstract SearchApi getSearchApi();

    /**
     * Gets the transaction and announce api.
     *
     * @return the transaction and announce api
     */
    public abstract TransactionAndAnnounceApi getTransactionAndAnnounceApi();

    /**
     * Gets the upload api.
     *
     * @return the upload api
     */
    public abstract UploadApi getUploadApi();

    /**
     * Gets the nem transaction api.
     *
     * @return the nem transaction api
     */
    public NemTransactionApi getNemTransactionApi() {
        if (nemTransactionApi == null)
            nemTransactionApi = new NemTransactionApi(nodeEndpoint, getAsyncNemConnector());
        return nemTransactionApi;
    }

    /**
     * Gets the nem account api.
     *
     * @return the nem account api
     */
    public NemAccountApi getNemAccountApi() {
        if (nemAccountApi == null)
            nemAccountApi = new NemAccountApi(nodeEndpoint, getAsyncNemConnector());
        return nemAccountApi;
    }

    /**
     * Gets the nem namespace and mosaics api.
     *
     * @return the nem namespace and mosaics api
     */
    public NemNamespaceAndMosaicsApi getNemNamespaceAndMosaicsApi() {
        if (nemNamespaceAndMosaicsApi == null)
            nemNamespaceAndMosaicsApi = new NemNamespaceAndMosaicsApi(nodeEndpoint, getAsyncNemConnector());
        return nemNamespaceAndMosaicsApi;
    }

    /**
     * Gets the transaction sender.
     *
     * @return the transaction sender
     */
    public TransactionSender getTransactionSender() {
        if (transactionSender == null)
            transactionSender = new TransactionSender(getNemTransactionApi(), getNemAccountApi());
        return transactionSender;
    }

    /**
     * Gets the transaction fee calculators.
     *
     * @return the transaction fee calculators
     */
    public TransactionFeeCalculators getTransactionFeeCalculators() {
        if (transactionFeeCalculators == null)
            transactionFeeCalculators = new TransactionFeeCalculators(getNemAccountApi(), getNemNamespaceAndMosaicsApi());
        return transactionFeeCalculators;
    }

    /**
     * Gets the transaction announcer.
     *
     * @return the transaction announcer
     */
    public TransactionAnnouncer getTransactionAnnouncer() {
        if (transactionAnnouncer == null)
            transactionAnnouncer = new TransactionAnnouncer(getTransactionFeeCalculators(), getTransactionSender());
        return transactionAnnouncer;
    }

    /**
     * Gets the async nem connector.
     *
     * @return the async nem connector
     */
    private DefaultAsyncNemConnector<ApiId> getAsyncNemConnector() {
        if (asyncNemConnector == null)
            asyncNemConnector = ConnectionFactory.createConnector();
        return asyncNemConnector;
    }





}
