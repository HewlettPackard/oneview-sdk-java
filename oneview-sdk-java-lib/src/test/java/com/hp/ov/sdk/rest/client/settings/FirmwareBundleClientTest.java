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

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.then;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.client.Request;

@RunWith(MockitoJUnitRunner.class)
public class FirmwareBundleClientTest {

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private FirmwareBundleClient client;

    @Test
    public void shouldAddFirmwareBundle() {
        File firmwareBundleFile = mock(File.class);

        given(firmwareBundleFile.exists()).willReturn(Boolean.TRUE);
        given(firmwareBundleFile.isFile()).willReturn(Boolean.TRUE);

        client.add(firmwareBundleFile, false);

        Request expectedRequest = new Request(HttpMethodType.POST,
                ResourceUris.FIRMWARE_BUNDLE_URI, firmwareBundleFile);

        expectedRequest.setContentType(ContentType.MULTIPART_FORM_DATA);
        expectedRequest.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenFirmwareBundleDoesNotExists() {
        File firmwareBundleFile = mock(File.class);

        given(firmwareBundleFile.exists()).willReturn(Boolean.FALSE);
        given(firmwareBundleFile.isFile()).willReturn(Boolean.TRUE);

        client.add(firmwareBundleFile, false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenFirmwareBundleIsNotAFile() {
        File firmwareBundleFile = mock(File.class);

        given(firmwareBundleFile.exists()).willReturn(Boolean.TRUE);
        given(firmwareBundleFile.isFile()).willReturn(Boolean.FALSE);

        client.add(firmwareBundleFile, false);
    }

}
