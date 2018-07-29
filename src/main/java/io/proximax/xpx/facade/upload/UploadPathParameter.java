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



/**
 * The Class UploadPathParameter.
 */
public class UploadPathParameter extends AbstractUploadParameter implements Serializable {


    /** The path. */
    private String path;

    /**
     * Gets the path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path.
     *
     * @param path the new path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Creates the.
     *
     * @return the sender private key step
     */
    public static SenderPrivateKeyStep<ReceiverPublicKeyStep<PathStep<FinalBuildSteps>>> create() {
        return new Builder();
    }

    /**
     * The Interface FinalBuildSteps.
     */
    public interface FinalBuildSteps extends CommonUploadBuildSteps<FinalBuildSteps> {

        /**
         * Builds the.
         *
         * @return the upload path parameter
         */
        UploadPathParameter build();
    }

    /**
     * The Class Builder.
     */
    public static class Builder
            extends AbstractUploadParameterBuilder<PathStep, FinalBuildSteps>
            implements PathStep, FinalBuildSteps {

        /** The instance. */
        UploadPathParameter instance;

        /**
         * Instantiates a new builder.
         */
        private Builder() {
            super(new UploadPathParameter());
            this.instance = (UploadPathParameter) super.instance;
        }

        /* (non-Javadoc)
         * @see io.proximax.xpx.builder.steps.PathStep#path(java.lang.String)
         */
        @Override
        public FinalBuildSteps path(String path) {
            this.instance.setPath(path);
            return this;
        }

        /* (non-Javadoc)
         * @see io.proximax.xpx.facade.upload.UploadPathParameter.FinalBuildSteps#build()
         */
        @Override
        public UploadPathParameter build() {
            return instance;
        }
    }

}
