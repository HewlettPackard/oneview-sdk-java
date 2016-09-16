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

import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosure;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosurePortMap;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosureRefreshRequest;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class DriveEnclosureClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private DriveEnclosureClient driveEnclosureClient;

    @Test
    public void shouldGetDriveEnclosureById() {
        driveEnclosureClient.getById(ANY_RESOURCE_ID);

        String expectedUri = DriveEnclosureClient.DRIVE_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, DriveEnclosure.class);
    }

    @Test
    public void shouldGetAllDriveEnclosures() {
        driveEnclosureClient.getAll();

        then(baseClient).should().getResourceCollection(DriveEnclosureClient.DRIVE_ENCLOSURE_URI, DriveEnclosure.class);
    }

    @Test
    public void shouldGetDriveEnclosuresByName() {
        driveEnclosureClient.getByName(ANY_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(DriveEnclosureClient.DRIVE_ENCLOSURE_URI,
                DriveEnclosure.class, UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));
    }

    @Test
    public void shouldGetDriveEnclosurePortMap() {
        driveEnclosureClient.getPortMap(ANY_RESOURCE_ID);

        String expectedUri = DriveEnclosureClient.DRIVE_ENCLOSURE_URI
                + "/"
                + ANY_RESOURCE_ID
                + "/"
                + DriveEnclosureClient.PORT_MAP_URI;

        then(baseClient).should().getResource(expectedUri, DriveEnclosurePortMap.class);
    }

    @Test
    public void shouldPatchDriveEnclosure() {
        Patch patch = new Patch();

        driveEnclosureClient.patch(ANY_RESOURCE_ID, patch, false);

        String expectedUri = DriveEnclosureClient.DRIVE_ENCLOSURE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PATCH, expectedUri, patch);

        expectedRequest.setContentType(ContentType.APPLICATION_JSON_PATCH);
        expectedRequest.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateDriveEnclosureRefreshState() {
        DriveEnclosureRefreshRequest refreshStateConfig = new DriveEnclosureRefreshRequest();

        driveEnclosureClient.updateRefreshState(ANY_RESOURCE_ID, refreshStateConfig, false);

        String expectedUri = DriveEnclosureClient.DRIVE_ENCLOSURE_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + DriveEnclosureClient.REFRESH_STATE_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, refreshStateConfig);

        expectedRequest.setTimeout(1200000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

}
