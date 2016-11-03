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
package com.hpe.i3s.client.statelessserver;

import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;
import com.hpe.i3s.dto.statelessserver.osvolume.OsVolume;

@Api(OsVolumeClient.OS_VOLUME_URI)
public interface OsVolumeClient extends
        SearchableResource<OsVolume> {

    String OS_VOLUME_URI = "/rest/os-volumes";
    String OS_VOLUME_ARCHIVE_URI = "/archive";

    /**
     * Retrieves details of the archived OS volume identified by the given <code>resourceId</code>.
     *
     * @param resourceId OS Volume resource identifier.
     *
     * @return {@link OsVolume} containing the details of the resource identified by <code>resourceId</code>.
     */
    @Endpoint(uri = OS_VOLUME_ARCHIVE_URI + "/{resourceId}")
    OsVolume getArchivedOsVolume(@PathParam("resourceId") String resourceId);

}
