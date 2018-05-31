package io.nem.xpx.service;


import java.util.concurrent.ExecutionException;

import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.model.FeeUnitAwareTransactionFeeCalculator;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicDefinition;
import org.nem.core.model.mosaic.MosaicFeeInformation;
import org.nem.core.model.mosaic.MosaicFeeInformationLookup;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Supply;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.MosaicInformationNotFoundException;

public final class TransactionFeeCalculators {

	private final NodeEndpoint nodeEndpoint;

	private DefaultAsyncNemConnector<ApiId> asyncNemConnector;
	
	public TransactionFeeCalculators(NodeEndpoint nodeEndpoint, DefaultAsyncNemConnector<ApiId> asyncNemConnector) {
		this.nodeEndpoint = nodeEndpoint;
		this.asyncNemConnector = asyncNemConnector;
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
        if (feeCalculator == null)
            feeCalculator = new FeeUnitAwareTransactionFeeCalculator(
                    Amount.fromMicroNem(50_000L), mosaicInfoLookup(senderAddress));
        return feeCalculator;
    }

    public TransactionFeeCalculator getFeeCalculatorMultiSig(String senderAddress) {
        if (feeCalculatorMultiSig == null)
        	feeCalculatorMultiSig =  new FeeUnitAwareTransactionFeeCalculator(
                    Amount.fromMicroNem(50_000L), mosaicInfoLookup(senderAddress));
        return feeCalculatorMultiSig;
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
    
    private MosaicFeeInformationLookup mosaicInfoLookup(final String sender) {

        return id -> {
        	final NemAccountApi accountApi = new NemAccountApi(nodeEndpoint, asyncNemConnector);
        	final NemNamespaceAndMosaicsApi namespaceMosaicApi = new NemNamespaceAndMosaicsApi(nodeEndpoint, asyncNemConnector);
        	
        	try {
				for(Mosaic mosaic: accountApi.getAccountOwnedMosaic(sender)) {
					String namespace = mosaic.getMosaicId().getNamespaceId().getRoot().toString();
					String mosaicString = mosaic.getMosaicId().getName();
					if(!namespace.equals("nem") && !mosaicString.equals("xem")) { // nem:xem will is always needed for NIS1
						MosaicDefinition mosaicDefinition = namespaceMosaicApi.getMosaicInformation(namespace,mosaicString);
						return new MosaicFeeInformation(Supply.fromValue(mosaicDefinition.getProperties().getInitialSupply()), mosaicDefinition.getProperties().getDivisibility());
					}
					// nem:xem will is always needed for NIS1
					return new MosaicFeeInformation(Supply.fromValue(8_999_999_999L), 6);
				}
			} catch (InterruptedException | ExecutionException | ApiException e) {
				throw new MosaicInformationNotFoundException("Exception occured while getting Mosaic Information."  + e.getMessage());
			}
        	
        	//	error - meaning the sender doesn't own the mosaics attached.
        	throw new MosaicInformationNotFoundException("Mosaic not found on Sender");
        			
        };
    }
    
    
}
