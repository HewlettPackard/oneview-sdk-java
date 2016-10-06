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

import com.hp.ov.sdk.dto.storage.Drive;
import com.hp.ov.sdk.dto.storage.saslogicaljbod.SasLogicalJbod;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(SasLogicalJbodClient.SAS_LOGICAL_JBOD_URI)
public interface SasLogicalJbodClient extends SearchableResource<SasLogicalJbod> {

    String SAS_LOGICAL_JBOD_URI = "/rest/sas-logical-jbods";
    String DRIVES = "/drives";

    /**
     * Retrieves a {@link List}&lt;{@link Drive}&gt; containing details for the drives
     * allocated to the SAS logical JBOD.
     *
     * @param resourceId SAS logical JBOD resource identifier as seen in HPE OneView.
     *
     * @return {@link List}&lt;{@link Drive}&gt; containing the details for the found drives
     * allocated to the given SAS logical JBOD.
     */
    @Endpoint(uri = "/{resourceId}" + DRIVES)
    List<Drive> getDrives(@PathParam("resourceId") String resourceId);

}
