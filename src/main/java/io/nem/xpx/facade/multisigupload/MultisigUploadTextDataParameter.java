package io.nem.xpx.facade.multisigupload;

import io.nem.xpx.builder.steps.MultisigPublicKeyStep;
import io.nem.xpx.builder.steps.ReceiverOrSenderPublicKeyStep;
import io.nem.xpx.builder.steps.SenderOrReceiverPrivateKeyStep;
import io.nem.xpx.builder.steps.TextDataStep;
import io.nem.xpx.facade.upload.UploadTextDataParameter;
import io.nem.xpx.utils.ContentTypeUtils;
import io.nem.xpx.utils.StringUtils;

import java.io.Serializable;

public class MultisigUploadTextDataParameter extends UploadTextDataParameter implements Serializable {

    private String multisigPublicKey;

    public String getMultisigPublicKey() {
        return multisigPublicKey;
    }

    public void setMultisigPublicKey(String multisigPublicKey) {
        this.multisigPublicKey = multisigPublicKey;
    }

    public static MultisigPublicKeyStep<SenderOrReceiverPrivateKeyStep<ReceiverOrSenderPublicKeyStep<TextDataStep<FinalBuildSteps>>>> createMultisigParam() {
        return new Builder();
    }


    private static class Builder extends UploadTextDataParameter.Builder
            implements MultisigPublicKeyStep {

        MultisigUploadTextDataParameter instance;

        private Builder() {
            super(new MultisigUploadTextDataParameter());
            this.instance = (MultisigUploadTextDataParameter) super.instance;
        }

        @Override
        public SenderOrReceiverPrivateKeyStep multisigPublicKeyStep(String multisigPublicKeyStep) {
            this.instance.setMultisigPublicKey(multisigPublicKeyStep);
            return this;
        }

        @Override
        public MultisigUploadTextDataParameter build() {
            if (StringUtils.isEmpty(this.instance.getContentType()))
                this.instance.setContentType(ContentTypeUtils.detectContentType(this.instance.getData()));
            return instance;
        }
    }

}
