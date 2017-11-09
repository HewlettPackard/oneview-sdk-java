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

package com.hp.ov.sdk.rest.client.settings;

import static com.hp.ov.sdk.rest.client.settings.FirmwareBundleClient.FIRMWARE_BUNDLE_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;

@RunWith(MockitoJUnitRunner.class)
public class FirmwareBundleClientTest {

    private BaseClient baseClient = mock(BaseClient.class);
    private FirmwareBundleClient client = Reflection.newProxy(FirmwareBundleClient.class,
            new ClientRequestHandler<>(baseClient, FirmwareBundleClient.class));
    @Test
    public void shouldAddFirmwareBundle() {
        File firmwareBundleFile = mock(File.class);

        given(firmwareBundleFile.exists()).willReturn(Boolean.TRUE);
        given(firmwareBundleFile.isFile()).willReturn(Boolean.TRUE);

        client.upload(firmwareBundleFile, TaskTimeout.of(12345));

        Request expectedRequest = new Request(HttpMethod.POST,
                FIRMWARE_BUNDLE_URI, firmwareBundleFile);

        expectedRequest.setContentType(ContentType.MULTIPART_FORM_DATA);
        expectedRequest.setTimeout(12345);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenFirmwareBundleDoesNotExists() {
        File firmwareBundleFile = mock(File.class);

        given(firmwareBundleFile.exists()).willReturn(Boolean.FALSE);
        given(firmwareBundleFile.isFile()).willReturn(Boolean.TRUE);

        client.upload(firmwareBundleFile);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenFirmwareBundleIsNotAFile() {
        File firmwareBundleFile = mock(File.class);

        given(firmwareBundleFile.exists()).willReturn(Boolean.TRUE);
        given(firmwareBundleFile.isFile()).willReturn(Boolean.FALSE);

        client.upload(firmwareBundleFile);
    }

}
