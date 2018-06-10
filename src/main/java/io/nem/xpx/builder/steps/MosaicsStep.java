package io.nem.xpx.builder.steps;

import org.nem.core.model.mosaic.Mosaic;

public interface MosaicsStep<T> {
    T mosaics(Mosaic... mosaics);
}
