package io.nem.xpx.builder.steps;

import io.nem.xpx.strategy.privacy.PrivacyStrategy;

import java.util.List;
import java.util.Map;

import static io.nem.xpx.strategy.privacy.SecuredWithShamirSecretSharingPrivacyStrategy.SecretPart;


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
     * Secured with sender nem keys privacy strategy.
     *
     * @param senderPrivateKey the sender private key
     * @param receiverPublicKey the receiver public key
     * @return the t
     */
    T securedWithSenderNemKeysPrivacyStrategy(String senderPrivateKey, String receiverPublicKey);

    /**
     * Secured with password privacy strategy.
     *
     * @param password the password
     * @return the t
     */
    T securedWithPasswordPrivacyStrategy(String password);

    T securedWithShamirSecretSharingPrivacyStrategy(int secretTotalPartCount, int secretMinimumPartCountToBuild,
                                                    SecretPart... secretParts);

    T securedWithShamirSecretSharingPrivacyStrategy(int secretTotalPartCount, int secretMinimumPartCountToBuild,
                                                    List<SecretPart> secretParts);

    T securedWithShamirSecretSharingPrivacyStrategy(int secretTotalPartCount, int secretMinimumPartCountToBuild,
                                                    Map<Integer, byte[]> secretParts);
}
