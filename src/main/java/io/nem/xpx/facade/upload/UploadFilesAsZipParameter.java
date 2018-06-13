package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.steps.*;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static io.nem.xpx.model.DataTextContentType.APPLICATION_ZIP;
import static java.util.Arrays.asList;


public class UploadFilesAsZipParameter extends AbstractUploadParameter implements Serializable {

    private List<File> files = new ArrayList<>();

    public List<File> getFiles() {
        return files;
    }

    public void addFiles(List<File> files) {
        this.files.addAll(files);
    }

    public void addFiles(File... files) {
        this.files.addAll(asList(files));
    }

    public void addFile(File file) {
        this.files.add(file);
    }

    public static SenderPrivateKeyStep<ReceiverPublicKeyStep<ZipFileNameStep<FinalBuildSteps>>> create() {
        return new Builder();
    }

    public interface FinalBuildSteps extends
            FilesStep<FinalBuildSteps>,
            CommonUploadBuildSteps<FinalBuildSteps> {

        UploadFilesAsZipParameter build();
    }

    public static class Builder
            extends AbstractUploadParameterBuilder<ZipFileNameStep, FinalBuildSteps>
            implements ZipFileNameStep, FinalBuildSteps {

        UploadFilesAsZipParameter instance;

        private Builder() {
            super(new UploadFilesAsZipParameter());
            this.instance = (UploadFilesAsZipParameter) super.instance;
            this.instance.setContentType(APPLICATION_ZIP.toString());
        }

        @Override
        public FinalBuildSteps zipFileName(String name) {
            this.instance.setName(name);
            return this;
        }

        @Override
        public FinalBuildSteps addFiles(File... files) {
            this.instance.addFiles(files);
            return this;
        }

        @Override
        public FinalBuildSteps addFiles(List<File> files) {
            this.instance.addFiles(files);
            return this;
        }

        @Override
        public FinalBuildSteps addFile(File file) {
            this.instance.addFile(file);
            return this;
        }

        @Override
        public UploadFilesAsZipParameter build() {
            return instance;
        }

    }
}
