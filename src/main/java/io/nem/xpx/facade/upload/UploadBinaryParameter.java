package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.steps.*;
import io.nem.xpx.utils.ContentTypeUtils;
import io.nem.xpx.utils.StringUtils;

import java.io.Serializable;

public class UploadBinaryParameter extends AbstractUploadParameter implements Serializable {


    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public static SenderOrReceiverPrivateKeyStep<ReceiverOrSenderPublicKeyStep<BinaryDataStep<FinalBuildSteps>>> create() {
        return new Builder();
    }

    public interface FinalBuildSteps extends
            ContentTypeStep<FinalBuildSteps>,
            NameStep<FinalBuildSteps>,
            CommonUploadBuildSteps<FinalBuildSteps> {

        UploadBinaryParameter build();
    }

    public static class Builder
            extends AbstractUploadParameterBuilder<BinaryDataStep, FinalBuildSteps>
            implements BinaryDataStep, FinalBuildSteps {

        protected UploadBinaryParameter instance;

        private Builder() {
            super(new UploadBinaryParameter());
            this.instance = (UploadBinaryParameter) super.instance;
        }

        protected Builder(UploadBinaryParameter instance) {
            super(instance);
            this.instance = instance;
        }

        @Override
        public FinalBuildSteps data(byte[] data) {
            this.instance.setData(data);
            return this;
        }

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

        @Override
        public UploadBinaryParameter build() {
            if (StringUtils.isEmpty(this.instance.getContentType()))
                this.instance.setContentType(ContentTypeUtils.detectContentType(this.instance.data));
            return instance;
        }

    }


}
