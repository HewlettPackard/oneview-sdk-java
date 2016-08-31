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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.networking.fabric.Fabric;
import com.hp.ov.sdk.dto.networking.fabric.VlanPool;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class FabricClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FabricClient.class);

    public static final String FABRIC_URI = "/rest/fabrics";
    public static final String RESERVED_VLAN_RANGE_URI = "reserved-vlan-range";

    private final BaseClient baseClient;

    public FabricClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link Fabric} details for the specified fabric.
     *
     * @param resourceId fabric resource identifier as seen in HPE OneView.
     *
     * @return {@link Fabric} object containing the details.
     */
    public Fabric getById(String resourceId) {
        LOGGER.info("FabricClient : getById : Start");

        Fabric fabric = baseClient.getResource(
                UrlUtils.createUrl(FABRIC_URI, resourceId), Fabric.class);

        LOGGER.info("FabricClient : getById : End");

        return fabric;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link Fabric}&gt; containing details
     * for all the available fabrics found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link Fabric}&gt; containing
     * the details for all found fabrics.
     */
    public ResourceCollection<Fabric> getAll() {
        LOGGER.info("FabricClient : getAll : Start");

        ResourceCollection<Fabric> fabrics = baseClient.getResourceCollection(FABRIC_URI, Fabric.class);

        LOGGER.info("FabricClient : getAll : End");

        return fabrics;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link Fabric}&gt; containing details
     * for the available fabrics found under the current HPE OneView that match the name.
     *
     * @param name fabric name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link Fabric}&gt; containing
     * the details for the found fabrics.
     */
    public ResourceCollection<Fabric> getByName(String name) {
        LOGGER.info("FabricClient : getByName : Start");

        ResourceCollection<Fabric> fabrics = baseClient.getResourceCollection(
                FABRIC_URI, Fabric.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("FabricClient : getByName : End");

        return fabrics;
    }

    /**
     * Retrieves the {@link VlanPool} range for the fabric.
     *
     * @param resourceId fabric resource identifier as seen in HPE OneView.
     *
     * @return {@link VlanPool} object containing the details.
     */
    public VlanPool getReservedVlanRange(String resourceId) {
        LOGGER.info("FabricClient : getReservedVlanRange : Start");

        VlanPool vlanPoll = baseClient.getResource(
                UrlUtils.createUrl(FABRIC_URI, resourceId, RESERVED_VLAN_RANGE_URI), VlanPool.class);

        LOGGER.info("FabricClient : getReservedVlanRange : End");

        return vlanPoll;
    }

    /**
     * Updates a {@link VlanPool} range for the fabric.
     *
     * @param resourceId fabric resource identifier as seen in HPE OneView.
     * @param vlanPool object containing the range details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateReservedVlanRange(String resourceId, VlanPool vlanPool, boolean aSync) {
        LOGGER.info("FabricClient : updateReservedVlanRange : Start");

        TaskResourceV2 taskResource = baseClient.updateResource(
                UrlUtils.createUrl(FABRIC_URI, resourceId, RESERVED_VLAN_RANGE_URI), vlanPool, aSync);

        LOGGER.info("FabricClient : updateReservedVlanRange : End");

        return taskResource;
    }

}
