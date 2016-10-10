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

package com.hp.ov.sdk.rest.client.server;

import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.dto.servers.serverprofiletemplate.ServerProfileTemplate;
import com.hp.ov.sdk.rest.client.common.CreatableResource;
import com.hp.ov.sdk.rest.client.common.DeletableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.client.common.UpdatableResource;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;
import com.hp.ov.sdk.rest.reflect.QueryParam;

@Api(ServerProfileTemplateClient.SERVER_PROFILE_TEMPLATE_URI)
public interface ServerProfileTemplateClient extends
        SearchableResource<ServerProfileTemplate>,
        CreatableResource<ServerProfileTemplate>,
        UpdatableResource<ServerProfileTemplate>,
        DeletableResource {

    String SERVER_PROFILE_TEMPLATE_URI = "/rest/server-profile-templates";
    String SERVER_PROFILE_TEMPLATE_TRANSFORMATION_URI = "/transformation";
    String SERVER_PROFILE_TEMPLATE_NEW_PROFILE_URI = "/new-profile";

    /**
     * A server profile object will be returned with the configuration based on this template.
     * This profile object can subsequently be used to create a new server profile.
     *
     * @param resourceId server profile template resource identifier as seen in HPE OneView.
     *
     * @return {@link ServerProfile} containing the server profile details.
     */
    @Endpoint(uri = "/{resourceId}" + SERVER_PROFILE_TEMPLATE_NEW_PROFILE_URI)
    ServerProfile getNewServerProfile(@PathParam("resourceId") String resourceId);

    /**
     * Transforms an existing server profile template by supplying a new server
     * hardware type and/or enclosure group. A profile will be returned with a
     * new configuration based on the capabilities of the supplied server
     * hardware type and/or enclosure group. All deployed connections will have
     * their port assignment set to 'Auto'. Re-selection of the server hardware
     * may also be required. The new profile can subsequently be used for the
     * PUT https://{appl}/rest/server-profiles/{id} API but is not guaranteed to
     * pass validation. Any incompatibilities will be flagged when the
     * transformed server profile is submitted.
     *
     * @param resourceId
     *            server profile template resource identifier as seen in HPE OneView.
     * @param serverHardwareTypeUri
     *            string specifying the server hardware type URI.
     * @param enclosureGroupUri
     *            string specifying the enclosure group URI.
     *
     * @return {@link ServerProfileTemplate} object containing the details.
     */
    @Endpoint(uri = "/{resourceId}" + SERVER_PROFILE_TEMPLATE_TRANSFORMATION_URI)
    ServerProfileTemplate getTransformation(@PathParam("resourceId") String resourceId,
            @QueryParam(key = "serverHardwareTypeUri") String serverHardwareTypeUri,
            @QueryParam(key = "enclosureGroupUri") String enclosureGroupUri);

}
