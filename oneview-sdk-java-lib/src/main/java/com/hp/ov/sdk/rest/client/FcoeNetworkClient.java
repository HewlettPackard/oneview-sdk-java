/*******************************************************************************
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.FcoeNetwork;
import com.hp.ov.sdk.dto.FcoeNetworkCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface FcoeNetworkClient {

    /**
     * Retrieves the {@link FcoeNetwork} details for the specified
     * FCoE network.
     *
     * @param params structure containing the connection details.
     * @param resourceId FCoE network id as seen in HP OneView.
     *
     * @return {@link FcoeNetwork} object containing the details.
     */
    FcoeNetwork getFcoeNetwork(RestParams params, String resourceId);

    /**
     * Retrieves a {@link FcoeNetworkCollection} containing details
     * for all the available FCoE networks found under current HP OneView.
     *
     * @param params structure containing the connection details.
     *
     * @return {@link FcoeNetworkCollection} object containing the details
     * for all found FCoE networks.
     */
    FcoeNetworkCollection getAllFcoeNetworks(RestParams params);

    /**
     * Retrieves the {@link FcoeNetwork} details for the specified
     * FCoE network identified by name.
     *
     * @param params structure containing the connection details.
     * @param name FCoE network name as seen in HP OneView.
     *
     * @return {@link FcoeNetwork} object containing the details.
     */
    FcoeNetwork getFcoeNetworkByName(RestParams params, String name);

    /**
     * Creates a FCoE network according to the provided {@link FcoeNetwork}
     * object. The request can be processed asynchronously or synchronously.
     *
     * @param params structure containing the connection details.
     * @param fcoeNetwork object containing the FCoE network details.
     * @param aSync flag to indicate whether the request should be
     *          processed asynchronously or synchronously.
     *
     * @return taskResource which returns the task status for the process
     */
    TaskResourceV2 createFcoeNetwork(RestParams params, FcoeNetwork fcoeNetwork, boolean aSync);

    /**
     * Updates a {@link FcoeNetwork} identified by the given resourceId.
     *
     * @param params structure containing the connection details.
     * @param resourceId FCoE network id as seen in HP OneView.
     * @param fcoeNetwork object containing the FCoE network details.
     * @param aSync flag to indicate whether the request should be
     *          processed asynchronously or synchronously.
     *
     * @return taskResource which returns the task status for the process
     */
    TaskResourceV2 updateFcoeNetwork(RestParams params, String resourceId, FcoeNetwork fcoeNetwork, boolean aSync);

    /**
     * Deletes the {@link FcoeNetwork} identified by the given resourceId.
     *
     * @param params structure containing the connection details.
     * @param resourceId FCoE network id as seen in HP OneView.
     * @param aSync flag to indicate whether the request should be
     *          processed asynchronously or synchronously.
     *
     * @return taskResource which returns the task status for the process
     */
    TaskResourceV2 deleteFcoeNetwork(RestParams params, String resourceId, boolean aSync);

    /**
     * Retrieves the {@link FcoeNetwork} resource id for the specified
     * FCoE network identified by name.
     *
     * @param params structure containing the connection details.
     * @param name FCoE network name as seen in HP OneView.
     *
     * @return resourceId for the FCoE network name as seen in HP OneView.
     */
    String getId(RestParams params, final String name);
}
