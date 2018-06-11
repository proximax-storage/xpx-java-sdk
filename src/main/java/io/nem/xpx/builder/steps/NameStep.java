package io.nem.xpx.builder.steps;

import java.io.File;


/**
 * The Interface NameStep.
 *
 * @param <T> the generic type
 */
public interface NameStep<T> {

    /**
     * Name.
     *
     * @param name the name
     * @return the t
     */
    T name(String name);
}
