package io.nem.xpx.builder.steps;


/**
 * The Interface PathStep.
 *
 * @param <T> the generic type
 */
public interface PathStep<T> {
    
    /**
     * Path.
     *
     * @param path the path
     * @return the t
     */
    T path(String path);
}
