package io.nem.xpx.builder.steps;


/**
 * The Interface SenderOrReceiverPrivateKeyStep.
 *
 * @param <T> the generic type
 */
public interface SenderOrReceiverPrivateKeyStep<T> {
    
    /**
     * Sender or receiver private key.
     *
     * @param senderOrReceiverPrivateKey the sender or receiver private key
     * @return the t
     */
    T senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey);
}
