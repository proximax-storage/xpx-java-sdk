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
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;




/**
 * The Class ProgressResponseBody.
 */
public class ProgressResponseBody extends ResponseBody {

    /**
     * The listener interface for receiving progress events.
     * The class that is interested in processing a progress
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addProgressListener</code> method. When
     * the progress event occurs, that object's appropriate
     * method is invoked.
     *
     */
    public interface ProgressListener {
        
        /**
         * Update.
         *
         * @param bytesRead the bytes read
         * @param contentLength the content length
         * @param done the done
         */
        void update(long bytesRead, long contentLength, boolean done);
    }

    /** The response body. */
    private final ResponseBody responseBody;
    
    /** The progress listener. */
    private final ProgressListener progressListener;
    
    /** The buffered source. */
    private BufferedSource bufferedSource;

    /**
     * Instantiates a new progress response body.
     *
     * @param responseBody the response body
     * @param progressListener the progress listener
     */
    public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = progressListener;
    }

    /* (non-Javadoc)
     * @see com.squareup.okhttp.ResponseBody#contentType()
     */
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    /* (non-Javadoc)
     * @see com.squareup.okhttp.ResponseBody#contentLength()
     */
    @Override
    public long contentLength() throws IOException {
        return responseBody.contentLength();
    }

    /* (non-Javadoc)
     * @see com.squareup.okhttp.ResponseBody#source()
     */
    @Override
    public BufferedSource source() throws IOException {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    /**
     * Source.
     *
     * @param source the source
     * @return the source
     */
    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                return bytesRead;
            }
        };
    }
}


