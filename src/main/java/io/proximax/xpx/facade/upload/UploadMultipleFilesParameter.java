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

import io.proximax.xpx.builder.steps.CommonUploadBuildSteps;
import io.proximax.xpx.builder.steps.FilesStep;
import io.proximax.xpx.builder.steps.ReceiverPublicKeyStep;
import io.proximax.xpx.builder.steps.SenderPrivateKeyStep;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;



/**
 * The Class UploadMultipleFilesParameter.
 */
public class UploadMultipleFilesParameter extends AbstractUploadParameter implements Serializable {

    /** The files. */
    private List<File> files = new ArrayList<>();

    /**
     * Gets the files.
     *
     * @return the files
     */
    public List<File> getFiles() {
        return files;
    }

    /**
     * Adds the files.
     *
     * @param files the files
     */
    public void addFiles(List<File> files) {
        this.files.addAll(files);
    }

    /**
     * Adds the files.
     *
     * @param files the files
     */
    public void addFiles(File... files) {
        this.files.addAll(asList(files));
    }

    /**
     * Adds the file.
     *
     * @param file the file
     */
    public void addFile(File file) {
        this.files.add(file);
    }

    /**
     * Creates the.
     *
     * @return the sender private key step
     */
    public static SenderPrivateKeyStep<ReceiverPublicKeyStep<FinalBuildSteps>> create() {
        return new Builder();
    }

    /**
     * The Interface FinalBuildSteps.
     */
    public interface FinalBuildSteps extends
            FilesStep<FinalBuildSteps>,
            CommonUploadBuildSteps<FinalBuildSteps> {

        /**
         * Builds the.
         *
         * @return the upload multiple files parameter
         */
        UploadMultipleFilesParameter build();
    }

    /**
     * The Class Builder.
     */
    public static class Builder
            extends AbstractUploadParameterBuilder<FinalBuildSteps, FinalBuildSteps>
            implements FinalBuildSteps {

        /** The instance. */
        private UploadMultipleFilesParameter instance;

        /**
         * Instantiates a new builder.
         */
        private Builder() {
            super(new UploadMultipleFilesParameter());
            this.instance = (UploadMultipleFilesParameter) super.instance;
        }

        /* (non-Javadoc)
         * @see io.proximax.xpx.builder.steps.FilesStep#addFiles(java.io.File[])
         */
        @Override
        public FinalBuildSteps addFiles(File... files) {
            this.instance.addFiles(files);
            return this;
        }

        /* (non-Javadoc)
         * @see io.proximax.xpx.builder.steps.FilesStep#addFiles(java.util.List)
         */
        @Override
        public FinalBuildSteps addFiles(List<File> files) {
            this.instance.addFiles(files);
            return this;
        }

        /* (non-Javadoc)
         * @see io.proximax.xpx.builder.steps.FilesStep#addFile(java.io.File)
         */
        @Override
        public FinalBuildSteps addFile(File file) {
            this.instance.addFile(file);
            return this;
        }

        /* (non-Javadoc)
         * @see io.proximax.xpx.facade.upload.UploadMultipleFilesParameter.FinalBuildSteps#build()
         */
        @Override
        public UploadMultipleFilesParameter build() {
            return instance;
        }

    }

}
