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

import static com.hp.ov.sdk.rest.client.storage.FcSanManagedSanClient.FC_SANS_MANAGED_SAN_ENDPOINTS_URI;
import static com.hp.ov.sdk.rest.client.storage.FcSanManagedSanClient.FC_SANS_MANAGED_SAN_ISSUES_URI;
import static com.hp.ov.sdk.rest.client.storage.FcSanManagedSanClient.FC_SANS_MANAGED_SAN_URI;
import static com.hp.ov.sdk.rest.client.storage.FcSanManagedSanClient.FC_SANS_WWN_LOCATE_URI;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.EndpointResponse;
import com.hp.ov.sdk.dto.EndpointsCsvFileResponse;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.fcsans.LocateSanResponse;
import com.hp.ov.sdk.dto.fcsans.SanRequest;
import com.hp.ov.sdk.dto.fcsans.SanResponse;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class FcSanManagedSanClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_WWN = "11:22:33:44:55:66:77:88";

    private BaseClient baseClient = mock(BaseClient.class);
    private FcSanManagedSanClient client = Reflection.newProxy(FcSanManagedSanClient.class,
            new ClientRequestHandler<>(baseClient, FcSanManagedSanClient.class));

    @Test
    public void shouldGetFcSanManagedSanById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = FcSanManagedSanClient.FC_SANS_MANAGED_SAN_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(SanResponse.class).getType());
    }

    @Test
    public void shouldGetAllFcSanManagedSan() {
        client.getAll();
        Request expectedRequest = new Request(HttpMethod.GET, FC_SANS_MANAGED_SAN_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<SanResponse>>() {}.getType());
    }

    @Test
    public void shouldGetFcSanManagedSanByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, FC_SANS_MANAGED_SAN_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<SanResponse>>() {}.getType());
    }

    @Test
    public void shouldUpdateFcSanManagedSan() {
        SanRequest sanRequest = new SanRequest();

        client.update(ANY_RESOURCE_ID, sanRequest);

        String expectedUri = FcSanManagedSanClient.FC_SANS_MANAGED_SAN_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, sanRequest);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(SanResponse.class).getType());
    }

    @Test
    public void shouldGetFcSanManagedSanEndpoints() {
        client.getEndpoints(ANY_RESOURCE_ID);

        String expectedUri = FC_SANS_MANAGED_SAN_URI
                + "/" + ANY_RESOURCE_ID
                + FC_SANS_MANAGED_SAN_ENDPOINTS_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<EndpointResponse>>() {}.getType());
    }

    @Test
    public void shouldCreateFcSanManagedSanIssuesReport() {
        client.createIssuesReport(ANY_RESOURCE_ID, TaskTimeout.of(321));

        String expectedUri = FC_SANS_MANAGED_SAN_URI
                + "/" + ANY_RESOURCE_ID
                + FC_SANS_MANAGED_SAN_ISSUES_URI;
        Request expectedRequest = new Request(HttpMethod.POST, expectedUri);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldCreateFcSanManagedSanEndpointsCsv() {
        client.createEndpointsCsv(ANY_RESOURCE_ID);

        String expectedUri = FcSanManagedSanClient.FC_SANS_MANAGED_SAN_URI
                + "/" + ANY_RESOURCE_ID
                + FcSanManagedSanClient.FC_SANS_MANAGED_SAN_ENDPOINTS_URI;
        Request expectedRequest = new Request(HttpMethod.POST, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(EndpointsCsvFileResponse.class).getType());
    }

    @Test
    public void shouldGetFcSanManagedSanWwnAssociations() {
        client.getWwnAssociations(ANY_WWN);

        String expectedUri = FC_SANS_MANAGED_SAN_URI
                + FC_SANS_WWN_LOCATE_URI
                + ANY_WWN;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<List<LocateSanResponse>>() {}.getType());
    }

}
