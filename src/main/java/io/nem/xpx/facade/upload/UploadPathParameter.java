package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.steps.*;

import java.io.Serializable;



/**
 * The Class UploadPathParameter.
 */
public class UploadPathParameter extends AbstractUploadParameter implements Serializable {


    /** The path. */
    private String path;

    /**
     * Gets the path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path.
     *
     * @param path the new path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Creates the.
     *
     * @return the sender private key step
     */
    public static SenderPrivateKeyStep<ReceiverPublicKeyStep<PathStep<FinalBuildSteps>>> create() {
        return new Builder();
    }

    /**
     * The Interface FinalBuildSteps.
     */
    public interface FinalBuildSteps extends CommonUploadBuildSteps<FinalBuildSteps> {

        /**
         * Builds the.
         *
         * @return the upload path parameter
         */
        UploadPathParameter build();
    }

    /**
     * The Class Builder.
     */
    public static class Builder
            extends AbstractUploadParameterBuilder<PathStep, FinalBuildSteps>
            implements PathStep, FinalBuildSteps {

        /** The instance. */
        UploadPathParameter instance;

        /**
         * Instantiates a new builder.
         */
        private Builder() {
            super(new UploadPathParameter());
            this.instance = (UploadPathParameter) super.instance;
        }

        /* (non-Javadoc)
         * @see io.nem.xpx.builder.steps.PathStep#path(java.lang.String)
         */
        @Override
        public FinalBuildSteps path(String path) {
            this.instance.setPath(path);
            return this;
        }

        /* (non-Javadoc)
         * @see io.nem.xpx.facade.upload.UploadPathParameter.FinalBuildSteps#build()
         */
        @Override
        public UploadPathParameter build() {
            return instance;
        }
    }

}
