package io.nem.xpx.builder.steps;

import io.nem.xpx.strategy.privacy.PlainPrivacyStrategy;
import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import io.nem.xpx.strategy.privacy.SecuredWithNemKeysPrivacyStrategy;
import io.nem.xpx.strategy.privacy.SecuredWithPasswordPrivacyStrategy;

import static java.util.Objects.requireNonNull;

public interface PrivacyStrategyStep<T extends PrivacyStrategyStep<T>> {

    void setPrivacyStrategy(PrivacyStrategy privacyStrategy);

    String getSenderOrReceiverPrivateKey();

    String getReceiverOrSenderPublicKey();

    default T plainPrivacy() {
        setPrivacyStrategy(new PlainPrivacyStrategy());
        return (T) this;
    }

    default T securedWithNemKeysPrivacy() {
        requireNonNull(getSenderOrReceiverPrivateKey(), "private key is required");
        requireNonNull(getReceiverOrSenderPublicKey(), "public key is required");

        setPrivacyStrategy(new SecuredWithNemKeysPrivacyStrategy(getSenderOrReceiverPrivateKey(), getReceiverOrSenderPublicKey()));
        return (T) this;
    }

    default T securedWithNemKeysPrivacy(String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey) {
        requireNonNull(senderOrReceiverPrivateKey, "private key is required");
        requireNonNull(receiverOrSenderPublicKey, "public key is required");

        setPrivacyStrategy(new SecuredWithNemKeysPrivacyStrategy(senderOrReceiverPrivateKey, receiverOrSenderPublicKey));
        return (T) this;
    }

    default T securedWithPasswordPrivacyStrategy(String password) {
        requireNonNull(password, "password is required");

        setPrivacyStrategy(new SecuredWithPasswordPrivacyStrategy(password));
        return (T) this;
    }
}
