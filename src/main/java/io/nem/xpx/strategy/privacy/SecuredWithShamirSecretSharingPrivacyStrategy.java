package io.nem.xpx.strategy.privacy;

import com.codahale.shamir.Scheme;
import io.nem.xpx.adapters.cipher.BinaryPBKDF2CipherEncryption;
import io.nem.xpx.exceptions.EncryptionFailureException;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.nem.core.model.TransferTransaction;

import java.util.Map;

import static io.nem.xpx.utils.ParameterValidationUtils.checkParameter;


public final class SecuredWithShamirSecretSharingPrivacyStrategy extends AbstractPlainMessagePrivacyStrategy {

    private final char[] secret;
    private final BinaryPBKDF2CipherEncryption encryptor;

    public SecuredWithShamirSecretSharingPrivacyStrategy(BinaryPBKDF2CipherEncryption encryptor,
                                                         int secretTotalPartCount, int secretMinimumPartCountToBuild,
                                                         Map<Integer, byte[]> secretParts) {
        checkParameter(secretTotalPartCount > 0, "secretTotalPartCount should be a positive number");
        checkParameter(secretMinimumPartCountToBuild > 0 && secretMinimumPartCountToBuild <= secretTotalPartCount,
                "secretMinimumPartCountToBuild should be a positive number less than or equal to secretTotalPartCount");
        checkParameter(secretParts != null, "secretParts is required");
        checkParameter(secretParts.size() >= secretMinimumPartCountToBuild,
                "secretParts should meet minimum part count as defined by secretMinimumPartCountToBuild");

        this.secret = new String(Scheme.of(secretTotalPartCount, secretMinimumPartCountToBuild).join(secretParts)).toCharArray();
        this.encryptor = encryptor;
    }

    @Override
    public byte[] encrypt(final byte[] data) {
        try {
            return encryptor.encrypt(data, secret);
        } catch (Exception e) {
            throw new EncryptionFailureException("Exception encountered encrypting data", e);
        }
    }

    @Override
    public byte[] decrypt(final byte[] data, final TransferTransaction transaction, final ResourceHashMessage hashMessage) {
        try {
            return encryptor.decrypt(data, secret);
        } catch (Exception e) {
            throw new EncryptionFailureException("Exception encountered decrypting data", e);
        }
    }

    public static class SecretPart {

        public final int index;
        public final byte[] secretPart;

        public SecretPart(int index, byte[] secretPart) {
            this.index = index;
            this.secretPart = secretPart;
        }

        public static SecretPart secretPart(int index, byte[] secretPart) {
            return new SecretPart(index, secretPart);
        }
    }
}
