package io.nem.xpx.builder.steps;

import java.util.Map;

public interface MetadataStep<T> {
    T metadata(Map<String, String> metadata);
}
