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

import io.proximax.xpx.exceptions.ApiException;




/**
 * The Interface DirectoryLoadApi.
 */
public interface DirectoryLoadApi {
	
	/**
	 * Load directory using GET.
	 *
	 * @param nemHash the proximax hash
	 * @return the object
	 * @throws ApiException the api exception
	 */
	public Object loadDirectoryUsingGET(String nemHash) throws ApiException;
}
