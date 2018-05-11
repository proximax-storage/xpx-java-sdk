package io.nem.xpx.builder.steps;

import java.io.File;
import java.util.List;

import static java.util.Arrays.asList;

public interface FilesStep<T extends FilesStep<T>> {

    List<File> getFiles();

    default T addFiles(File... files) {
        getFiles().addAll(asList(files));
        return (T) this;
    }

    default T addFiles(List<File> files) {
        getFiles().addAll(files);
        return (T) this;
    }

    default T addFile(File file) {
        getFiles().add(file);
        return (T) this;
    }

}
