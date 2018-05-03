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

    NodeEndpoint getNodeEndpoint();

    boolean isLocal();

    AccountApi getAccountApi();

    DataHashApi getDataHashApi();

    DirectoryLoadApi getDirectoryLoadApi();

    DownloadApi getDownloadApi();

    NodeApi getNodeApi();

    PublishAndSubscribeApi getPublishAndSubscribeApi();

    SearchApi getSearchApi();

    TransactionAndAnnounceApi getTransactionAndAnnounceApi();

    UploadApi getUploadApi();

    NemTransactionApi getNemTransactionApi();

    NemAccountApi getNemAccountApi();

    TransactionSender getTransactionSender();

}
