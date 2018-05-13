package io.nem.xpx.builder.steps;

import io.nem.xpx.strategy.privacy.PrivacyStrategy;

public interface PrivacyStrategyStep<T> {

    T privacyStrategy(PrivacyStrategy privacyStrategy);

    T plainPrivacy();

    T securedWithNemKeysPrivacyStrategy();

    T securedWithPasswordPrivacyStrategy(String password);
}
