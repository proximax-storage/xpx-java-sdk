package io.nem.xpx.builder.steps;


/**
 * The Interface NemHashStep.
 *
 * @param <T> the generic type
 */
public interface NemHashStep<T> {

    /**
     * Nem hash.
     *
     * @param nemHash the nem hash
     * @return the t
     */
    T nemHash(String nemHash);
}
