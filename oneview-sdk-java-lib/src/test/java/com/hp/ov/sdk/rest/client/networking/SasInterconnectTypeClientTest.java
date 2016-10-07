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

import static com.hp.ov.sdk.rest.client.networking.SasInterconnectTypeClient.SAS_INTERCONNECT_TYPE_URI;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.sasinterconnect.SasInterconnectType;
import com.hp.ov.sdk.dto.networking.sasinterconnect.SasInterconnectTypeName;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class SasInterconnectTypeClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private SasInterconnectTypeClient client = Reflection.newProxy(SasInterconnectTypeClient.class,
            new ClientRequestHandler<>(baseClient, SasInterconnectTypeClient.class));

    @Test
    public void shouldGetSasInterconnectTypeById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = SAS_INTERCONNECT_TYPE_URI
                + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(SasInterconnectType.class).getType());
    }

    @Test
    public void shouldGetAllSasInterconnectTypes() {
        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, SAS_INTERCONNECT_TYPE_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<SasInterconnectType>>() {}.getType());
    }

    @Test
    public void shouldGetSasInterconnectTypeByNameUsingEnum() {
        client.getByName(SasInterconnectTypeName.SYNERGY_12GB_SAS_CONNECTION_MODULE);

        Request expectedRequest = new Request(HttpMethod.GET, SAS_INTERCONNECT_TYPE_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(SasInterconnectTypeName.SYNERGY_12GB_SAS_CONNECTION_MODULE.getValue()));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<SasInterconnectType>>() {}.getType());
    }

    @Test
    public void shouldGetSasInterconnectTypeByNameUsingString() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, SAS_INTERCONNECT_TYPE_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<SasInterconnectType>>() {}.getType());
    }

}
