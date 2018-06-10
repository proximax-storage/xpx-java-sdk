package io.nem.xpx.strategy.privacy;

import io.nem.xpx.adapters.cipher.BinaryPBKDF2CipherEncryption;

public class PrivacyStrategyFactory {

    public static PrivacyStrategy plainPrivacyStrategy;

    private PrivacyStrategyFactory() {}

    public static PrivacyStrategy plainPrivacy() {
        if (plainPrivacyStrategy == null)
            plainPrivacyStrategy = new PlainPrivacyStrategy();
        return plainPrivacyStrategy;
    }

    public static PrivacyStrategy securedWithNemKeysPrivacyStrategy(String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey) {
        return new SecuredWithNemKeysPrivacyStrategy(senderOrReceiverPrivateKey, receiverOrSenderPublicKey);
    }

    public static PrivacyStrategy securedWithPasswordPrivacyStrategy(String password) {
        return new SecuredWithPasswordPrivacyStrategy(new BinaryPBKDF2CipherEncryption(), password);
    }
}
