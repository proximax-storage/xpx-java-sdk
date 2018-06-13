package io.nem.xpx.builder.steps;


/**
 * The Interface EncodingStep.
 *
 * @param <T> the generic type
 */
public interface EncodingStep<T> {
    
    /**
     * Encoding.
     *
     * @param encoding the encoding
     * @return the t
     */
    T encoding(String encoding);
}
