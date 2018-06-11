package io.nem.xpx.builder.steps;

import org.nem.core.model.mosaic.Mosaic;


/**
 * The Interface MosaicsStep.
 *
 * @param <T> the generic type
 */
public interface MosaicsStep<T> {
    
    /**
     * Mosaics.
     *
     * @param mosaics the mosaics
     * @return the t
     */
    T mosaics(Mosaic... mosaics);
}
