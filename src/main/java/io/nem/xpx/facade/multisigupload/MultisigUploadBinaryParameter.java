package io.nem.xpx.facade.multisigupload;

import io.nem.xpx.builder.steps.*;
import io.nem.xpx.facade.upload.UploadBinaryParameter;
import io.nem.xpx.utils.ContentTypeUtils;
import io.nem.xpx.utils.StringUtils;

import java.io.Serializable;


/**
 * The Class MultisigUploadBinaryParameter.
 */
public class MultisigUploadBinaryParameter extends UploadBinaryParameter implements Serializable {


    /** The multisig public key. */
    private String multisigPublicKey;

    /**
     * Gets the multisig public key.
     *
     * @return the multisig public key
     */
    public String getMultisigPublicKey() {
        return multisigPublicKey;
    }

    /**
     * Sets the multisig public key.
     *
     * @param multisigPublicKey the new multisig public key
     */
    public void setMultisigPublicKey(String multisigPublicKey) {
        this.multisigPublicKey = multisigPublicKey;
    }


    /**
     * Creates the multisig param.
     *
     * @return the multisig public key step
     */
    public static MultisigPublicKeyStep<SenderPrivateKeyStep<ReceiverPublicKeyStep<BinaryDataStep<FinalBuildSteps>>>> createMultisigParam() {
        return new Builder();
    }

    /**
     * The Class Builder.
     */
    private static class Builder
            extends UploadBinaryParameter.Builder
            implements MultisigPublicKeyStep {

        /** The instance. */
        MultisigUploadBinaryParameter instance;

        /**
         * Instantiates a new builder.
         */
        private Builder() {
            super(new MultisigUploadBinaryParameter());
            this.instance = (MultisigUploadBinaryParameter) super.instance;
        }

        /* (non-Javadoc)
         * @see io.nem.xpx.builder.steps.MultisigPublicKeyStep#multisigPublicKeyStep(java.lang.String)
         */
        @Override
        public SenderPrivateKeyStep multisigPublicKeyStep(String multisigPublicKeyStep) {
            this.instance.setMultisigPublicKey(multisigPublicKeyStep);
            return this;
        }

        /* (non-Javadoc)
         * @see io.nem.xpx.facade.upload.UploadBinaryParameter.Builder#build()
         */
        @Override
        public MultisigUploadBinaryParameter build() {
            if (StringUtils.isEmpty(this.instance.getContentType()))
                this.instance.setContentType(ContentTypeUtils.detectContentType(this.instance.getData()));
            return instance;
        }
    }


}
