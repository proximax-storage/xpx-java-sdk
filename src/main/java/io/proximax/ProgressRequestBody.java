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

package io.proximax;

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
     * component's <code>addProgressRequestListener</code> method. When
     * the progressRequest event occurs, that object's appropriate
     * method is invoked.
     *
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
