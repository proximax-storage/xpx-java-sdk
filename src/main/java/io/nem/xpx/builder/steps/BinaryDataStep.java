package io.nem.xpx.builder.steps;

public interface BinaryDataStep<T> {
    T data(byte[] data);
}
