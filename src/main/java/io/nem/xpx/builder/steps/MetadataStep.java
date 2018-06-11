package io.nem.xpx.builder.steps;

import java.util.Map;


/**
 * The Interface MetadataStep.
 *
 * @param <T> the generic type
 */
public interface MetadataStep<T> {
    
    /**
     * Metadata.
     *
     * @param metadata the metadata
     * @return the t
     */
    T metadata(Map<String, String> metadata);
}
