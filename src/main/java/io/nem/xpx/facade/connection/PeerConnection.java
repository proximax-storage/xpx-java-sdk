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


public abstract class PeerConnection {

    protected NodeEndpoint nodeEndpoint;

    private DefaultAsyncNemConnector<ApiId> asyncNemConnector;

    private NemTransactionApi nemTransactionApi;

    private NemAccountApi nemAccountApi;

    private NemNamespaceAndMosaicsApi nemNamespaceAndMosaicsApi;

    private TransactionSender transactionSender;

    private TransactionFeeCalculators transactionFeeCalculators;

    private TransactionAnnouncer transactionAnnouncer;

    public abstract boolean isLocal();

    public abstract AccountApi getAccountApi();

    public abstract DataHashApi getDataHashApi();

    public abstract DirectoryLoadApi getDirectoryLoadApi();

    public abstract DownloadApi getDownloadApi();

    public abstract NodeApi getNodeApi();

    public abstract PublishAndSubscribeApi getPublishAndSubscribeApi();

    public abstract SearchApi getSearchApi();

    public abstract TransactionAndAnnounceApi getTransactionAndAnnounceApi();

    public abstract UploadApi getUploadApi();

    public NemTransactionApi getNemTransactionApi() {
        if (nemTransactionApi == null)
            nemTransactionApi = new NemTransactionApi(nodeEndpoint, getAsyncNemConnector());
        return nemTransactionApi;
    }

    public NemAccountApi getNemAccountApi() {
        if (nemAccountApi == null)
            nemAccountApi = new NemAccountApi(nodeEndpoint, getAsyncNemConnector());
        return nemAccountApi;
    }

    public NemNamespaceAndMosaicsApi getNemNamespaceAndMosaicsApi() {
        if (nemNamespaceAndMosaicsApi == null)
            nemNamespaceAndMosaicsApi = new NemNamespaceAndMosaicsApi(nodeEndpoint, getAsyncNemConnector());
        return nemNamespaceAndMosaicsApi;
    }

    public TransactionSender getTransactionSender() {
        if (transactionSender == null)
            transactionSender = new TransactionSender(getNemTransactionApi(), getNemAccountApi());
        return transactionSender;
    }

    public TransactionFeeCalculators getTransactionFeeCalculators() {
        if (transactionFeeCalculators == null)
            transactionFeeCalculators = new TransactionFeeCalculators(getNemAccountApi(), getNemNamespaceAndMosaicsApi());
        return transactionFeeCalculators;
    }

    public TransactionAnnouncer getTransactionAnnouncer() {
        if (transactionAnnouncer == null)
            transactionAnnouncer = new TransactionAnnouncer(getTransactionFeeCalculators(), getTransactionSender());
        return transactionAnnouncer;
    }

    private DefaultAsyncNemConnector<ApiId> getAsyncNemConnector() {
        if (asyncNemConnector == null)
            asyncNemConnector = ConnectionFactory.createConnector();
        return asyncNemConnector;
    }





}
