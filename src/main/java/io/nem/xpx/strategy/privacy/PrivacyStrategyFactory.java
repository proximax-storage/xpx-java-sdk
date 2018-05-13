package io.nem.xpx.strategy.privacy;

import static java.util.Objects.requireNonNull;

public class PrivacyStrategyFactory {

    private PrivacyStrategyFactory() {}

    public static PrivacyStrategy plainPrivacy() {
        return new PlainPrivacyStrategy();
    }

    public static PrivacyStrategy securedWithNemKeysPrivacyStrategy(String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey) {
        requireNonNull(senderOrReceiverPrivateKey, "private key is required");
        requireNonNull(receiverOrSenderPublicKey, "public key is required");

        return new SecuredWithNemKeysPrivacyStrategy(senderOrReceiverPrivateKey, receiverOrSenderPublicKey);
    }

    public static PrivacyStrategy  securedWithPasswordPrivacyStrategy(String password) {
        requireNonNull(password, "password is required");

        return new SecuredWithPasswordPrivacyStrategy(password);
    }
}
