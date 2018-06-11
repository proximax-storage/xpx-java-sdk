package io.nem.xpx.builder.steps;


/**
 * The Interface BinaryDataStep.
 *
 * @param <T> the generic type
 */
public interface BinaryDataStep<T> {
    
    /**
     * Data.
     *
     * @param data the data
     * @return the t
     */
    T data(byte[] data);
}
