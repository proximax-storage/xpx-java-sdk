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

package io.proximax.xpx.facade.upload;

import io.proximax.xpx.builder.steps.*;

import java.io.Serializable;
import java.net.URL;


/**
 * The Class UploadFromUrlParameter.
 */
public class UploadFromUrlParameter extends AbstractUploadParameter implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The data. */
    private URL url;

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    /**
     * Creates the.
     *
     * @return the sender private key step
     */
    public static SenderPrivateKeyStep<ReceiverPublicKeyStep<UrlStep<FinalBuildSteps>>> create() {
        return new Builder();
    }

    /**
     * The Interface FinalBuildSteps.
     */
    public interface FinalBuildSteps extends
            NameStep<FinalBuildSteps>,
            ContentTypeStep<FinalBuildSteps>,
            CommonUploadBuildSteps<FinalBuildSteps> {

        /**
         * Builds the.
         *
         * @return the upload from url parameter
         */
        UploadFromUrlParameter build();
    }

    /**
     * The Class Builder.
     */
    public static class Builder
            extends AbstractUploadParameterBuilder<UrlStep, FinalBuildSteps>
            implements UrlStep, FinalBuildSteps {

        /** The instance. */
        protected UploadFromUrlParameter instance;

        /**
         * Instantiates a new builder.
         */
        private Builder() {
            super(new UploadFromUrlParameter());
            this.instance = (UploadFromUrlParameter) super.instance;
        }

        /**
         * Instantiates a new builder.
         *
         * @param instance the instance
         */
        protected Builder(UploadFromUrlParameter instance) {
            super(instance);
            this.instance = instance;
        }

        /* (non-Javadoc)
         * @see io.proximax.xpx.builder.steps.TextDataStep#data(java.lang.String)
         */
        @Override
        public FinalBuildSteps url(URL url) {
            this.instance.setUrl(url);
            return this;
        }

        /* (non-Javadoc)
         * @see io.proximax.xpx.builder.steps.ContentTypeStep#contentType(java.lang.String)
         */
        @Override
        public FinalBuildSteps contentType(String contentType) {
            this.instance.setContentType(contentType);
            return this;
        }

        @Override
        public FinalBuildSteps name(String name) {
            this.instance.setName(name);
            return this;
        }

        /* (non-Javadoc)
         * @see io.proximax.xpx.facade.upload.UploadTextDataParameter.FinalBuildSteps#build()
         */
        @Override
        public UploadFromUrlParameter build() {
            return instance;
        }
    }


}
