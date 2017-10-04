/*
 * (C) Copyright 2017 Hewlett Packard Enterprise Development LP
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

import static com.hp.ov.sdk.rest.client.settings.LicenseClient.LICENSES_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.settings.License;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.BasicURIQuery;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class LicenseClientTest {

    private static final String LICENSE_ID = "random-UUID";

    private BaseClient baseClient = mock(BaseClient.class);
    private LicenseClient client = Reflection.newProxy(LicenseClient.class,
            new ClientRequestHandler<>(baseClient, LicenseClient.class));

    @Test
    public void shoulAddLicense() {
        License license = new License();

        client.add(license);

        Request expectedRequest = new Request(HttpMethod.POST, LICENSES_URI, license);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(License.class).getType());
    }

    @SuppressWarnings("serial")
    @Test
    public void shouldGetAllLicenses() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class)))
                .willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, LICENSES_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<License>>() {}.getType());
    }

    @Test
    public void shouldGetLicenseById() {
        client.getById(LICENSE_ID);

        String expectedUri = LICENSES_URI + "/" + LICENSE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(License.class).getType());
    }

    @SuppressWarnings("serial")
    @Test
    public void shouldGetLicensesUsingFilter() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class)))
        .willReturn(new ResourceCollection<>());

        client.get(new BasicURIQuery());

        Request expectedRequest = new Request(HttpMethod.GET, LICENSES_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<License>>() {}.getType());
    }

    @Test
    public void shouldDeleteLicense() {
        client.delete(LICENSE_ID);

        String expectedUri = LICENSES_URI + "/" + LICENSE_ID;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

}
