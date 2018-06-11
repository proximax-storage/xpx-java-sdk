package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.steps.*;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_ZIP;
import static java.util.Arrays.asList;



/**
 * The Class UploadFilesAsZipParameter.
 */
public class UploadFilesAsZipParameter extends AbstractUploadParameter implements Serializable {

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
    public static SenderPrivateKeyStep<ReceiverPublicKeyStep<ZipFileNameStep<FinalBuildSteps>>> create() {
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
         * @return the upload files as zip parameter
         */
        UploadFilesAsZipParameter build();
    }

    /**
     * The Class Builder.
     */
    public static class Builder
            extends AbstractUploadParameterBuilder<ZipFileNameStep, FinalBuildSteps>
            implements ZipFileNameStep, FinalBuildSteps {

        /** The instance. */
        UploadFilesAsZipParameter instance;

        /**
         * Instantiates a new builder.
         */
        private Builder() {
            super(new UploadFilesAsZipParameter());
            this.instance = (UploadFilesAsZipParameter) super.instance;
            this.instance.setContentType(APPLICATION_ZIP.toString());
        }

        /* (non-Javadoc)
         * @see io.nem.xpx.builder.steps.ZipFileNameStep#zipFileName(java.lang.String)
         */
        @Override
        public FinalBuildSteps zipFileName(String name) {
            this.instance.setName(name);
            return this;
        }

        /* (non-Javadoc)
         * @see io.nem.xpx.builder.steps.FilesStep#addFiles(java.io.File[])
         */
        @Override
        public FinalBuildSteps addFiles(File... files) {
            this.instance.addFiles(files);
            return this;
        }

        /* (non-Javadoc)
         * @see io.nem.xpx.builder.steps.FilesStep#addFiles(java.util.List)
         */
        @Override
        public FinalBuildSteps addFiles(List<File> files) {
            this.instance.addFiles(files);
            return this;
        }

        /* (non-Javadoc)
         * @see io.nem.xpx.builder.steps.FilesStep#addFile(java.io.File)
         */
        @Override
        public FinalBuildSteps addFile(File file) {
            this.instance.addFile(file);
            return this;
        }

        /* (non-Javadoc)
         * @see io.nem.xpx.facade.upload.UploadFilesAsZipParameter.FinalBuildSteps#build()
         */
        @Override
        public UploadFilesAsZipParameter build() {
            return instance;
        }

    }
}
