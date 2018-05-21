package io.nem.xpx.strategy.privacy;

import io.nem.xpx.adapters.cipher.BinaryPBKDF2CipherEncryption;
import io.nem.xpx.exceptions.EncryptionFailureException;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.nem.core.model.TransferTransaction;

import static io.nem.xpx.utils.ParameterValidationUtils.checkParameter;

public final class SecuredWithPasswordPrivacyStrategy extends AbstractPlainMessagePrivacyStrategy {

    private static final int MINIMUM_PASSWORD_LENGTH = 50;
    private final BinaryPBKDF2CipherEncryption encryptor;
    private final char[] password;


    public SecuredWithPasswordPrivacyStrategy(BinaryPBKDF2CipherEncryption encryptor, String password) {
        checkParameter(password != null, "password is required");
        checkParameter(password.length() >= MINIMUM_PASSWORD_LENGTH, "minimum length for password is 50");

        this.encryptor = encryptor;
        this.password = password.toCharArray();
    }

    @Override
    public byte[] encrypt(final byte[] data) {
        try {
            return encryptor.encrypt(data, password);
        } catch (Exception e) {
            throw new EncryptionFailureException("Exception encountered encrypting data", e);
        }
    }

    @Override
    public byte[] decrypt(final byte[] data, final TransferTransaction transaction, final ResourceHashMessage hashMessage) {
        try {
            return encryptor.decrypt(data, password);
        } catch (Exception e) {
            throw new EncryptionFailureException("Exception encountered decrypting data", e);
        }
    }
}
