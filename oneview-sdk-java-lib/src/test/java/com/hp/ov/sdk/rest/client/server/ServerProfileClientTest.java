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

import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Optional;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.AvailableStorageSystem;
import com.hp.ov.sdk.dto.AvailableTargets;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ServerProfileCompliancePreview;
import com.hp.ov.sdk.dto.ServerProfileHealth;
import com.hp.ov.sdk.dto.generated.AvailableNetworks;
import com.hp.ov.sdk.dto.generated.AvailableServers;
import com.hp.ov.sdk.dto.generated.ProfilePorts;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class ServerProfileClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_FILTER = "random-Filter";
    private static final String ANY_HARDWARE_TYPE_URI = "random-Hardware";
    private static final String ANY_ENCLOSURE_GROUP_URI = "random-Enclosure";
    private static final String ANY_PROFILE_URI = "random-Profile";
    private static final String ANY_STORAGE_SYSTEM_ID = "random-Storage";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private ServerProfileClient client;

    @Test
    public void shouldGetServerProfileById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.SERVER_PROFILE_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, ServerProfile.class);
    }

    @Test
    public void shouldGetAllServerProfile() {
        client.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.SERVER_PROFILE_URI, ServerProfile.class);
    }

    @Test
    public void shouldGetServerProfilesByName() {
        client.getByName(ANY_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.SERVER_PROFILE_URI,
                ServerProfile.class, UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));
    }

    @Test
    public void shouldCreateServerProfile() {
        ServerProfile serverProfile = new ServerProfile();

        client.create(serverProfile, false);

        Request request = new Request(HttpMethodType.POST, ResourceUris.SERVER_PROFILE_URI, serverProfile);

        request.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldDeleteServerProfile() {
        client.delete(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.SERVER_PROFILE_URI + "/" + ANY_RESOURCE_ID;
        Request request = new Request(HttpMethodType.DELETE, expectedUri);

        request.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldDeleteServerProfileByFilter() {
        client.deleteByFilter(ANY_FILTER, false);

        Request expectedRequest = new Request(HttpMethodType.DELETE, ResourceUris.SERVER_PROFILE_URI);

        expectedRequest.addQuery(new UrlParameter("filter", ANY_FILTER));
        expectedRequest.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateServerProfile() {
        ServerProfile serverProfile = new ServerProfile();

        client.update(ANY_RESOURCE_ID, serverProfile, false);

        String expectedUri = ResourceUris.SERVER_PROFILE_URI + "/" + ANY_RESOURCE_ID;
        Request request = new Request(HttpMethodType.PUT, expectedUri, serverProfile);

        request.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldUpdatePatchServerProfile() {
        Patch patch = new Patch();

        client.patch(ANY_RESOURCE_ID, patch, false);

        String expectedUri = ResourceUris.SERVER_PROFILE_URI + "/" + ANY_RESOURCE_ID;
        Request request = new Request(HttpMethodType.PATCH, expectedUri, patch);

        request.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(request, false);
    }

    @Test
    public void shouldGetServerProfileCompliancePreview() {
        client.getCompliancePreview(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.SERVER_PROFILE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.SERVER_PROFILE_COMPLIANCE_PREVIEW_URI;

        then(baseClient).should().getResource(expectedUri, ServerProfileCompliancePreview.class);
    }

    @Test
    public void shouldGetServerProfileMessages() {
        client.getMessages(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.SERVER_PROFILE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.SERVER_PROFILE_COMPLIANCE_MESSAGES_URI;

        then(baseClient).should().getResource(expectedUri, ServerProfileHealth.class);
    }

    @Test
    public void shouldGetServerProfileTransformation() {
        client.getTransformation(ANY_RESOURCE_ID, ANY_HARDWARE_TYPE_URI, ANY_ENCLOSURE_GROUP_URI);

        String expectedUri = ResourceUris.SERVER_PROFILE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.SERVER_PROFILE_COMPLIANCE_TRANSFORMATION_URI;

        Request expectedRequest = new Request(HttpMethodType.GET, expectedUri);

        expectedRequest.addQuery(new UrlParameter("serverHardwareTypeUri", ANY_HARDWARE_TYPE_URI));
        expectedRequest.addQuery(new UrlParameter("enclosureGroupUri", ANY_ENCLOSURE_GROUP_URI));

        then(baseClient).should().executeRequest(expectedRequest, ServerProfile.class);
    }

    @Test
    public void shouldGetServerProfileAvailableNetworks() {
        client.getAvailableNetworks(ANY_HARDWARE_TYPE_URI, ANY_ENCLOSURE_GROUP_URI);

        Request expectedRequest = new Request(HttpMethodType.GET, ResourceUris.AVAILABLE_NETWORKS_URI);

        expectedRequest.addQuery(new UrlParameter("serverHardwareTypeUri", ANY_HARDWARE_TYPE_URI));
        expectedRequest.addQuery(new UrlParameter("enclosureGroupUri", ANY_ENCLOSURE_GROUP_URI));

        then(baseClient).should().executeRequest(expectedRequest, AvailableNetworks.class);
    }

    @Test
    public void shouldGetAvailableServers() {
        client.getAvailableServers();

        then(baseClient).should().getResourceList(ResourceUris.AVAILABLE_SERVERS_URI, AvailableServers.class);
    }

    @Test
    public void shouldGetServerProfileAvailableServersByHardwareTypeAndEnclosureGroup() {
        client.getAvailableServers(ANY_HARDWARE_TYPE_URI, ANY_ENCLOSURE_GROUP_URI);

        then(baseClient).should().getResourceList(ResourceUris.AVAILABLE_SERVERS_URI,
                AvailableServers.class,
                new UrlParameter("serverHardwareTypeUri", ANY_HARDWARE_TYPE_URI),
                new UrlParameter("enclosureGroupUri", ANY_ENCLOSURE_GROUP_URI));
    }

    @Test
    public void shouldGetServerProfileAvailableServersByProfileUri() {
        client.getAvailableServers(ANY_PROFILE_URI);

        then(baseClient).should().getResourceList(ResourceUris.AVAILABLE_SERVERS_URI,
                AvailableServers.class,
                new UrlParameter("profileUri", ANY_PROFILE_URI));
    }

    @Test
    public void shouldGetServerProfileAvailableStorageSystems() {
        client.getAvailableStorageSystems(ANY_HARDWARE_TYPE_URI, ANY_ENCLOSURE_GROUP_URI);

        then(baseClient).should().getResourceCollection(ResourceUris.AVAILABLE_STORAGE_SYSTEMS,
                AvailableStorageSystem.class,
                new UrlParameter("serverHardwareTypeUri", ANY_HARDWARE_TYPE_URI),
                new UrlParameter("enclosureGroupUri", ANY_ENCLOSURE_GROUP_URI));
    }

    @Test
    public void shouldGetServerProfileAvailableStorageSystem() {
        client.getAvailableStorageSystem(ANY_HARDWARE_TYPE_URI, ANY_ENCLOSURE_GROUP_URI, ANY_STORAGE_SYSTEM_ID);

        then(baseClient).should().getResource(ResourceUris.AVAILABLE_STORAGE_SYSTEM,
                AvailableStorageSystem.class,
                new UrlParameter("serverHardwareTypeUri", ANY_HARDWARE_TYPE_URI),
                new UrlParameter("enclosureGroupUri", ANY_ENCLOSURE_GROUP_URI),
                new UrlParameter("storageSystemId", ANY_STORAGE_SYSTEM_ID));
    }

    @Test
    public void shouldGetServerProfileAvailableTargets() {
        client.getAvailableTargets(Optional.of(ANY_HARDWARE_TYPE_URI),
                Optional.of(ANY_ENCLOSURE_GROUP_URI),
                Optional.of(ANY_PROFILE_URI));

        then(baseClient).should().getResource(ResourceUris.AVAILABLE_TARGETS,
                AvailableTargets.class,
                new UrlParameter("serverHardwareTypeUri", ANY_HARDWARE_TYPE_URI),
                new UrlParameter("enclosureGroupUri", ANY_ENCLOSURE_GROUP_URI),
                new UrlParameter("profileUri", ANY_PROFILE_URI));
    }

    @Test
    public void shouldGetServerProfilePorts() {
        client.getProfilePorts(ANY_HARDWARE_TYPE_URI, ANY_ENCLOSURE_GROUP_URI);

        then(baseClient).should().getResource(ResourceUris.PROFILE_PORTS_URI,
                ProfilePorts.class,
                new UrlParameter("serverHardwareTypeUri", ANY_HARDWARE_TYPE_URI),
                new UrlParameter("enclosureGroupUri", ANY_ENCLOSURE_GROUP_URI));
    }

}
