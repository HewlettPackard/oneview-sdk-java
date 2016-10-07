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

import static com.hp.ov.sdk.rest.client.storage.DriveEnclosureClient.DRIVE_ENCLOSURE_URI;
import static com.hp.ov.sdk.rest.client.storage.DriveEnclosureClient.PORT_MAP_URI;
import static com.hp.ov.sdk.rest.client.storage.DriveEnclosureClient.REFRESH_STATE_URI;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosure;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosurePortMap;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosureRefreshRequest;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class DriveEnclosureClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private DriveEnclosureClient client = Reflection.newProxy(DriveEnclosureClient.class,
            new ClientRequestHandler<>(baseClient, DriveEnclosureClient.class));

    @Test
    public void shouldGetDriveEnclosureById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = DRIVE_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(DriveEnclosure.class).getType());
    }

    @Test
    public void shouldGetAllDriveEnclosures() {
        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, DRIVE_ENCLOSURE_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<DriveEnclosure>>() {}.getType());
    }

    @Test
    public void shouldGetDriveEnclosuresByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, DRIVE_ENCLOSURE_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<DriveEnclosure>>() {}.getType());
    }

    @Test
    public void shouldGetDriveEnclosurePortMap() {
        client.getPortMap(ANY_RESOURCE_ID);

        String expectedUri = DRIVE_ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + PORT_MAP_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(DriveEnclosurePortMap.class).getType());
    }

    @Test
    public void shouldPatchDriveEnclosure() {
        Patch patch = new Patch();

        client.patch(ANY_RESOURCE_ID, patch, TaskTimeout.of(321));

        String expectedUri = DRIVE_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PATCH, expectedUri, patch);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateDriveEnclosureRefreshState() {
        DriveEnclosureRefreshRequest refreshStateConfig = new DriveEnclosureRefreshRequest();

        client.updateRefreshState(ANY_RESOURCE_ID, refreshStateConfig, TaskTimeout.of(321));

        String expectedUri = DRIVE_ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + REFRESH_STATE_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, refreshStateConfig);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

}
