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

import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosure;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosurePortMap;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosureRefreshRequest;
import com.hp.ov.sdk.rest.client.common.PatchableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(DriveEnclosureClient.DRIVE_ENCLOSURE_URI)
public interface DriveEnclosureClient extends
        SearchableResource<DriveEnclosure>,
        PatchableResource {

    String DRIVE_ENCLOSURE_URI = "/rest/drive-enclosures";
    String PORT_MAP_URI = "/port-map";
    String REFRESH_STATE_URI = "/refreshState";

    /**
     * Retrieves the drive enclosure I/O adapter port to SAS interconnect port connectivity.
     *
     * @param resourceId Drive Enclosure resource identifier as seen in HPE OneView.
     *
     * @return the port map for the specified Drive Enclosure.
     */
    @Endpoint(uri = "/{resourceId}" + PORT_MAP_URI)
    DriveEnclosurePortMap getPortMap(@PathParam("resourceId") String resourceId);

    /**
     * Refreshes the Drive Enclosure to fix any configuration issue.
     *
     * @param resourceId Drive Enclosure resource identifier as seen in HPE OneView.
     * @param refreshStateConfig refresh state details to fix configuration issues.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + REFRESH_STATE_URI, method = HttpMethod.PUT)
    TaskResource updateRefreshState(@PathParam("resourceId") String resourceId,
            @BodyParam DriveEnclosureRefreshRequest refreshStateConfig, RequestOption... options);

}
