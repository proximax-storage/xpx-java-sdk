package io.nem.xpx.facade.multisigupload;

import io.nem.xpx.builder.steps.FileStep;
import io.nem.xpx.builder.steps.MultisigPublicKeyStep;
import io.nem.xpx.builder.steps.ReceiverPublicKeyStep;
import io.nem.xpx.builder.steps.SenderPrivateKeyStep;
import io.nem.xpx.facade.upload.UploadFileParameter;
import io.nem.xpx.utils.ContentTypeUtils;
import io.nem.xpx.utils.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.io.Serializable;


public class MultisigUploadFileParameter extends UploadFileParameter implements Serializable {


    private String multisigPublicKey;

    public String getMultisigPublicKey() {
        return multisigPublicKey;
    }

    public void setMultisigPublicKey(String multisigPublicKey) {
        this.multisigPublicKey = multisigPublicKey;
    }

    public static MultisigPublicKeyStep<SenderPrivateKeyStep<ReceiverPublicKeyStep<FileStep<FinalBuildSteps>>>> createMultisigParam() {
        return new Builder();
    }


    private static class Builder extends UploadFileParameter.Builder
            implements MultisigPublicKeyStep {

        MultisigUploadFileParameter instance;

        private Builder() {
            super(new MultisigUploadFileParameter());
            this.instance = (MultisigUploadFileParameter) super.instance;
        }

        @Override
        public SenderPrivateKeyStep multisigPublicKeyStep(String multisigPublicKeyStep) {
            this.instance.setMultisigPublicKey(multisigPublicKeyStep);
            return this;
        }

        @Override
        public MultisigUploadFileParameter build() throws IOException {
            if (StringUtils.isEmpty(this.instance.getName()))
                this.instance.setName(this.instance.getFile().getName());
            if (StringUtils.isEmpty(this.instance.getContentType()))
                this.instance.setContentType(ContentTypeUtils.detectContentType(FileUtils.readFileToByteArray(this.instance.getFile())));
            return instance;
        }
    }

}
