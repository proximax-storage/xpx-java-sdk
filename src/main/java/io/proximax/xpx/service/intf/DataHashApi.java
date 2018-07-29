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

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import io.proximax.xpx.exceptions.ApiException;


/**
 * The Interface DataHashApiInterface.
 */
public interface DataHashApi {
	
	/**
	 * Generate hash and expose data to network using POST.
	 *
	 * @param data the data
	 * @return the binary transaction encrypted message
	 * @throws ApiException the api exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public String generateHashForDataOnlyUsingPOST(byte[] data) throws ApiException, IOException, NoSuchAlgorithmException;


}
