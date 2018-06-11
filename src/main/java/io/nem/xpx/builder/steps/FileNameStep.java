package io.nem.xpx.builder.steps;


/**
 * The Interface FileNameStep.
 *
 * @param <T> the generic type
 */
public interface FileNameStep<T> {
    
    /**
     * File name.
     *
     * @param fileName the file name
     * @return the t
     */
    T fileName(String fileName);
}
