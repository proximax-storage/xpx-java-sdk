package io.nem.xpx.service;


import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.MosaicInformationNotFoundException;
import org.nem.core.model.FeeUnitAwareTransactionFeeCalculator;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicDefinition;
import org.nem.core.model.mosaic.MosaicFeeInformation;
import org.nem.core.model.mosaic.MosaicFeeInformationLookup;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Supply;

import java.util.concurrent.ExecutionException;

public final class TransactionFeeCalculators {

    private final NemAccountApi accountApi;
    private final NemNamespaceAndMosaicsApi namespaceMosaicApi;

	public TransactionFeeCalculators(NemAccountApi accountApi, NemNamespaceAndMosaicsApi namespaceMosaicApi) {
		this.accountApi = accountApi;
		this.namespaceMosaicApi = namespaceMosaicApi;
	}
	
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
    
    public TransactionFeeCalculator getFeeCalculator(String senderAddress) {
        return new FeeUnitAwareTransactionFeeCalculator(
                Amount.fromMicroNem(50_000L), mosaicInfoLookupForSender(senderAddress));
    }

    public TransactionFeeCalculator getFeeCalculatorMultiSig(String senderAddress) {
        return new FeeUnitAwareTransactionFeeCalculator(
                Amount.fromMicroNem(50_000L), mosaicInfoLookupForSender(senderAddress));
    }


    /**
     * Mosaic info lookup.
     *
     * @return the mosaic fee information lookup
     */
    private MosaicFeeInformationLookup mosaicInfoLookup() {
        return id -> {
        	
            if (id.getName().equals("xpx")) {
                return new MosaicFeeInformation(Supply.fromValue(8_999_999_999L), 4);
            }
            final int multiplier = Integer.parseInt(id.getName().substring(4));
            final int divisibilityChange = multiplier - 1;
            return new MosaicFeeInformation(Supply.fromValue(100_000_000 * multiplier), 3 + divisibilityChange);
        };
    }
    
    private MosaicFeeInformationLookup mosaicInfoLookupForSender(final String sender) {

        return id -> {
            final String namespaceToLookup = id.getNamespaceId().getRoot().toString();
            final String mosaicNameToLookup = id.getName();

            if (namespaceToLookup.equals("nem") && mosaicNameToLookup.equals("xem"))
                return null;

        	try {
				for (Mosaic mosaic : accountApi.getAccountOwnedMosaic(sender)) {
					final String namespace = mosaic.getMosaicId().getNamespaceId().getRoot().toString();
					final String mosaicName = mosaic.getMosaicId().getName();

					if (namespace.equals(namespaceToLookup) && mosaicName.equals(mosaicNameToLookup)) {
						MosaicDefinition mosaicDefinition = namespaceMosaicApi.getMosaicInformation(namespace,mosaicName);
						return new MosaicFeeInformation(
						        Supply.fromValue(mosaicDefinition.getProperties().getInitialSupply()),
                                mosaicDefinition.getProperties().getDivisibility());
					}
				}
                //	error - meaning the sender doesn't own the mosaics attached.
                throw new MosaicInformationNotFoundException("Mosaic not found on Sender");

			} catch (InterruptedException | ExecutionException | ApiException e) {
				throw new MosaicInformationNotFoundException("Exception occured while getting Mosaic Information."  + e.getMessage());
			}
        	

        };
    }
    
    
}
