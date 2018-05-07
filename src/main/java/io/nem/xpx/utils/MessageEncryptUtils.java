package io.nem.xpx.utils;

import org.apache.commons.codec.binary.Base64;
import org.nem.core.crypto.*;
import org.nem.core.model.MessageTypes;

import java.io.UnsupportedEncodingException;

public class MessageEncryptUtils {

    private static final CryptoEngine engine = CryptoEngines.ed25519Engine();

    private MessageEncryptUtils() {
    }

    public static String encryptToString(int messageType, byte[] data,
                           String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey) throws UnsupportedEncodingException {
        if (messageType == MessageTypes.SECURE) {
            byte[] encrypted = encryptSecure(senderOrReceiverPrivateKey, receiverOrSenderPublicKey, data);
            return Base64.encodeBase64String(encrypted);
        } else {
            return Base64.encodeBase64String(data);
        }
    }

    public static byte[] encryptToByte(int messageType, byte[] data,
                           String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey) throws UnsupportedEncodingException {
        if (messageType == MessageTypes.SECURE) {
            byte[] encrypted = encryptSecure(senderOrReceiverPrivateKey, receiverOrSenderPublicKey, data);
            return Base64.encodeBase64(encrypted);
        } else {
            return Base64.encodeBase64(data);
        }
    }

    private static byte[] encryptSecure(String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey, byte[] data) {
        return engine.createBlockCipher(
                new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine),
                new KeyPair(PublicKey.fromHexString(receiverOrSenderPublicKey), engine))
                .encrypt(data);
    }
}
