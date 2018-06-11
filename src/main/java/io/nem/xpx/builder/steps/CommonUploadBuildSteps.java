package io.nem.xpx.builder.steps;

/**
 * The Interface CommonUploadBuildSteps.
 *
 * @param <T> the generic type
 */
public interface CommonUploadBuildSteps<T>
    extends KeywordsStep<T>,
        MetadataStep<T>,
        MosaicsStep<T>,
        PrivacyStrategyUploadStep<T>{
}
