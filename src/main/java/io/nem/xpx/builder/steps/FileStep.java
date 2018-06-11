package io.nem.xpx.builder.steps;

import java.io.File;
import java.util.List;


/**
 * The Interface FileStep.
 *
 * @param <T> the generic type
 */
public interface FileStep<T> {

    /**
     * File.
     *
     * @param file the file
     * @return the t
     */
    T file(File file);
}
