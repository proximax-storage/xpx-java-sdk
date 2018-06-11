package io.nem.xpx.builder.steps;

import io.nem.xpx.strategy.privacy.PrivacyStrategy;


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
     * Secured with password privacy strategy.
     *
     * @param password the password
     * @return the t
     */
    T securedWithPasswordPrivacyStrategy(String password);
}
