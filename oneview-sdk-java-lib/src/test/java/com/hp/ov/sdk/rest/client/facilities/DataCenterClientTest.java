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

import static org.mockito.BDDMockito.then;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.facilities.datacenter.DataCenter;
import com.hp.ov.sdk.dto.facilities.datacenter.VisualContent;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class DataCenterClientTest {

    private static final String ANY_DATA_CENTER_RESOURCE_ID = "random-UUID";
    private static final String ANY_DATA_CENTER_RESOURCE_NAME = "random-Name";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private DataCenterClient dataCenterClient;

    @Test
    public void shouldGetDataCenter() {
        dataCenterClient.getById(ANY_DATA_CENTER_RESOURCE_ID);

        String expectedUri = ResourceUris.DATA_CENTER_URI + "/" + ANY_DATA_CENTER_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, DataCenter.class);
    }

    @Test
    public void shouldGetAllDataCenter() {
        dataCenterClient.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.DATA_CENTER_URI, DataCenter.class);
    }

    @Test
    public void shouldGetDataCenterCollectionByName() {
        dataCenterClient.getByName(ANY_DATA_CENTER_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.DATA_CENTER_URI,
                DataCenter.class, UrlParameter.getFilterByNameParameter(ANY_DATA_CENTER_RESOURCE_NAME));
    }

    @Test
    public void shouldAddDataCenter() {
        DataCenter dataCenter = new DataCenter();

        dataCenterClient.add(dataCenter);

        Request request = new Request(HttpMethod.POST, ResourceUris.DATA_CENTER_URI, dataCenter);

        then(baseClient).should().executeRequest(request, DataCenter.class);
    }

    @Test
    public void shouldUpdateDataCenter() {
        DataCenter dataCenter = new DataCenter();

        dataCenterClient.update(ANY_DATA_CENTER_RESOURCE_ID, dataCenter);

        String expectedUri = ResourceUris.DATA_CENTER_URI + "/" + ANY_DATA_CENTER_RESOURCE_ID;
        Request request = new Request(HttpMethod.PUT, expectedUri, dataCenter);

        then(baseClient).should().executeRequest(request, DataCenter.class);
    }

    @Test
    public void shouldRemoveDataCenter() {
        dataCenterClient.remove(ANY_DATA_CENTER_RESOURCE_ID);

        String expectedUri = ResourceUris.DATA_CENTER_URI + "/" + ANY_DATA_CENTER_RESOURCE_ID;
        Request request = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeRequest(request, String.class);
    }

    @Test
    public void shouldRemoveDataCenterByFilter() {
        dataCenterClient.removeByFilter(ANY_DATA_CENTER_RESOURCE_NAME, false);

        UrlParameter filter = new UrlParameter("filter", ANY_DATA_CENTER_RESOURCE_NAME);

        then(baseClient).should().deleteResource(ResourceUris.DATA_CENTER_URI, false, filter);
    }

    @Test
    public void shouldGetDataCenterVisualContent() {
        dataCenterClient.getVisualContent(ANY_DATA_CENTER_RESOURCE_ID);

        String expectedUri = ResourceUris.DATA_CENTER_URI + "/" + ANY_DATA_CENTER_RESOURCE_ID
                + "/" + ResourceUris.DATA_CENTER_VISUAL_CONTENT_URI;

        then(baseClient).should().getResourceList(expectedUri, VisualContent.class);
    }

}
