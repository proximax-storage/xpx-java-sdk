package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.steps.*;
import io.nem.xpx.utils.ContentTypeUtils;
import io.nem.xpx.utils.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;



/**
 * The Class UploadFileParameter.
 */
public class UploadFileParameter extends AbstractUploadParameter implements Serializable {

    /** The file. */
    private File file;

    /**
     * Gets the file.
     *
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets the file.
     *
     * @param file the new file
     */
    public void setFile(File file) {
        this.file = file;
    }


    /**
     * Creates the.
     *
     * @return the sender private key step
     */
    public static SenderPrivateKeyStep<ReceiverPublicKeyStep<FileStep<FinalBuildSteps>>> create() {
        return new Builder();
    }

    /**
     * The Interface FinalBuildSteps.
     */
    public interface FinalBuildSteps extends
            FileNameStep<FinalBuildSteps>,
            ContentTypeStep<FinalBuildSteps>,
            CommonUploadBuildSteps<FinalBuildSteps> {

        /**
         * Builds the.
         *
         * @return the upload file parameter
         * @throws IOException Signals that an I/O exception has occurred.
         */
        UploadFileParameter build() throws IOException;
    }

    /**
     * The Class Builder.
     */
    public static class Builder
            extends AbstractUploadParameterBuilder<FileStep, FinalBuildSteps>
            implements FileStep, FinalBuildSteps {

        /** The instance. */
        protected UploadFileParameter instance;

        /**
         * Instantiates a new builder.
         */
        private Builder() {
            super(new UploadFileParameter());
            this.instance = (UploadFileParameter) super.instance;
        }

        /**
         * Instantiates a new builder.
         *
         * @param instance the instance
         */
        protected Builder(UploadFileParameter instance) {
            super(instance);
            this.instance = instance;
        }

        /* (non-Javadoc)
         * @see io.nem.xpx.builder.steps.FileStep#file(java.io.File)
         */
        @Override
        public FinalBuildSteps file(File file) {
            this.instance.setFile(file);
            return this;
        }

        /* (non-Javadoc)
         * @see io.nem.xpx.builder.steps.FileNameStep#fileName(java.lang.String)
         */
        @Override
        public FinalBuildSteps fileName(String fileName) {
            this.instance.setName(fileName);
            return this;
        }

        /* (non-Javadoc)
         * @see io.nem.xpx.builder.steps.ContentTypeStep#contentType(java.lang.String)
         */
        @Override
        public FinalBuildSteps contentType(String contentType) {
            this.instance.setContentType(contentType);
            return this;
        }

        /* (non-Javadoc)
         * @see io.nem.xpx.facade.upload.UploadFileParameter.FinalBuildSteps#build()
         */
        @Override
        public UploadFileParameter build() throws IOException {
            if (StringUtils.isEmpty(this.instance.getName()))
                this.instance.setName(this.instance.getFile().getName());
            if (StringUtils.isEmpty(this.instance.getContentType()))
                this.instance.setContentType(ContentTypeUtils.detectContentType(FileUtils.readFileToByteArray(this.instance.getFile())));
            return instance;
        }
    }

}
