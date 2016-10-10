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

import static com.hp.ov.sdk.rest.client.storage.SasLogicalJbodAttachmentClient.SAS_LOGICAL_JBOD_ATTACHMENT_URI;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.storage.saslogicaljbod.SasLogicalJbodAttachment;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class SasLogicalJbodAttachmentClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private SasLogicalJbodAttachmentClient client = Reflection.newProxy(SasLogicalJbodAttachmentClient.class,
            new ClientRequestHandler<>(baseClient, SasLogicalJbodAttachmentClient.class));

    @Test
    public void shouldGetSasLogicalJbodAttachmentById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = SAS_LOGICAL_JBOD_ATTACHMENT_URI
                + "/" + ANY_RESOURCE_ID;

        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(SasLogicalJbodAttachment.class).getType());
    }

    @Test
    public void shouldGetAllSasLogicalJbodAttachments() {
        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, SAS_LOGICAL_JBOD_ATTACHMENT_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<SasLogicalJbodAttachment>>() {}.getType());
    }

    @Test
    public void shouldGetSasLogicalJbodAttachmentByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, SAS_LOGICAL_JBOD_ATTACHMENT_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<SasLogicalJbodAttachment>>() {}.getType());
    }

}
