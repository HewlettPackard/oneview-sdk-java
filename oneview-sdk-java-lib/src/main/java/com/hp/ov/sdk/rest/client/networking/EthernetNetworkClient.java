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

import java.util.List;

import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.ethernet.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.networking.ethernet.Network;
import com.hp.ov.sdk.rest.client.common.CreatableResource;
import com.hp.ov.sdk.rest.client.common.DeletableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.client.common.UpdatableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(EthernetNetworkClient.ETHERNET_URI)
public interface EthernetNetworkClient extends
        CreatableResource<Network>,
        SearchableResource<Network>,
        UpdatableResource<Network>,
        DeletableResource {

    String ETHERNET_URI = "/rest/ethernet-networks";
    String BULK_ETHERNET_URI = "/bulk";
    String ASSOCIATED_PROFILES = "/associatedProfiles";
    String ASSOCIATED_UPLINK_GROUPS = "/associatedUplinkGroups";

    /**
     * Creates a set of ethernet networks according to the provided {@link BulkEthernetNetwork}.
     *
     * Since this operation can take some time to complete, it is possible to specify a timeout
     * using the option {@link com.hp.ov.sdk.rest.http.core.client.TaskTimeout}. If no timeout
     * is specified, the default behavior is to wait until the create action completes.
     *
     * @param bulkEthernet object containing the bulk ethernet network details the should used
     *                     to create a set of ethernet networks. The number of networks created
     *                     is specified through the VLAN range. For each network, the VLAN number
     *                     will be used as suffix to uniquely identify the network.
     *
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = BULK_ETHERNET_URI, method = HttpMethod.POST)
    TaskResource createInBulk(@BodyParam BulkEthernetNetwork bulkEthernet, RequestOption ... options);

    /**
     * Retrieves a {@link List}&lt;String&gt; containing the URIs of the server profiles associated
     * with the ethernet network identified by the provided <code>resourceId</code>.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     *
     * @return {@link List} containing the URIs for the associated server profiles.
     */
    @Endpoint(uri = "/{resourceId}" + ASSOCIATED_PROFILES)
    List<String> getAssociatedProfiles(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves a {@link List}&lt;String&gt; containing the URIs of the uplink groups associated
     * with the ethernet network identified by the provided <code>resourceId</code>.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     *
     * @return {@link List} containing the URIs for the associated uplink groups.
     */
    @Endpoint(uri = "/{resourceId}" + ASSOCIATED_UPLINK_GROUPS)
    List<String> getAssociatedUplinkGroups(@PathParam("resourceId") String resourceId);

}
