package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.steps.*;

import java.io.Serializable;


public class UploadTextDataParameter extends AbstractUploadParameter implements Serializable {

    private static final long serialVersionUID = 1L;
    private String data;
    private String encoding;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public static SenderPrivateKeyStep<ReceiverPublicKeyStep<TextDataStep<FinalBuildSteps>>> create() {
        return new Builder();
    }

    public interface FinalBuildSteps extends
            NameStep<FinalBuildSteps>,
            EncodingStep<FinalBuildSteps>,
            ContentTypeStep<FinalBuildSteps>,
            CommonUploadBuildSteps<FinalBuildSteps> {

        UploadTextDataParameter build();
    }

    public static class Builder
            extends AbstractUploadParameterBuilder<TextDataStep, FinalBuildSteps>
            implements TextDataStep, FinalBuildSteps {

        protected UploadTextDataParameter instance;

        private Builder() {
            super(new UploadTextDataParameter());
            this.instance = (UploadTextDataParameter) super.instance;
        }

        protected Builder(UploadTextDataParameter instance) {
            super(instance);
            this.instance = instance;
        }

        @Override
        public FinalBuildSteps data(String data) {
            this.instance.setData(data);
            return this;
        }

        @Override
        public FinalBuildSteps contentType(String contentType) {
            this.instance.setContentType(contentType);
            return this;
        }

        @Override
        public FinalBuildSteps encoding(String encoding) {
            this.instance.setEncoding(encoding);
            return this;
        }

        @Override
        public FinalBuildSteps name(String name) {
            this.instance.setName(name);
            return this;
        }

        @Override
        public UploadTextDataParameter build() {
            if (instance.getEncoding() == null) {
                instance.setEncoding("UTF-8");
            }
            if (instance.getContentType() == null) {
                instance.setContentType("text/plain");
            }
            return instance;
        }
    }


}
