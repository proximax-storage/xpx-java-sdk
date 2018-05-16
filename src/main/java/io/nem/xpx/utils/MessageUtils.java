package io.nem.xpx.utils;

import org.nem.core.crypto.*;
import org.nem.core.model.Address;

public class MessageUtils {

    private static final CryptoEngine engine = CryptoEngines.ed25519Engine();

    private MessageUtils() {
    }

    public static Address getNemAddressFromPrivateKey(final String privateKeyString) {
        final PrivateKey privateKey = PrivateKey.fromHexString(privateKeyString);
        final KeyPair keyPair = new KeyPair(privateKey);
        return Address.fromPublicKey(keyPair.getPublicKey());
    }

    public static byte[] encryptDataWithNemKeys(String senderPrivateKey, String receiverPublicKey, byte[] data) {
        return engine.createBlockCipher(
                new KeyPair(PrivateKey.fromHexString(senderPrivateKey), engine),
                new KeyPair(PublicKey.fromHexString(receiverPublicKey), engine))
                .encrypt(data);
    }

    public static byte[] decryptDataWithSenderPrivateNemKey(String senderPrivateKey, String receiverPublicKey, byte[] data) {
        return engine.createBlockCipher(
                new KeyPair(PublicKey.fromHexString(receiverPublicKey), engine),
                new KeyPair(PrivateKey.fromHexString(senderPrivateKey), engine))
                .decrypt(data);
    }

    public static byte[] decryptDataWithReceiverPrivateNemKey(String receiverPrivateKey, String senderPublicKey, byte[] data) {
        return engine.createBlockCipher(
                new KeyPair(PublicKey.fromHexString(senderPublicKey), engine),
                new KeyPair(PrivateKey.fromHexString(receiverPrivateKey), engine))
                .decrypt(data);
    }
}
