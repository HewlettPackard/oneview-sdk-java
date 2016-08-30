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

package com.hp.ov.sdk.rest.client.storage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.storage.Drive;
import com.hp.ov.sdk.dto.storage.saslogicaljbod.SasLogicalJbod;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class SasLogicalJbodClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SasLogicalJbodClient.class);

    protected static final String SAS_LOGICAL_JBOD_URI = "/rest/sas-logical-jbods";
    protected static final String DRIVES = "drives";

    private final BaseClient baseClient;

    public SasLogicalJbodClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link SasLogicalJbod} details for the specified SAS logical JBOD.
     *
     * @param resourceId SAS logical JBOD resource identifier as seen in HPE OneView.
     *
     * @return {@link SasLogicalJbod} object containing the details.
     */
    public SasLogicalJbod getById(String resourceId) {
        LOGGER.info("SasLogicalJbodClient : getById : Start");

        SasLogicalJbod sasLogicalJbod = baseClient.getResource(
                UrlUtils.createUrl(SAS_LOGICAL_JBOD_URI, resourceId), SasLogicalJbod.class);

        LOGGER.info("SasLogicalJbodClient : getById : End");

        return sasLogicalJbod;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link SasLogicalJbod}&gt; containing details
     * for all the available SAS logical JBOD found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link SasLogicalJbod}&gt; containing
     * the details for all found SAS logical JBODs.
     */
    public ResourceCollection<SasLogicalJbod> getAll() {
        LOGGER.info("SasLogicalJbodClient : getAll : Start");

        ResourceCollection<SasLogicalJbod> saslogicalJbods = baseClient.getResourceCollection(
                SAS_LOGICAL_JBOD_URI, SasLogicalJbod.class);

        LOGGER.info("SasLogicalJbodClient : getAll : End");

        return saslogicalJbods;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link SasLogicalJbod}&gt; containing details
     * for the available SAS logical JBOD found under the current HPE OneView that match the name.
     *
     * @param name SAS logical JBOD name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link SasLogicalJbod}&gt; containing
     * the details for the found SAS logical JBODs.
     */
    public ResourceCollection<SasLogicalJbod> getByName(String name) {
        LOGGER.info("SasLogicalJbodClient : getByName : Start");

        ResourceCollection<SasLogicalJbod> saslogicalJbods = baseClient.getResourceCollection(
                SAS_LOGICAL_JBOD_URI, SasLogicalJbod.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("SasLogicalJbodClient : getByName : End");

        return saslogicalJbods;
    }

    /**
     * Retrieves a {@link List}&lt;{@link Drive}&gt; containing details for the drives
     * allocated to the SAS logical JBOD.
     *
     * @param resourceId SAS logical JBOD resource identifier as seen in HPE OneView.
     *
     * @return {@link List}&lt;{@link Drive}&gt; containing the details for the found drives
     * allocated to the given SAS logical JBOD.
     */
    public List<Drive> getDrives(String resourceId) {
        LOGGER.info("SasLogicalJbodClient : getDrives : Start");

        List<Drive> drives = baseClient.getResourceList(
                UrlUtils.createUrl(SAS_LOGICAL_JBOD_URI, resourceId, DRIVES), new TypeToken<List<Drive>>() {});

        LOGGER.info("SasLogicalJbodClient : getDrives : End");

        return drives;
    }

}
