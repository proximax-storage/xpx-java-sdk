package io.nem.xpx.builder.steps;

import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import io.nem.xpx.strategy.privacy.SecuredWithShamirSecretSharingPrivacyStrategy;

import java.util.List;
import java.util.Map;

import static io.nem.xpx.strategy.privacy.SecuredWithShamirSecretSharingPrivacyStrategy.*;


/**
 * The Interface PrivacyStrategyUploadStep.
 *
 * @param <T> the generic type
 */
public interface PrivacyStrategyUploadStep<T> {

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
     * @return the t
     */
    T securedWithNemKeysPrivacyStrategy();


    /**
     * Secured with nem keys of sender only
     *
     * @return the t
     */
    T securedWithSenderNemKeysPrivacyStrategy();


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
