package io.nem.xpx.builder.steps;

public interface MetadataStep<T extends MetadataStep<T>> {
    T metadata(String metadata);
}
