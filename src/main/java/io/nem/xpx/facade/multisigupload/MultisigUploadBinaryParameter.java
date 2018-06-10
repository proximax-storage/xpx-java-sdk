package io.nem.xpx.facade.multisigupload;

import io.nem.xpx.builder.steps.*;
import io.nem.xpx.facade.upload.UploadBinaryParameter;
import io.nem.xpx.utils.ContentTypeUtils;
import io.nem.xpx.utils.StringUtils;

import java.io.Serializable;

public class MultisigUploadBinaryParameter extends UploadBinaryParameter implements Serializable {


    private String multisigPublicKey;

    public String getMultisigPublicKey() {
        return multisigPublicKey;
    }

    public void setMultisigPublicKey(String multisigPublicKey) {
        this.multisigPublicKey = multisigPublicKey;
    }


    public static MultisigPublicKeyStep<SenderPrivateKeyStep<ReceiverPublicKeyStep<BinaryDataStep<FinalBuildSteps>>>> createMultisigParam() {
        return new Builder();
    }

    private static class Builder
            extends UploadBinaryParameter.Builder
            implements MultisigPublicKeyStep {

        MultisigUploadBinaryParameter instance;

        private Builder() {
            super(new MultisigUploadBinaryParameter());
            this.instance = (MultisigUploadBinaryParameter) super.instance;
        }

        @Override
        public SenderPrivateKeyStep multisigPublicKeyStep(String multisigPublicKeyStep) {
            this.instance.setMultisigPublicKey(multisigPublicKeyStep);
            return this;
        }

        @Override
        public MultisigUploadBinaryParameter build() {
            if (StringUtils.isEmpty(this.instance.getContentType()))
                this.instance.setContentType(ContentTypeUtils.detectContentType(this.instance.getData()));
            return instance;
        }
    }


}
