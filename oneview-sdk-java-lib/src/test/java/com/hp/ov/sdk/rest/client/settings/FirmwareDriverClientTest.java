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

package com.hp.ov.sdk.rest.client.settings;

import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.firmware.FwBaseline;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

@RunWith(MockitoJUnitRunner.class)
public class FirmwareDriverClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private FirmwareDriverClient client;

    @Test
    public void shouldGetFirmwareDriverById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.FIRMWARE_DRIVER_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, FwBaseline.class);
    }

    @Test
    public void shouldGetAllFirmwareDrivers() {
        client.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.FIRMWARE_DRIVER_URI, FwBaseline.class);
    }

    @Test
    public void shouldGetFirmwareDriverByName() {
        client.getByName(ANY_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.FIRMWARE_DRIVER_URI,
                FwBaseline.class, UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));
    }

    @Test
    public void shouldDeleteFirmwareDriver() {
        client.delete(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.FIRMWARE_DRIVER_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }

}
