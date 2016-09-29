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

    @Endpoint(uri = BULK_ETHERNET_URI, method = HttpMethod.POST)
    TaskResource createInBulk(@BodyParam BulkEthernetNetwork bulkEthernet, RequestOption ... options);

    @Endpoint(uri = "/{resourceId}" + ASSOCIATED_PROFILES)
    List<String> getAssociatedProfiles(@PathParam("resourceId") String resourceId);

    @Endpoint(uri = "/{resourceId}" + ASSOCIATED_UPLINK_GROUPS)
    List<String> getAssociatedUplinkGroups(@PathParam("resourceId") String resourceId);

}
