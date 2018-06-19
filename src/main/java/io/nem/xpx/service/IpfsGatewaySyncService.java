package io.nem.xpx.service;

import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.String.format;

public class IpfsGatewaySyncService {

    private List<String> syncGateways;
    private ExecutorService executor;
    private HttpClient httpClient;

    public IpfsGatewaySyncService(List<String> syncGateways) {
        this(syncGateways, new HttpClient());
    }

    IpfsGatewaySyncService(List<String> syncGateways, HttpClient httpClient) {
        this.syncGateways = syncGateways;
        this.executor = Executors.newCachedThreadPool();
        this.httpClient = httpClient;
    }

    public void syncToGatewaysAsynchronously(String dataHash) {
        if (syncGateways == null || syncGateways.isEmpty()) return;

        executor.submit(() -> syncGateways.forEach(gateway -> {
            try {
                final URL gatewayUrl = new URL(gateway + "/ipfs/" + dataHash);
                httpClient.head(gatewayUrl);
            } catch (IOException e) {
                Logger.error(format("Error syncing to gateways %s for hash %s", gateway, dataHash), e);
            }
        }));
    }
}
