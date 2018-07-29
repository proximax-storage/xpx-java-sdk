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

import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.mosaic.MosaicDefinition;
import org.nem.core.model.namespace.Namespace;
import org.nem.core.model.ncc.MosaicDefinitionMetaDataPair;
import org.nem.core.model.ncc.NamespaceMetaDataPair;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.Deserializer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * The Class NemNamespaceAndMosaicsApi.
 */
public class NemNamespaceAndMosaicsApi {

	/** The node endpoint. */
	private final NodeEndpoint nodeEndpoint;

	/** The async proximax connector. */
	private DefaultAsyncNemConnector<ApiId> asyncNemConnector;

	/**
	 * Instantiates a new proximax namespace and mosaics api.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param asyncNemConnector the async proximax connector
	 */
	public NemNamespaceAndMosaicsApi(NodeEndpoint nodeEndpoint, DefaultAsyncNemConnector<ApiId> asyncNemConnector) {
		this.nodeEndpoint = nodeEndpoint;
		this.asyncNemConnector = asyncNemConnector;
	}


	/**
	 * Gets the namespace root page.
	 *
	 * @return the namespace root page
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public List<NamespaceMetaDataPair> getNamespaceRootPage() throws InterruptedException, ExecutionException {

		Deserializer des;
		List<NamespaceMetaDataPair> list;
		des = asyncNemConnector
				.getAsync(nodeEndpoint, NisApiId.NIS_REST_NAMESPACE_ROOT_PAGE, "").get();
		list = (ArrayList<NamespaceMetaDataPair>) des.readObjectArray("data", NamespaceMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the namespace root page.
	 *
	 * @param id the id
	 * @return the namespace root page
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public List<NamespaceMetaDataPair> getNamespaceRootPage(String id)
			throws InterruptedException, ExecutionException {

		Deserializer des;
		List<NamespaceMetaDataPair> list;
		des = asyncNemConnector
				.getAsync(nodeEndpoint, NisApiId.NIS_REST_NAMESPACE_ROOT_PAGE, "id=" + id).get();
		list = (ArrayList<NamespaceMetaDataPair>) des.readObjectArray("data", NamespaceMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the namespace root page.
	 *
	 * @param id the id
	 * @param pageSize the page size
	 * @return the namespace root page
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public List<NamespaceMetaDataPair> getNamespaceRootPage(String id, String pageSize)
			throws InterruptedException, ExecutionException {

		Deserializer des;
		List<NamespaceMetaDataPair> list;
		des = asyncNemConnector
				.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_NAMESPACE_ROOT_PAGE, "id=" + id + "&pagesize=" + pageSize).get();
		list = (ArrayList<NamespaceMetaDataPair>) des.readObjectArray("data", NamespaceMetaDataPair::new);
		return list;

	}

	/**
	 * Gets the namespace.
	 *
	 * @param namespace the namespace
	 * @return the namespace
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public Namespace getNamespace(String namespace) throws InterruptedException, ExecutionException {

		Deserializer des;
		des = asyncNemConnector
				.getAsync(nodeEndpoint, NisApiId.NIS_REST_NAMESPACE, "namespace=" + namespace)
				.exceptionally(fn -> {
					fn.printStackTrace();
					return null;
				}).get();
		return new Namespace(des);

	}

	/**
	 * Gets the namespace mosaic definition page.
	 *
	 * @param namespace the namespace
	 * @return the namespace mosaic definition page
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public List<MosaicDefinitionMetaDataPair> getNamespaceMosaicDefinitionPage(String namespace)
			throws InterruptedException, ExecutionException {

		Deserializer des;
		List<MosaicDefinitionMetaDataPair> list;
		des = asyncNemConnector
				.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_NAMESPACE_MOSAIC_DEFINITION_PAGE, "namespace=" + namespace).get();
		list = (ArrayList<MosaicDefinitionMetaDataPair>) des.readObjectArray("data", MosaicDefinitionMetaDataPair::new);
		return list;

	}

	/**
	 * Gets the namespace mosaic definition page.
	 *
	 * @param namespace the namespace
	 * @param id the id
	 * @return the namespace mosaic definition page
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public  List<MosaicDefinitionMetaDataPair> getNamespaceMosaicDefinitionPage(String namespace, String id)
			throws InterruptedException, ExecutionException {

		Deserializer des;
		List<MosaicDefinitionMetaDataPair> list;
		des = asyncNemConnector
				.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_NAMESPACE_MOSAIC_DEFINITION_PAGE, "namespace=" + namespace + "&id=" + id).get();
		list = (ArrayList<MosaicDefinitionMetaDataPair>) des.readObjectArray("data", MosaicDefinitionMetaDataPair::new);
		return list;

	}

	/**
	 * Gets the namespace mosaic definition page.
	 *
	 * @param namespace the namespace
	 * @param id the id
	 * @param pageSize the page size
	 * @return the namespace mosaic definition page
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public List<MosaicDefinitionMetaDataPair> getNamespaceMosaicDefinitionPage(String namespace, String id,
			String pageSize) throws InterruptedException, ExecutionException {

		Deserializer des;
		List<MosaicDefinitionMetaDataPair> list;
		des = asyncNemConnector
				.getAsync(nodeEndpoint, NisApiId.NIS_REST_NAMESPACE_MOSAIC_DEFINITION_PAGE,
						"namespace=" + namespace + "&id=" + id + "&pagesize=" + pageSize)
				.get();
		list = (ArrayList<MosaicDefinitionMetaDataPair>) des.readObjectArray("data", MosaicDefinitionMetaDataPair::new);
		return list;

	}
	
	/**
	 * Gets the mosaic information .
	 *
	 * @param namespace the namespace
	 * @param mosaic the mosaic
	 * @return the mosaic information
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public MosaicDefinition getMosaicInformation(String namespace, String mosaic) throws InterruptedException, ExecutionException{
		Deserializer des;
		List<MosaicDefinitionMetaDataPair> list;
		des = asyncNemConnector
				.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_NAMESPACE_MOSAIC_DEFINITION_PAGE, "namespace=" + namespace).get();
		list = (ArrayList<MosaicDefinitionMetaDataPair>) des.readObjectArray("data", MosaicDefinitionMetaDataPair::new);
		
		for(MosaicDefinitionMetaDataPair mosaicDefinitionMetadataPair:list) {
			if(mosaicDefinitionMetadataPair.getEntity().getId().getName().equals(mosaic)) {
				return mosaicDefinitionMetadataPair.getEntity();
			}
		}
		return null;
	}



}
