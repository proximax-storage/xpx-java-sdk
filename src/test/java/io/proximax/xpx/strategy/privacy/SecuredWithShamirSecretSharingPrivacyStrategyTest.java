/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.strategy.privacy;

import com.codahale.shamir.Scheme;
import io.proximax.xpx.adapters.cipher.BinaryPBKDF2CipherEncryption;
import io.proximax.xpx.exceptions.EncryptionFailureException;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

public class SecuredWithShamirSecretSharingPrivacyStrategyTest {

    public static final byte[] SAMPLE_DATA = "the quick brown fox jumps over the lazy dog".getBytes();
    
    public static final byte[] SECRET = ("dhsakdhsauihcxznmneywquidkjsanjcbnxztyduisyaheqkwjncbxzcgyuiagsdiujasodjk" +
            "wqehqtsyiudhsandmbsamnbcxzygcahgisudhasnmbdmnasbcysagciugxziuhkjsbdamndnmsabfgaduishadshakdnsakdbsajbeh" +
            "wqyuieyqwiueyqwohdsanlnalsfjkhgkzgmnbcmnxzbhjgdsajgduisayiuyewquehwqkjbeqwnbdmnabsdabjshgdasudhgsuakghk" +
            "cbxzcbajsbdkasjgkjhwgquegqwbzmcbmzxn").getBytes();

    public static final int SECRET_TOTAL_PART_COUNT = 5;

    public static final int SECRET_MINIMUM_PART_COUNT_TO_BUILD = 3;

    public static final Scheme SCHEME = Scheme.of(SECRET_TOTAL_PART_COUNT, SECRET_MINIMUM_PART_COUNT_TO_BUILD);

    public static final Map<Integer, byte[]> SECRET_PARTS = SCHEME.split(SECRET);


