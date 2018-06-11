package io.nem.xpx.builder.steps;

import java.io.File;
import java.util.List;


/**
 * The Interface FilesStep.
 *
 * @param <T> the generic type
 */
public interface FilesStep<T> {

    /**
     * Adds the files.
     *
     * @param files the files
     * @return the t
     */
    T addFiles(File... files);

    /**
     * Adds the files.
     *
     * @param files the files
     * @return the t
     */
    T addFiles(List<File> files);

    /**
     * Adds the file.
     *
     * @param file the file
     * @return the t
     */
    T addFile(File file);
}
