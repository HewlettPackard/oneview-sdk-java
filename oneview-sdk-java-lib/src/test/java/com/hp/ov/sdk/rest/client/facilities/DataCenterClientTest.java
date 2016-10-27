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

package com.hp.ov.sdk.rest.client.facilities;

import static com.hp.ov.sdk.rest.client.facilities.DataCenterClient.DATA_CENTER_URI;
import static com.hp.ov.sdk.rest.client.facilities.DataCenterClient.DATA_CENTER_VISUAL_CONTENT_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.facilities.datacenter.DataCenter;
import com.hp.ov.sdk.dto.facilities.datacenter.VisualContent;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class DataCenterClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private DataCenterClient dataCenterClient = Reflection.newProxy(DataCenterClient.class,
            new ClientRequestHandler<>(baseClient, DataCenterClient.class));

    @Test
    public void shouldGetDataCenter() {
        dataCenterClient.getById(ANY_RESOURCE_ID);

        String expectedUri = DATA_CENTER_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(DataCenter.class).getType());
    }

    @Test
    public void shouldGetAllDataCenter() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        dataCenterClient.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, DATA_CENTER_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<DataCenter>>() {}.getType());
    }

    @Test
    public void shouldGetDataCenterCollectionByName() {
        dataCenterClient.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, DATA_CENTER_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<DataCenter>>() {}.getType());
    }

    @Test
    public void shouldAddDataCenter() {
        DataCenter dataCenter = new DataCenter();

        dataCenterClient.add(dataCenter);

        Request expectedRequest = new Request(HttpMethod.POST, DATA_CENTER_URI);
        expectedRequest.setEntity(dataCenter);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(DataCenter.class).getType());
    }

    @Test
    public void shouldUpdateDataCenter() {
        DataCenter dataCenter = new DataCenter();

        dataCenterClient.update(ANY_RESOURCE_ID, dataCenter);

        String expectedUri = DATA_CENTER_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(dataCenter);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(DataCenter.class).getType());
    }

    @Test
    public void shouldRemoveDataCenter() {
        dataCenterClient.remove(ANY_RESOURCE_ID);

        String expectedUri = DATA_CENTER_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldRemoveDataCenterByFilter() {
        String filter = "'name' = '" + ANY_RESOURCE_NAME + "'";
        dataCenterClient.removeByFilter(filter, TaskTimeout.of(321));

        String expectedUri = DATA_CENTER_URI;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        expectedRequest.addQuery(new UrlParameter("filter", filter));

        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetDataCenterVisualContent() {
        dataCenterClient.getVisualContent(ANY_RESOURCE_ID);

        String expectedUri = DATA_CENTER_URI
                + "/" + ANY_RESOURCE_ID
                + DATA_CENTER_VISUAL_CONTENT_URI;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<List<VisualContent>>() {}.getType());
    }

}
