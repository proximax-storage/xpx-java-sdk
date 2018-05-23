package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.steps.CommonUploadBuildSteps;
import io.nem.xpx.builder.steps.FilesStep;
import io.nem.xpx.builder.steps.ReceiverOrSenderPublicKeyStep;
import io.nem.xpx.builder.steps.SenderOrReceiverPrivateKeyStep;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;


public class UploadMultipleFilesParameter extends AbstractUploadParameter implements Serializable {

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

    public static SenderOrReceiverPrivateKeyStep
            <ReceiverOrSenderPublicKeyStep
                    <FinalBuildSteps>> create() {
        return new Builder();
    }

    public interface FinalBuildSteps extends
            FilesStep<FinalBuildSteps>,
            CommonUploadBuildSteps<FinalBuildSteps> {

        UploadMultipleFilesParameter build();
    }

    public static class Builder
            extends AbstractUploadParameterBuilder<FinalBuildSteps, FinalBuildSteps>
            implements FinalBuildSteps {

        private UploadMultipleFilesParameter instance;

        private Builder() {
            super(new UploadMultipleFilesParameter());
            this.instance = (UploadMultipleFilesParameter) super.instance;
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
        public UploadMultipleFilesParameter build() {
            return instance;
        }

    }

}
