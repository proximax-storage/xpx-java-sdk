package io.nem.xpx.builder.steps;

import java.io.File;
import java.util.List;

public interface FilesStep<T> {
    T addFiles(File... files);

    T addFiles(List<File> files);

    T addFile(File file);
}
