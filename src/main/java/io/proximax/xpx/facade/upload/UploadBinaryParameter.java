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
import io.proximax.xpx.utils.ContentTypeUtils;
import io.proximax.xpx.utils.StringUtils;

import java.io.Serializable;


/**
 * The Class UploadBinaryParameter.
 */
public class UploadBinaryParameter extends AbstractUploadParameter implements Serializable {


    /** The data. */
    private byte[] data;

    /**
     * Gets the data.
     *
     * @return the data
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Sets the data.
     *
     * @param data the new data
     */
    public void setData(byte[] data) {
        this.data = data;
    }

    /**
     * Creates the.
     *
     * @return the sender private key step
     */
    public static SenderPrivateKeyStep<ReceiverPublicKeyStep<BinaryDataStep<FinalBuildSteps>>> create() {
        return new Builder();
    }

    /**
     * The Interface FinalBuildSteps.
     */
    public interface FinalBuildSteps extends
            ContentTypeStep<FinalBuildSteps>,
            NameStep<FinalBuildSteps>,
            CommonUploadBuildSteps<FinalBuildSteps> {

        /**
         * Builds the.
         *
         * @return the upload binary parameter
         */
        UploadBinaryParameter build();
    }

    /**
     * The Class Builder.
     */
    public static class Builder
            extends AbstractUploadParameterBuilder<BinaryDataStep, FinalBuildSteps>
            implements BinaryDataStep, FinalBuildSteps {

        /** The instance. */
        protected UploadBinaryParameter instance;

        /**
         * Instantiates a new builder.
         */
        private Builder() {
            super(new UploadBinaryParameter());
            this.instance = (UploadBinaryParameter) super.instance;
        }

        /**
         * Instantiates a new builder.
         *
         * @param instance the instance
         */
        protected Builder(UploadBinaryParameter instance) {
            super(instance);
            this.instance = instance;
        }

        /* (non-Javadoc)
         * @see io.proximax.xpx.builder.steps.BinaryDataStep#data(byte[])
         */
        @Override
        public FinalBuildSteps data(byte[] data) {
            this.instance.setData(data);
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

        /* (non-Javadoc)
         * @see io.proximax.xpx.builder.steps.NameStep#name(java.lang.String)
         */
        @Override
        public FinalBuildSteps name(String name) {
            this.instance.setName(name);
            return this;
        }

        /* (non-Javadoc)
         * @see io.proximax.xpx.facade.upload.UploadBinaryParameter.FinalBuildSteps#build()
         */
        @Override
        public UploadBinaryParameter build() {
            if (StringUtils.isEmpty(this.instance.getContentType()))
                this.instance.setContentType(ContentTypeUtils.detectContentType(this.instance.data));
            return instance;
        }

    }


}
