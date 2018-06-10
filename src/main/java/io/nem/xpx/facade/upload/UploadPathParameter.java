package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.steps.*;

import java.io.Serializable;


public class UploadPathParameter extends AbstractUploadParameter implements Serializable {


    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static SenderPrivateKeyStep<ReceiverPublicKeyStep<PathStep<FinalBuildSteps>>> create() {
        return new Builder();
    }

    public interface FinalBuildSteps extends CommonUploadBuildSteps<FinalBuildSteps> {

        UploadPathParameter build();
    }

    public static class Builder
            extends AbstractUploadParameterBuilder<PathStep, FinalBuildSteps>
            implements PathStep, FinalBuildSteps {

        UploadPathParameter instance;

        private Builder() {
            super(new UploadPathParameter());
            this.instance = (UploadPathParameter) super.instance;
        }

        @Override
        public FinalBuildSteps path(String path) {
            this.instance.setPath(path);
            return this;
        }

        @Override
        public UploadPathParameter build() {
            return instance;
        }
    }

}
