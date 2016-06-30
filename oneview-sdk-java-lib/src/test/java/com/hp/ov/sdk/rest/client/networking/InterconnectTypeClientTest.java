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

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.dto.InterconnectTypeName;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;

@RunWith(MockitoJUnitRunner.class)
public class InterconnectTypeClientTest {

    private static final String ANY_INTERCONNECT_TYPE_RESOURCE_ID = "random-UUID";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private InterconnectTypeClient interconnectTypeClient;

    @Test
    public void shouldGetInterconnectType() {
        interconnectTypeClient.getById(ANY_INTERCONNECT_TYPE_RESOURCE_ID);

        String expectedUri = ResourceUris.INTERCONNECT_TYPE_URI + "/" + ANY_INTERCONNECT_TYPE_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, InterconnectType.class);
    }

    @Test
    public void shouldGetAllInterconnectType() {
        interconnectTypeClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.INTERCONNECT_TYPE_URI, InterconnectType.class);
    }

    @Test
    public void shouldGetInterconnectTypeCollectionByName() {
        interconnectTypeClient.getByName(InterconnectTypeName.HP_VC_8Gb_20_Port_FC_Module);

        then(baseClient).should().getResourceCollection(ResourceUris.INTERCONNECT_TYPE_URI,
                InterconnectType.class,
                UrlParameter.getFilterByNameParameter(InterconnectTypeName.HP_VC_8Gb_20_Port_FC_Module.getValue()));
    }

}
