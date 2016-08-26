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
import com.hp.ov.sdk.dto.networking.sasinterconnect.SasInterconnectType;
import com.hp.ov.sdk.dto.networking.sasinterconnect.SasInterconnectTypeName;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class SasInterconnectTypeClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SasInterconnectTypeClient.class);

    protected static final String SAS_INTERCONNECT_TYPE_URI = "/rest/sas-interconnect-types";

    private final BaseClient baseClient;

    public SasInterconnectTypeClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link SasInterconnectType} details for the specified SAS interconnect type.
     *
     * @param resourceId SAS interconnect type resource identifier as seen in HPE OneView.
     *
     * @return {@link SasInterconnectType} object containing the details.
     */
    public SasInterconnectType getById(String resourceId) {
        LOGGER.info("SasInterconnectTypeClient : getById : Start");

        SasInterconnectType interconnectType = baseClient.getResource(
                UrlUtils.createUrl(SAS_INTERCONNECT_TYPE_URI, resourceId), SasInterconnectType.class);

        LOGGER.info("SasInterconnectTypeClient : getById : End");

        return interconnectType;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link SasInterconnectType}&gt; containing details
     * for all the available SAS interconnect types found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link SasInterconnectType}&gt; containing
     * the details for all found SAS interconnect types.
     */
    public ResourceCollection<SasInterconnectType> getAll() {
        LOGGER.info("SasInterconnectTypeClient : getAll : Start");

        ResourceCollection<SasInterconnectType> interconnectTypes = baseClient.getResourceCollection(
                SAS_INTERCONNECT_TYPE_URI, SasInterconnectType.class);

        LOGGER.info("SasInterconnectTypeClient : getAll : End");

        return interconnectTypes;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link SasInterconnectType}&gt; containing details
     * for the available SAS interconnect types found under the current HPE OneView that match the name.
     *
     * @param typeName {@link SasInterconnectTypeName} identifying the SAS interconnect type.
     *
     * @return {@link ResourceCollection}&lt;{@link SasInterconnectType}&gt; containing
     * the details for the found SAS interconnect types.
     */
    public ResourceCollection<SasInterconnectType> getByName(SasInterconnectTypeName typeName) {
        LOGGER.info("SasInterconnectTypeClient : getByName : Start");

        ResourceCollection<SasInterconnectType> interconnectTypes = this.getByName(typeName.getValue());

        LOGGER.info("SasInterconnectTypeClient : getByName : End");

        return interconnectTypes;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link SasInterconnectType}&gt; containing details
     * for the available SAS interconnect types found under the current HPE OneView that match the name.
     *
     * @param typeName SAS interconnect type name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link SasInterconnectType}&gt; containing
     * the details for the found SAS interconnect types.
     */
    public ResourceCollection<SasInterconnectType> getByName(String typeName) {
        LOGGER.info("SasInterconnectTypeClient : getByName : Start");

        ResourceCollection<SasInterconnectType> interconnectTypes = baseClient.getResourceCollection(
                SAS_INTERCONNECT_TYPE_URI, SasInterconnectType.class,
                UrlParameter.getFilterByNameParameter(typeName));

        LOGGER.info("SasInterconnectTypeClient : getByName : End");

        return interconnectTypes;
    }

}
