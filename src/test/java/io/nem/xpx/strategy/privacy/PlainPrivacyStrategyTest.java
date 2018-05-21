package io.nem.xpx.strategy.privacy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlainPrivacyStrategyTest {

    public static final byte[] INPUT_DATA = "the quick brown fox jumps over the lazy dog".getBytes();

    private PlainPrivacyStrategy unitUnderTest;

    @Before
    public void setUp() {
        unitUnderTest = new PlainPrivacyStrategy();
    }

    @Test
    public void returnSameDataOnEncrypt() {
        final byte[] encrypted = unitUnderTest.encrypt(INPUT_DATA);

        assertEquals(INPUT_DATA, encrypted);
    }

    @Test
    public void returnSameDataOnDecrypt() {
        final byte[] decrypted = unitUnderTest.decrypt(INPUT_DATA, null, null);

        assertEquals(INPUT_DATA, decrypted);
    }
}
