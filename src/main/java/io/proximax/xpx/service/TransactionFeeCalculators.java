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

package io.proximax.xpx.service;


import io.proximax.xpx.exceptions.ApiException;
import io.proximax.xpx.exceptions.MosaicInformationNotFoundException;
import org.nem.core.model.FeeUnitAwareTransactionFeeCalculator;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicDefinition;
import org.nem.core.model.mosaic.MosaicFeeInformation;
import org.nem.core.model.mosaic.MosaicFeeInformationLookup;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Supply;

import java.util.concurrent.ExecutionException;


/**
 * The Class TransactionFeeCalculators.
 */
public final class TransactionFeeCalculators {

    /** The account api. */
    private final NemAccountApi accountApi;
    
    /** The namespace mosaic api. */
    private final NemNamespaceAndMosaicsApi namespaceMosaicApi;

	/**
	 * Instantiates a new transaction fee calculators.
	 *
	 * @param accountApi the account api
	 * @param namespaceMosaicApi the namespace mosaic api
	 */
	public TransactionFeeCalculators(NemAccountApi accountApi, NemNamespaceAndMosaicsApi namespaceMosaicApi) {
		this.accountApi = accountApi;
		this.namespaceMosaicApi = namespaceMosaicApi;
	}
	
    /** The fee calculator. */
    private TransactionFeeCalculator feeCalculator;

    /** The fee calculator multi sig. */
    private TransactionFeeCalculator feeCalculatorMultiSig;

    /**
     * Gets the fee calculator.
     *
     * @return the fee calculator
     */
    public TransactionFeeCalculator getFeeCalculator() {
        if (feeCalculator == null)
            feeCalculator = new FeeUnitAwareTransactionFeeCalculator(
                    Amount.fromMicroNem(50_000L), mosaicInfoLookup());
        return feeCalculator;
    }

    /**
     * Gets the fee calculator multi sig.
     *
     * @return the fee calculator multi sig
     */
    public TransactionFeeCalculator getFeeCalculatorMultiSig() {
        if (feeCalculatorMultiSig == null)
        	feeCalculatorMultiSig =  new FeeUnitAwareTransactionFeeCalculator(
                    Amount.fromMicroNem(50_000L), mosaicInfoLookup());
        return feeCalculatorMultiSig;
    }
    
    /**
     * Gets the fee calculator.
     *
     * @param senderAddress the sender address
     * @return the fee calculator
     */
    public TransactionFeeCalculator getFeeCalculator(String senderAddress) {
        return new FeeUnitAwareTransactionFeeCalculator(
                Amount.fromMicroNem(50_000L), mosaicInfoLookupForSender(senderAddress));
    }

    /**
     * Gets the fee calculator multi sig.
     *
     * @param senderAddress the sender address
     * @return the fee calculator multi sig
     */
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
    
    /**
     * Mosaic info lookup for sender.
     *
     * @param sender the sender
     * @return the mosaic fee information lookup
     */
    private MosaicFeeInformationLookup mosaicInfoLookupForSender(final String sender) {

        return id -> {
            final String namespaceToLookup = id.getNamespaceId().getRoot().toString();
            final String mosaicNameToLookup = id.getName();

            if (namespaceToLookup.equals("proximax") && mosaicNameToLookup.equals("xem"))
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
