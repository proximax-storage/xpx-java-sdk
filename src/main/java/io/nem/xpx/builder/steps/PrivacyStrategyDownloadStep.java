package io.nem.xpx.builder.steps;

import io.nem.xpx.strategy.privacy.PrivacyStrategy;

public interface PrivacyStrategyDownloadStep<T> {

    T privacyStrategy(PrivacyStrategy privacyStrategy);

    T plainPrivacy();

    T securedWithNemKeysPrivacyStrategy(String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey);

    T securedWithPasswordPrivacyStrategy(String password);
}
