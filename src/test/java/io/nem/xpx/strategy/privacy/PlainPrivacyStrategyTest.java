package io.nem.xpx.strategy.privacy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * The Class PlainPrivacyStrategyTest.
 */
public class PlainPrivacyStrategyTest {

    /** The Constant INPUT_DATA. */
    public static final byte[] INPUT_DATA = "the quick brown fox jumps over the lazy dog".getBytes();

    /** The unit under test. */
    private PlainPrivacyStrategy unitUnderTest;

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        unitUnderTest = new PlainPrivacyStrategy();
    }

    /**
     * Return same data on encrypt.
     */
    @Test
    public void returnSameDataOnEncrypt() {
        final byte[] encrypted = unitUnderTest.encrypt(INPUT_DATA);

        assertEquals(INPUT_DATA, encrypted);
    }

    /**
     * Return same data on decrypt.
     */
    @Test
    public void returnSameDataOnDecrypt() {
        final byte[] decrypted = unitUnderTest.decrypt(INPUT_DATA, null, null);

        assertEquals(INPUT_DATA, decrypted);
    }
}
