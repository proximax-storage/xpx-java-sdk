package io.nem.xpx.service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    public HttpClient() {
    }

    public void head(URL url) throws IOException {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("HEAD");
            conn.setDoOutput(true);
            conn.setUseCaches(false);

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }
}
