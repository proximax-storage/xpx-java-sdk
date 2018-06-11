package io.nem.xpx.service;

import io.nem.xpx.exceptions.AnnounceTransactionFailureException;
import io.nem.xpx.testsupport.Constants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.nem.core.crypto.Hash;
import org.nem.core.model.Message;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.ValidationResult;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;

import static io.nem.xpx.testsupport.Constants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


/**
 * The Class TransactionAnnouncerTest.
 */
public class TransactionAnnouncerTest {


    /** The unit under test. */
    private TransactionAnnouncer unitUnderTest;

    /** The mock transaction fee calculators. */
    @Mock
    private TransactionFeeCalculators mockTransactionFeeCalculators;

    /** The mock transaction fee calculator. */
    @Mock
    private TransactionFeeCalculator mockTransactionFeeCalculator;

    /** The mock transaction sender. */
    @Mock
    private TransactionSender mockTransactionSender;

    /** The mock message. */
    @Mock
    private Message mockMessage;

    /** The transfer transaction argument captor. */
    @Captor
    private ArgumentCaptor<TransferTransaction> transferTransactionArgumentCaptor;

    /**
     * Sets the up.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        unitUnderTest = new TransactionAnnouncer(mockTransactionFeeCalculators, mockTransactionSender);
    }

    /**
     * Should announce transaction successfully.
     *
     * @throws Exception the exception
     */
    @Test
    public void shouldAnnounceTransactionSuccessfully() throws Exception {
        final String expectedNemHash = "94f43ffcaf403c0a3f9e72b72c6705bf5f6df09f1cfbed0d21be89b580ed5631";
        final Amount expectedFee = Amount.fromNem(1);
        given(mockTransactionFeeCalculators.getFeeCalculator(TEST_PRIVATE_KEY_ACCOUNT.getAddress().getEncoded()))
                .willReturn(mockTransactionFeeCalculator);
        given(mockTransactionFeeCalculator.calculateMinimumFee(any())).willReturn(expectedFee);
        given(mockTransactionSender.sendTransferTransaction(transferTransactionArgumentCaptor.capture()))
                .willReturn(new NemAnnounceResult(ValidationResult.fromValue(NemAnnounceResult.CODE_SUCCESS),
                        Hash.fromHexString(expectedNemHash), null));

        final String transactionHash = unitUnderTest.announceTransactionForUploadedContent(mockMessage, Constants.TEST_PRIVATE_KEY,
                Constants.TEST_PUBLIC_KEY, new Mosaic[]{Constants.MOSAIC_LAND_REGISTRY});

        assertThat(transactionHash, is(expectedNemHash));
        assertThat(transferTransactionArgumentCaptor.getValue().getFee(), is(expectedFee));
        assertThat(transferTransactionArgumentCaptor.getValue().getSigner(), is(TEST_PRIVATE_KEY_ACCOUNT));
        assertThat(transferTransactionArgumentCaptor.getValue().getRecipient(), is(TEST_PUBLIC_KEY_ACCOUNT));
        assertThat(transferTransactionArgumentCaptor.getValue().getMessage(), is(mockMessage));
        assertThat(transferTransactionArgumentCaptor.getValue().getMosaics().stream().anyMatch(mosaic -> mosaic.equals(MOSAIC_LAND_REGISTRY)), is(true));
    }

    /**
     * Should throw exception on failure.
     *
     * @throws Exception the exception
     */
    @Test(expected = AnnounceTransactionFailureException.class)
    public void shouldThrowExceptionOnFailure() throws Exception {
        given(mockTransactionFeeCalculators.getFeeCalculator(TEST_PRIVATE_KEY_ACCOUNT.getAddress().getEncoded()))
                .willReturn(mockTransactionFeeCalculator);
        given(mockTransactionFeeCalculator.calculateMinimumFee(any())).willReturn(Amount.fromNem(1));
        given(mockTransactionSender.sendTransferTransaction(any()))
                .willReturn(new NemAnnounceResult(ValidationResult.fromValue(2),
                        Hash.fromHexString("94f43ffcaf403c0a3f9e72b72c6705bf5f6df09f1cfbed0d21be89b580ed5631"), null));

        unitUnderTest.announceTransactionForUploadedContent(mockMessage, Constants.TEST_PRIVATE_KEY,
                Constants.TEST_PUBLIC_KEY, new Mosaic[]{Constants.MOSAIC_LAND_REGISTRY});
    }

}
