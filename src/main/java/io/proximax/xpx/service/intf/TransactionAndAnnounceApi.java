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

package io.proximax.xpx.service.intf;

import java.util.concurrent.ExecutionException;

import io.proximax.xpx.exceptions.ApiException;
import io.proximax.xpx.model.RequestAnnounceDataSignature;




/**
 * The Interface TransactionAndAnnounceApi.
 */
public interface TransactionAndAnnounceApi {
	
	/**
	 * Announce request publish data signature using POST.
	 *
	 * @param requestAnnounceDataSignature the request announce data signature
	 * @return the string
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public String announceRequestPublishDataSignatureUsingPOST(RequestAnnounceDataSignature requestAnnounceDataSignature) throws  ApiException, InterruptedException, ExecutionException;
	
	/**
	 * Gets the XPX transaction using GET.
	 *
	 * @param nemHash the proximax hash
	 * @return the XPX transaction using GET
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public String getXPXTransactionUsingGET(String nemHash) throws ApiException, InterruptedException, ExecutionException;
}