    @Test(expected = IllegalArgumentException.class)
    public void failWithNegativeSecretTotalPartCount() {
        new SecuredWithShamirSecretSharingPrivacyStrategy(
                new BinaryPBKDF2CipherEncryption(), -1, SECRET_MINIMUM_PART_COUNT_TO_BUILD, SECRET_PARTS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failWithNegativeSecretMinimumPartCountToBuild() {
        new SecuredWithShamirSecretSharingPrivacyStrategy(
                new BinaryPBKDF2CipherEncryption(), SECRET_TOTAL_PART_COUNT, -1, SECRET_PARTS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failWithSecretMinimumPartCountToBuildBeingGreaterThanTotalPartCount() {
        new SecuredWithShamirSecretSharingPrivacyStrategy(
                new BinaryPBKDF2CipherEncryption(), SECRET_TOTAL_PART_COUNT, SECRET_TOTAL_PART_COUNT + 1, SECRET_PARTS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failWithSecretPartsEmpty() {
        new SecuredWithShamirSecretSharingPrivacyStrategy(
                new BinaryPBKDF2CipherEncryption(), SECRET_TOTAL_PART_COUNT, SECRET_MINIMUM_PART_COUNT_TO_BUILD, Collections.emptyMap());
    }

    @Test(expected = IllegalArgumentException.class)
    public void failWithSecretPartsNotMeetingMinimumPartCountToBuild() {
        new SecuredWithShamirSecretSharingPrivacyStrategy(
                new BinaryPBKDF2CipherEncryption(), SECRET_TOTAL_PART_COUNT, SECRET_MINIMUM_PART_COUNT_TO_BUILD,
                Collections.singletonMap(1, SECRET_PARTS.get(1)));
    }

    @Test
    public void returnEncryptedWithAllSecretParts() {
        final SecuredWithShamirSecretSharingPrivacyStrategy unitUnderTest = new SecuredWithShamirSecretSharingPrivacyStrategy(
                new BinaryPBKDF2CipherEncryption(), SECRET_TOTAL_PART_COUNT, SECRET_MINIMUM_PART_COUNT_TO_BUILD,
                SECRET_PARTS);

        final byte[] encrypted = unitUnderTest.encrypt(SAMPLE_DATA);

        assertFalse(Arrays.equals(SAMPLE_DATA, encrypted));
    }

    @Test
    public void returnEncryptedWithMinimumSecretParts() {
        final Map<Integer, byte[]> minimumSecretParts = new HashMap<>();
        minimumSecretParts.put(1, SECRET_PARTS.get(1));
        minimumSecretParts.put(3, SECRET_PARTS.get(3));
        minimumSecretParts.put(5, SECRET_PARTS.get(5));
        final SecuredWithShamirSecretSharingPrivacyStrategy unitUnderTest = new SecuredWithShamirSecretSharingPrivacyStrategy(
                new BinaryPBKDF2CipherEncryption(), SECRET_TOTAL_PART_COUNT, SECRET_MINIMUM_PART_COUNT_TO_BUILD,
                minimumSecretParts);

        final byte[] encrypted = unitUnderTest.encrypt(SAMPLE_DATA);

        assertFalse(Arrays.equals(SAMPLE_DATA, encrypted));
    }

    @Test
    public void returnDecryptedWithMinimumSecretParts() {
        final SecuredWithShamirSecretSharingPrivacyStrategy allPartsStrategy = new SecuredWithShamirSecretSharingPrivacyStrategy(
                new BinaryPBKDF2CipherEncryption(), SECRET_TOTAL_PART_COUNT, SECRET_MINIMUM_PART_COUNT_TO_BUILD,
                SECRET_PARTS);
        final byte[] encrypted = allPartsStrategy.encrypt(SAMPLE_DATA);
        final Map<Integer, byte[]> minimumSecretParts = new HashMap<>();
        minimumSecretParts.put(1, SECRET_PARTS.get(1));
        minimumSecretParts.put(2, SECRET_PARTS.get(2));
        minimumSecretParts.put(4, SECRET_PARTS.get(4));
        final SecuredWithShamirSecretSharingPrivacyStrategy unitUnderTest = new SecuredWithShamirSecretSharingPrivacyStrategy(
                new BinaryPBKDF2CipherEncryption(), SECRET_TOTAL_PART_COUNT, SECRET_MINIMUM_PART_COUNT_TO_BUILD,
                minimumSecretParts);

        final byte[] decrypted = unitUnderTest.decrypt(encrypted, null, null);

        assertArrayEquals(SAMPLE_DATA, decrypted);
    }

    @Test
    public void returnDecryptedWithDifferentSecretParts() {
        final Map<Integer, byte[]> firstSecretParts = new HashMap<>();
        firstSecretParts.put(2, SECRET_PARTS.get(2));
        firstSecretParts.put(3, SECRET_PARTS.get(3));
        firstSecretParts.put(5, SECRET_PARTS.get(5));
        final SecuredWithShamirSecretSharingPrivacyStrategy firstPartsStrategy = new SecuredWithShamirSecretSharingPrivacyStrategy(
                new BinaryPBKDF2CipherEncryption(), SECRET_TOTAL_PART_COUNT, SECRET_MINIMUM_PART_COUNT_TO_BUILD,
                firstSecretParts);
        final byte[] encrypted = firstPartsStrategy.encrypt(SAMPLE_DATA);
        final Map<Integer, byte[]> secondSecretParts = new HashMap<>();
        secondSecretParts.put(1, SECRET_PARTS.get(1));
        secondSecretParts.put(2, SECRET_PARTS.get(2));
        secondSecretParts.put(4, SECRET_PARTS.get(4));
        final SecuredWithShamirSecretSharingPrivacyStrategy secondPartsStrategy = new SecuredWithShamirSecretSharingPrivacyStrategy(
                new BinaryPBKDF2CipherEncryption(), SECRET_TOTAL_PART_COUNT, SECRET_MINIMUM_PART_COUNT_TO_BUILD,
                secondSecretParts);

        final byte[] decrypted = secondPartsStrategy.decrypt(encrypted, null, null);

        assertArrayEquals(SAMPLE_DATA, decrypted);
    }

    @Test(expected = EncryptionFailureException.class)
    public void returnDecryptedWithWrongSecretParts() {
        final Map<Integer, byte[]> firstSecretParts = new HashMap<>();
        firstSecretParts.put(2, SECRET_PARTS.get(2));
        firstSecretParts.put(3, SECRET_PARTS.get(3));
        firstSecretParts.put(5, SECRET_PARTS.get(5));
        final SecuredWithShamirSecretSharingPrivacyStrategy firstPartsStrategy = new SecuredWithShamirSecretSharingPrivacyStrategy(
                new BinaryPBKDF2CipherEncryption(), SECRET_TOTAL_PART_COUNT, SECRET_MINIMUM_PART_COUNT_TO_BUILD,
                firstSecretParts);
        final byte[] encrypted = firstPartsStrategy.encrypt(SAMPLE_DATA);
        final Map<Integer, byte[]> wrongSecretParts = new HashMap<>();
        wrongSecretParts.put(1, SECRET_PARTS.get(1));
        wrongSecretParts.put(3, SECRET_PARTS.get(3));
        wrongSecretParts.put(4, SECRET_PARTS.get(5));

        final SecuredWithShamirSecretSharingPrivacyStrategy unitUnderTest = new SecuredWithShamirSecretSharingPrivacyStrategy(
                new BinaryPBKDF2CipherEncryption(), SECRET_TOTAL_PART_COUNT, SECRET_MINIMUM_PART_COUNT_TO_BUILD,
                wrongSecretParts);

        final byte[] decrypted = unitUnderTest.decrypt(encrypted, null, null);

        assertArrayEquals(SAMPLE_DATA, decrypted);
    }

}
