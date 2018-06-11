package io.nem.xpx.builder.steps;


/**
 * The Interface ReceiverPublicKeyStep.
 *
 * @param <T> the generic type
 */
public interface ReceiverPublicKeyStep<T> {
    
    /**
     * Receiver public key.
     *
     * @param receiverPublicKey the receiver public key
     * @return the t
     */
    T receiverPublicKey(String receiverPublicKey);

}
