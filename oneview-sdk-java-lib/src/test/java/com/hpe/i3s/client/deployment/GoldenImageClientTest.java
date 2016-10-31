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

package com.hpe.i3s.client.deployment;

import static com.hpe.i3s.client.deployment.GoldenImageClient.GOLDEN_IMAGE_ARCHIVED_URI;
import static com.hpe.i3s.client.deployment.GoldenImageClient.GOLDEN_IMAGE_DOWNLOAD_URI;
import static com.hpe.i3s.client.deployment.GoldenImageClient.GOLDEN_IMAGE_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;

import org.junit.Test;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;
import com.hpe.i3s.dto.deployment.goldenimage.GoldenImage;
import com.hpe.i3s.dto.deployment.goldenimage.GoldenImageFile;

public class GoldenImageClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";

    private BaseClient baseClient = mock(BaseClient.class);
    private GoldenImageClient client = Reflection.newProxy(GoldenImageClient.class,
            new ClientRequestHandler<>(baseClient, GoldenImageClient.class));

    @Test
    public void shouldGetGoldenImageById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = GOLDEN_IMAGE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(GoldenImage.class).getType());
    }

    @Test
    public void shouldGetAllGoldenImages() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, GOLDEN_IMAGE_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<GoldenImage>>() {}.getType());
    }

    @Test
    public void shouldGetGoldenImageByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, GOLDEN_IMAGE_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<GoldenImage>>() {}.getType());
    }

    @Test
    public void shouldCreateGoldenImage() {
        GoldenImage goldenImage = new GoldenImage();

        client.create(goldenImage);

        Request expectedRequest = new Request(HttpMethod.POST, GOLDEN_IMAGE_URI);
        expectedRequest.setEntity(goldenImage);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldCreateGoldenImageFromFile() {
        GoldenImageFile goldenImageFile = new GoldenImageFile();

        client.create(goldenImageFile);

        Request expectedRequest = new Request(HttpMethod.POST, GOLDEN_IMAGE_URI);
        expectedRequest.setEntity(goldenImageFile);
        expectedRequest.setContentType(ContentType.MULTIPART_FORM_DATA);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldUpdateGoldenImage() {
        GoldenImage goldenImage = new GoldenImage();

        client.update(ANY_RESOURCE_ID, goldenImage);

        String expectedUri = GOLDEN_IMAGE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setEntity(goldenImage);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(GoldenImage.class).getType());
    }

    @Test
    public void shouldDeleteGoldenImage() {
        client.delete(ANY_RESOURCE_ID);

        String expectedUri = GOLDEN_IMAGE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetGoldenImageArchivedLogs() {
        client.getArchivedLogs(ANY_RESOURCE_ID);

        String expectedUri = GOLDEN_IMAGE_URI + GOLDEN_IMAGE_ARCHIVED_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldDownloadGoldenImage() {
        client.download(ANY_RESOURCE_ID);

        String expectedUri = GOLDEN_IMAGE_URI + GOLDEN_IMAGE_DOWNLOAD_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

}
