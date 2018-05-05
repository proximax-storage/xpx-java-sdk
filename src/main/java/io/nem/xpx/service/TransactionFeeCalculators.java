package io.nem.xpx.service;

import org.nem.core.model.FeeUnitAwareTransactionFeeCalculator;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.mosaic.MosaicFeeInformation;
import org.nem.core.model.mosaic.MosaicFeeInformationLookup;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Supply;

public final class TransactionFeeCalculators {

    /** The fee calculator. */
    private TransactionFeeCalculator feeCalculator;

    /** The fee calculator multi sig. */
    private TransactionFeeCalculator feeCalculatorMultiSig;

    public TransactionFeeCalculator getFeeCalculator() {
        if (feeCalculator == null)
            feeCalculator = new FeeUnitAwareTransactionFeeCalculator(
                    Amount.fromMicroNem(50_000L), mosaicInfoLookup());
        return feeCalculator;
    }

    public TransactionFeeCalculator getFeeCalculatorMultiSig() {
        if (feeCalculatorMultiSig == null)
        	feeCalculatorMultiSig =  new FeeUnitAwareTransactionFeeCalculator(
                    Amount.fromMicroNem(50_000L), mosaicInfoLookup());
        return feeCalculatorMultiSig;
    }

    /**
     * Mosaic info lookup.
     *
     * @return the mosaic fee information lookup
     */
    private static MosaicFeeInformationLookup mosaicInfoLookup() {
        return id -> {
            if (id.getName().equals("xpx")) {
                return new MosaicFeeInformation(Supply.fromValue(8_999_999_999L), 4);
            }
            final int multiplier = Integer.parseInt(id.getName().substring(4));
            final int divisibilityChange = multiplier - 1;
            return new MosaicFeeInformation(Supply.fromValue(100_000_000 * multiplier), 3 + divisibilityChange);
        };
    }
}
