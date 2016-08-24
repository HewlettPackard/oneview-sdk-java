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

package com.hp.ov.sdk.rest.client.networking;

import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.dto.networking.saslogicalinterconnectgroup.SasLogicalInterconnectGroup;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

@RunWith(MockitoJUnitRunner.class)
public class SasLogicalInterconnectGroupClientTest {

    private static final String ANY_SAS_LOGICAL_INTERCONNECT_GROUP_RESOURCE_ID = "random-UUID";
    private static final String ANY_SAS_LOGICAL_INTERCONNECT_GROUP_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private SasLogicalInterconnectGroupClient sasLogicalInterconnectGroupClient;

    @Test
    public void shouldGetSasLogicalInterconnectGroupById() {
        sasLogicalInterconnectGroupClient.getById(ANY_SAS_LOGICAL_INTERCONNECT_GROUP_RESOURCE_ID);

        String expectedUri = SasLogicalInterconnectGroupClient.SAS_LOGICAL_INTERCONNECT_GROUP_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_GROUP_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, SasLogicalInterconnectGroup.class);
    }

    @Test
    public void shouldGetAllSasLogicalInterconnectGroups() {
        sasLogicalInterconnectGroupClient.getAll();

        then(baseClient).should().getResourceCollection(
                SasLogicalInterconnectGroupClient.SAS_LOGICAL_INTERCONNECT_GROUP_URI,
                SasLogicalInterconnectGroup.class);
    }

    @Test
    public void shouldGetSasLogicalInterconnectGroupsByName() {
        sasLogicalInterconnectGroupClient.getByName(ANY_SAS_LOGICAL_INTERCONNECT_GROUP_NAME);

        then(baseClient).should().getResourceCollection(
                SasLogicalInterconnectGroupClient.SAS_LOGICAL_INTERCONNECT_GROUP_URI,
                SasLogicalInterconnectGroup.class,
                UrlParameter.getFilterByNameParameter(ANY_SAS_LOGICAL_INTERCONNECT_GROUP_NAME));
    }

    @Test
    public void shouldCreateSasLogicalInterconnectGroup() {
        SasLogicalInterconnectGroup group = new SasLogicalInterconnectGroup();

        sasLogicalInterconnectGroupClient.create(group, false);

        then(baseClient).should().createResource(
                SasLogicalInterconnectGroupClient.SAS_LOGICAL_INTERCONNECT_GROUP_URI, group, false);
    }

    @Test
    public void shouldUpdateSasLogicalInterconnectGroup() {
        SasLogicalInterconnectGroup group = new SasLogicalInterconnectGroup();

        sasLogicalInterconnectGroupClient.update(ANY_SAS_LOGICAL_INTERCONNECT_GROUP_RESOURCE_ID, group, false);

        String expectedUri = SasLogicalInterconnectGroupClient.SAS_LOGICAL_INTERCONNECT_GROUP_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_GROUP_RESOURCE_ID;

        then(baseClient).should().updateResource(expectedUri, group, false);
    }

    @Test
    public void shouldDeleteSasLogicalInterconnectGroup() {
        sasLogicalInterconnectGroupClient.delete(ANY_SAS_LOGICAL_INTERCONNECT_GROUP_RESOURCE_ID, false);

        String expectedUri = SasLogicalInterconnectGroupClient.SAS_LOGICAL_INTERCONNECT_GROUP_URI
                + "/" + ANY_SAS_LOGICAL_INTERCONNECT_GROUP_RESOURCE_ID;

        then(baseClient).should().deleteResource(expectedUri, false);
    }
}
