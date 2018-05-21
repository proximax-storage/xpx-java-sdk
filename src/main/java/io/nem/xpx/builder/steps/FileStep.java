package io.nem.xpx.builder.steps;

import java.io.File;
import java.util.List;

public interface FileStep<T> {

    T file(File file);
}
