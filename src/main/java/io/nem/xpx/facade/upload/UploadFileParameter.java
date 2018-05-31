package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.steps.*;
import io.nem.xpx.utils.ContentTypeUtils;
import io.nem.xpx.utils.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;


public class UploadFileParameter extends AbstractUploadParameter implements Serializable {

    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }


    public static SenderPrivateKeyStep<ReceiverPublicKeyStep<FileStep<FinalBuildSteps>>> create() {
        return new Builder();
    }

    public interface FinalBuildSteps extends
            FileNameStep<FinalBuildSteps>,
            ContentTypeStep<FinalBuildSteps>,
            CommonUploadBuildSteps<FinalBuildSteps> {

        UploadFileParameter build() throws IOException;
    }

    public static class Builder
            extends AbstractUploadParameterBuilder<FileStep, FinalBuildSteps>
            implements FileStep, FinalBuildSteps {

        protected UploadFileParameter instance;

        private Builder() {
            super(new UploadFileParameter());
            this.instance = (UploadFileParameter) super.instance;
        }

        protected Builder(UploadFileParameter instance) {
            super(instance);
            this.instance = instance;
        }

        @Override
        public FinalBuildSteps file(File file) {
            this.instance.setFile(file);
            return this;
        }

        @Override
        public FinalBuildSteps fileName(String fileName) {
            this.instance.setName(fileName);
            return this;
        }

        @Override
        public FinalBuildSteps contentType(String contentType) {
            this.instance.setContentType(contentType);
            return this;
        }

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
