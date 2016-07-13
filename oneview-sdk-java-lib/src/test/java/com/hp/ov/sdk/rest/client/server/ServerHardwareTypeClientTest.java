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

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.ServerHardwareType;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.ServerHardwareTypeUpdate;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class ServerHardwareTypeClientTest {

    private static final String ANY_SERVER_HARDWARE_TYPE_RESOURCE_ID = "random-UUID";
    private static final String ANY_SERVER_HARDWARE_TYPE_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private ServerHardwareTypeClient serverHardwareTypeClient;

    @Test
    public void shouldGetServerHardwareType() {
        serverHardwareTypeClient.getById(ANY_SERVER_HARDWARE_TYPE_RESOURCE_ID);

        String expectedUri = ResourceUris.SERVER_HARDWARE_TYPE_URI + "/" + ANY_SERVER_HARDWARE_TYPE_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, ServerHardwareType.class);
    }

    @Test
    public void shouldGetAllServerHardwareTypes() {
        serverHardwareTypeClient.getAll();

        then(baseClient).should().getResourceCollection(
                ResourceUris.SERVER_HARDWARE_TYPE_URI, ServerHardwareType.class);
    }

    @Test
    public void shouldGetServerHardwareTypeByName() {
        serverHardwareTypeClient.getByName(ANY_SERVER_HARDWARE_TYPE_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(
                ResourceUris.SERVER_HARDWARE_TYPE_URI,
                ServerHardwareType.class,
                UrlParameter.getFilterByNameParameter(ANY_SERVER_HARDWARE_TYPE_RESOURCE_NAME));
    }

    @Test
    public void shouldUpdateServerHardwareType() {
        ServerHardwareTypeUpdate serverHardwareTypeUpdate = new ServerHardwareTypeUpdate();

        serverHardwareTypeClient.update(ANY_SERVER_HARDWARE_TYPE_RESOURCE_ID, serverHardwareTypeUpdate);

        String expectedUri = ResourceUris.SERVER_HARDWARE_TYPE_URI + "/" + ANY_SERVER_HARDWARE_TYPE_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri, serverHardwareTypeUpdate);

        then(baseClient).should().executeRequest(expectedRequest, ServerHardwareType.class);
    }

    @Test
    public void shouldDeleteServerHardwareType() {
        serverHardwareTypeClient.delete(ANY_SERVER_HARDWARE_TYPE_RESOURCE_ID, false);

        String expectedUri = ResourceUris.SERVER_HARDWARE_TYPE_URI + "/" + ANY_SERVER_HARDWARE_TYPE_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }

}
