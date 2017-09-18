/*
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
 */
package com.hp.ov.sdk.rest.client.networking;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.networkset.NetworkSet;
import com.hp.ov.sdk.rest.client.common.CreatableResource;
import com.hp.ov.sdk.rest.client.common.DeletableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.client.common.UpdatableResource;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(NetworkSetClient.NETWORK_SET_URI)
public interface NetworkSetClient extends
        CreatableResource<NetworkSet>,
        SearchableResource<NetworkSet>,
        UpdatableResource<NetworkSet>,
        DeletableResource {

    String NETWORK_SET_URI = "/rest/network-sets";
    String WITHOUT_ETHERNET_URI = "/withoutEthernet";

    /**
     * Retrieves the {@link ResourceCollection}&lt;{@link NetworkSet}&gt;
     * containing details for all available network sets found under
     * the current HPE OneView, excluding any Ethernet networks.
     *
     * @return {@link ResourceCollection}&lt;{@link NetworkSet}&gt; containing
     * the details for all network sets found
     */
    @Endpoint(uri = WITHOUT_ETHERNET_URI)
    public ResourceCollection<NetworkSet> getAllWithoutEthernet();

    /**
     * Retrieves the {@link NetworkSet} details for the specified network set,
     * excluding any Ethernet networks.
     *
     * @param resourceId network set resource identifier as seen in HPE OneView
     *
     * @return {@link NetworkSet} object containing the details
     */
    @Endpoint(uri = "/{resourceId}" + WITHOUT_ETHERNET_URI)
    public NetworkSet getByIdWithoutEthernet(@PathParam("resourceId") String resourceId);

}
