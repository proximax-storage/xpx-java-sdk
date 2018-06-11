package io.nem.xpx.builder.steps;

import io.nem.xpx.strategy.privacy.PrivacyStrategy;


/**
 * The Interface PrivacyStrategyDownloadStep.
 *
 * @param <T> the generic type
 */
public interface PrivacyStrategyDownloadStep<T> {

    /**
     * Privacy strategy.
     *
     * @param privacyStrategy the privacy strategy
     * @return the t
     */
    T privacyStrategy(PrivacyStrategy privacyStrategy);

    /**
     * Plain privacy.
     *
     * @return the t
     */
    T plainPrivacy();

    /**
     * Secured with nem keys privacy strategy.
     *
     * @param senderOrReceiverPrivateKey the sender or receiver private key
     * @param receiverOrSenderPublicKey the receiver or sender public key
     * @return the t
     */
    T securedWithNemKeysPrivacyStrategy(String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey);

    /**
     * Secured with password privacy strategy.
     *
     * @param password the password
     * @return the t
     */
    T securedWithPasswordPrivacyStrategy(String password);
}
