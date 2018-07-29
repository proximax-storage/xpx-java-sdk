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
 * 
 */
package io.proximax.xpx.facade.connection;

import io.proximax.xpx.factory.ConnectionFactory;
import io.proximax.xpx.service.*;
import io.proximax.xpx.service.common.FileAndNamingRouteApi;
import io.proximax.xpx.service.intf.*;
import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;


/**
 * The Class PeerConnection.
 */
public abstract class PeerConnection {

    /** The node endpoint. */
    protected NodeEndpoint nodeEndpoint;

    private List<String> syncGateways;

    /** The async proximax connector. */
    private DefaultAsyncNemConnector<ApiId> asyncNemConnector;

    /** The proximax transaction api. */
    private NemTransactionApi nemTransactionApi;

    /** The proximax account api. */
    private NemAccountApi nemAccountApi;

    /** The proximax namespace and mosaics api. */
    private NemNamespaceAndMosaicsApi nemNamespaceAndMosaicsApi;    

    /** The transaction sender. */
    private TransactionSender transactionSender;

    /** The transaction fee calculators. */
    private TransactionFeeCalculators transactionFeeCalculators;

    /** The transaction announcer. */
    private TransactionAnnouncer transactionAnnouncer;

    public PeerConnection() {
        syncGateways = emptyList();
    }

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
    
    public abstract FileAndNamingRouteApi getFileAndNamingRouteApi();

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

    protected void setSyncGateways(List<String>... syncGateways) {
        this.syncGateways = unmodifiableList(
                Stream.of(syncGateways)
                        .filter(Objects::nonNull)
                        .flatMap(list -> list.stream())
                        .collect(Collectors.toList()));
    }

    /**
     * Gets the sync gateways.
     *
     * @return list of gateway URLs where uploads will be sync
     */
    public List<String> getSyncGateways() {
        return syncGateways;
    }

    /**
     * Gets the proximax transaction api.
     *
     * @return the proximax transaction api
     */
    public NemTransactionApi getNemTransactionApi() {
        if (nemTransactionApi == null)
            nemTransactionApi = new NemTransactionApi(nodeEndpoint, getAsyncNemConnector());
        return nemTransactionApi;
    }

    /**
     * Gets the proximax account api.
     *
     * @return the proximax account api
     */
    public NemAccountApi getNemAccountApi() {
        if (nemAccountApi == null)
            nemAccountApi = new NemAccountApi(nodeEndpoint, getAsyncNemConnector());
        return nemAccountApi;
    }

    /**
     * Gets the proximax namespace and mosaics api.
     *
     * @return the proximax namespace and mosaics api
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
     * Gets the async proximax connector.
     *
     * @return the async proximax connector
     */
    private DefaultAsyncNemConnector<ApiId> getAsyncNemConnector() {
        if (asyncNemConnector == null)
            asyncNemConnector = ConnectionFactory.createConnector();
        return asyncNemConnector;
    }





}
