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

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.storage.Drive;
import com.hp.ov.sdk.dto.storage.saslogicaljbod.SasLogicalJbod;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

@RunWith(MockitoJUnitRunner.class)
public class SasLogicalJbodClientTest {

    private static final String ANY_SAS_LOGICAL_RESOURCE_ID = "random-UUID";
    private static final String ANY_SAS_LOGICAL_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private SasLogicalJbodClient sasLogicalJbodClient;

    @Test
    public void shouldGetSasLogicalJbodById() {
        sasLogicalJbodClient.getById(ANY_SAS_LOGICAL_RESOURCE_ID);

        String expectedUri = SasLogicalJbodClient.SAS_LOGICAL_JBOD_URI
                + "/" + ANY_SAS_LOGICAL_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, SasLogicalJbod.class);
    }

    @Test
    public void shouldGetAllSasLogicalJbods() {
        sasLogicalJbodClient.getAll();

        then(baseClient).should().getResourceCollection(
                SasLogicalJbodClient.SAS_LOGICAL_JBOD_URI, SasLogicalJbod.class);
    }

    @Test
    public void shouldGetSasLogicalJbodByName() {
        sasLogicalJbodClient.getByName(ANY_SAS_LOGICAL_NAME);

        then(baseClient).should().getResourceCollection(
                SasLogicalJbodClient.SAS_LOGICAL_JBOD_URI,
                SasLogicalJbod.class,
                UrlParameter.getFilterByNameParameter(ANY_SAS_LOGICAL_NAME));
    }

    @Test
    public void shouldGetSasLogicalJbodDrives() {
        sasLogicalJbodClient.getDrives(ANY_SAS_LOGICAL_RESOURCE_ID);

        String expectedUri = SasLogicalJbodClient.SAS_LOGICAL_JBOD_URI
                + "/" + ANY_SAS_LOGICAL_RESOURCE_ID
                + "/" + SasLogicalJbodClient.DRIVES;

        then(baseClient).should().getResourceList(
                expectedUri, new TypeToken<List<Drive>>() {});
    }

}
