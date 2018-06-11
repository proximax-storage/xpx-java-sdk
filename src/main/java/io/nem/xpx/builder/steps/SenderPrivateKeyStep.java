package io.nem.xpx.builder.steps;


/**
 * The Interface SenderPrivateKeyStep.
 *
 * @param <T> the generic type
 */
public interface SenderPrivateKeyStep<T> {
    
    /**
     * Sender private key.
     *
     * @param senderPrivateKey the sender private key
     * @return the t
     */
    T senderPrivateKey(String senderPrivateKey);
}
