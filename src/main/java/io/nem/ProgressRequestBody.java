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


package io.nem;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;

import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;



/**
 * The Class ProgressRequestBody.
 */
public class ProgressRequestBody extends RequestBody {

    /**
     * The listener interface for receiving progressRequest events.
     * The class that is interested in processing a progressRequest
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addProgressRequestListener<code> method. When
     * the progressRequest event occurs, that object's appropriate
     * method is invoked.
     *
     * @see ProgressRequestEvent
     */
    public interface ProgressRequestListener {
        
        /**
         * On request progress.
         *
         * @param bytesWritten the bytes written
         * @param contentLength the content length
         * @param done the done
         */
        void onRequestProgress(long bytesWritten, long contentLength, boolean done);
    }

    /** The request body. */
    private final RequestBody requestBody;

    /** The progress listener. */
    private final ProgressRequestListener progressListener;

    /**
     * Instantiates a new progress request body.
     *
     * @param requestBody the request body
     * @param progressListener the progress listener
     */
    public ProgressRequestBody(RequestBody requestBody, ProgressRequestListener progressListener) {
        this.requestBody = requestBody;
        this.progressListener = progressListener;
    }

    /* (non-Javadoc)
     * @see com.squareup.okhttp.RequestBody#contentType()
     */
    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    /* (non-Javadoc)
     * @see com.squareup.okhttp.RequestBody#contentLength()
     */
    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    /* (non-Javadoc)
     * @see com.squareup.okhttp.RequestBody#writeTo(okio.BufferedSink)
     */
    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        BufferedSink bufferedSink = Okio.buffer(sink(sink));
        requestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    /**
     * Sink.
     *
     * @param sink the sink
     * @return the sink
     */
    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {

            long bytesWritten = 0L;
            long contentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    contentLength = contentLength();
                }

                bytesWritten += byteCount;
                progressListener.onRequestProgress(bytesWritten, contentLength, bytesWritten == contentLength);
            }
        };
    }
}
