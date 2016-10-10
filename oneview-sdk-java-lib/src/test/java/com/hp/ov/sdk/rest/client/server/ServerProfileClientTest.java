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

import static com.hp.ov.sdk.rest.client.server.ServerProfileClient.SERVER_PROFILE_AVAILABLE_NETWORKS_URI;
import static com.hp.ov.sdk.rest.client.server.ServerProfileClient.SERVER_PROFILE_AVAILABLE_SERVERS_URI;
import static com.hp.ov.sdk.rest.client.server.ServerProfileClient.SERVER_PROFILE_AVAILABLE_STORAGE_SYSTEMS_URI;
import static com.hp.ov.sdk.rest.client.server.ServerProfileClient.SERVER_PROFILE_AVAILABLE_STORAGE_SYSTEM_URI;
import static com.hp.ov.sdk.rest.client.server.ServerProfileClient.SERVER_PROFILE_AVAILABLE_TARGETS_URI;
import static com.hp.ov.sdk.rest.client.server.ServerProfileClient.SERVER_PROFILE_COMPLIANCE_MESSAGES_URI;
import static com.hp.ov.sdk.rest.client.server.ServerProfileClient.SERVER_PROFILE_COMPLIANCE_PREVIEW_URI;
import static com.hp.ov.sdk.rest.client.server.ServerProfileClient.SERVER_PROFILE_COMPLIANCE_TRANSFORMATION_URI;
import static com.hp.ov.sdk.rest.client.server.ServerProfileClient.SERVER_PROFILE_PROFILE_PORTS_URI;
import static com.hp.ov.sdk.rest.client.server.ServerProfileClient.SERVER_PROFILE_URI;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.AvailableStorageSystem;
import com.hp.ov.sdk.dto.AvailableTargets;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.ServerProfileCompliancePreview;
import com.hp.ov.sdk.dto.ServerProfileHealth;
import com.hp.ov.sdk.dto.generated.AvailableNetworks;
import com.hp.ov.sdk.dto.generated.AvailableServers;
import com.hp.ov.sdk.dto.generated.ProfilePorts;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class ServerProfileClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_FILTER = "random-Filter";
    private static final String ANY_HARDWARE_TYPE_URI = "random-Hardware";
    private static final String ANY_ENCLOSURE_GROUP_URI = "random-Enclosure";
    private static final String ANY_PROFILE_URI = "random-Profile";
    private static final String ANY_STORAGE_SYSTEM_ID = "random-Storage";

    private BaseClient baseClient = mock(BaseClient.class);
    private ServerProfileClient client = Reflection.newProxy(ServerProfileClient.class,
            new ClientRequestHandler<>(baseClient, ServerProfileClient.class));

    @Test
    public void shouldGetServerProfileById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = SERVER_PROFILE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(ServerProfile.class).getType());
    }

    @Test
    public void shouldGetAllServerProfile() {
        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, SERVER_PROFILE_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<ServerProfile>>() {}.getType());
    }

    @Test
    public void shouldGetServerProfilesByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, SERVER_PROFILE_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<ServerProfile>>() {}.getType());
    }

    @Test
    public void shouldCreateServerProfile() {
        ServerProfile serverProfile = new ServerProfile();

        client.create(serverProfile, TaskTimeout.of(321));

        Request expectedRequest = new Request(HttpMethod.POST, SERVER_PROFILE_URI, serverProfile);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldDeleteServerProfile() {
        client.delete(ANY_RESOURCE_ID, TaskTimeout.of(321));

        String expectedUri = SERVER_PROFILE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldDeleteServerProfileByFilter() {
        client.deleteByFilter(ANY_FILTER, TaskTimeout.of(321));

        Request expectedRequest = new Request(HttpMethod.DELETE,
                SERVER_PROFILE_URI + "?filter=" + ANY_FILTER);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateServerProfile() {
        ServerProfile serverProfile = new ServerProfile();

        client.update(ANY_RESOURCE_ID, serverProfile, TaskTimeout.of(321));

        String expectedUri = SERVER_PROFILE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, serverProfile);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdatePatchServerProfile() {
        Patch patch = new Patch();

        client.patch(ANY_RESOURCE_ID, patch, TaskTimeout.of(321));

        String expectedUri = SERVER_PROFILE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PATCH, expectedUri, patch);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetServerProfileCompliancePreview() {
        client.getCompliancePreview(ANY_RESOURCE_ID);

        String expectedUri = SERVER_PROFILE_URI
                + "/" + ANY_RESOURCE_ID
                + SERVER_PROFILE_COMPLIANCE_PREVIEW_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(ServerProfileCompliancePreview.class).getType());
    }

    @Test
    public void shouldGetServerProfileMessages() {
        client.getMessages(ANY_RESOURCE_ID);

        String expectedUri = SERVER_PROFILE_URI
                + "/" + ANY_RESOURCE_ID
                + SERVER_PROFILE_COMPLIANCE_MESSAGES_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(ServerProfileHealth.class).getType());
    }

    @Test
    public void shouldGetServerProfileTransformation() {
        client.getTransformation(ANY_RESOURCE_ID, ANY_HARDWARE_TYPE_URI, ANY_ENCLOSURE_GROUP_URI);

        String expectedUri = SERVER_PROFILE_URI
                + "/" + ANY_RESOURCE_ID
                + SERVER_PROFILE_COMPLIANCE_TRANSFORMATION_URI
                + "?serverHardwareTypeUri=" + ANY_HARDWARE_TYPE_URI
                + "&enclosureGroupUri=" + ANY_ENCLOSURE_GROUP_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(ServerProfile.class).getType());
    }

    @Test
    public void shouldGetServerProfileAvailableNetworks() {
        client.getAvailableNetworks(ANY_HARDWARE_TYPE_URI, ANY_ENCLOSURE_GROUP_URI);

        Request expectedRequest = new Request(HttpMethod.GET,
                SERVER_PROFILE_URI
                + SERVER_PROFILE_AVAILABLE_NETWORKS_URI
                + "?serverHardwareTypeUri=" + ANY_HARDWARE_TYPE_URI
                + "&enclosureGroupUri=" + ANY_ENCLOSURE_GROUP_URI);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(AvailableNetworks.class).getType());
    }

    @Test
    public void shouldGetAvailableServers() {
        client.getAvailableServers();

        String expectedUri = SERVER_PROFILE_URI
                + SERVER_PROFILE_AVAILABLE_SERVERS_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<List<AvailableServers>>() {}.getType());
    }

    @Test
    public void shouldGetServerProfileAvailableServersByHardwareTypeAndEnclosureGroup() {
        client.getAvailableServers(ANY_HARDWARE_TYPE_URI, ANY_ENCLOSURE_GROUP_URI);

        String expectedUri = SERVER_PROFILE_URI
                + SERVER_PROFILE_AVAILABLE_SERVERS_URI
                + "?serverHardwareTypeUri=" + ANY_HARDWARE_TYPE_URI
                + "&enclosureGroupUri=" + ANY_ENCLOSURE_GROUP_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<List<AvailableServers>>() {}.getType());
    }

    @Test
    public void shouldGetServerProfileAvailableServersByProfileUri() {
        client.getAvailableServers(ANY_PROFILE_URI);

        String expectedUri = SERVER_PROFILE_URI
                + SERVER_PROFILE_AVAILABLE_SERVERS_URI
                + "?profileUri=" + ANY_PROFILE_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<List<AvailableServers>>() {}.getType());
    }

    @Test
    public void shouldGetServerProfileAvailableStorageSystems() {
        client.getAvailableStorageSystems(ANY_HARDWARE_TYPE_URI, ANY_ENCLOSURE_GROUP_URI);

        String expectedUri = SERVER_PROFILE_URI
                + SERVER_PROFILE_AVAILABLE_STORAGE_SYSTEMS_URI
                + "?serverHardwareTypeUri=" + ANY_HARDWARE_TYPE_URI
                + "&enclosureGroupUri=" + ANY_ENCLOSURE_GROUP_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<AvailableStorageSystem>>() {}.getType());
    }

    @Test
    public void shouldGetServerProfileAvailableStorageSystem() {
        client.getAvailableStorageSystem(ANY_HARDWARE_TYPE_URI, ANY_ENCLOSURE_GROUP_URI, ANY_STORAGE_SYSTEM_ID);

        String expectedUri = SERVER_PROFILE_URI
                + SERVER_PROFILE_AVAILABLE_STORAGE_SYSTEM_URI
                + "?serverHardwareTypeUri=" + ANY_HARDWARE_TYPE_URI
                + "&enclosureGroupUri=" + ANY_ENCLOSURE_GROUP_URI
                + "&storageSystemId=" + ANY_STORAGE_SYSTEM_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(AvailableStorageSystem.class).getType());
    }

    @Test
    public void shouldGetServerProfileAvailableTargets() {
        client.getAvailableTargets(ANY_HARDWARE_TYPE_URI,
                ANY_ENCLOSURE_GROUP_URI,
                ANY_PROFILE_URI);

        String expectedUri = SERVER_PROFILE_URI
                + SERVER_PROFILE_AVAILABLE_TARGETS_URI
                + "?serverHardwareTypeUri=" + ANY_HARDWARE_TYPE_URI
                + "&enclosureGroupUri=" + ANY_ENCLOSURE_GROUP_URI
                + "&profileUri=" + ANY_PROFILE_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(AvailableTargets.class).getType());
    }

    @Test
    public void shouldGetServerProfilePorts() {
        client.getProfilePorts(ANY_HARDWARE_TYPE_URI, ANY_ENCLOSURE_GROUP_URI);

        String expectedUri = SERVER_PROFILE_URI
                + SERVER_PROFILE_PROFILE_PORTS_URI
                + "?serverHardwareTypeUri=" + ANY_HARDWARE_TYPE_URI
                + "&enclosureGroupUri=" + ANY_ENCLOSURE_GROUP_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(ProfilePorts.class).getType());
    }

}
