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

package com.hp.ov.sdk.rest.client;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class FirmwareBundleClientImplTest {

    private static final String ANY_FIRMWARE_BUNDLE_FILE = "random-FILE";

    @Mock
    private HttpRestClient restClient;
    @Mock
    private TaskAdaptor adaptor;
    @Mock
    private TaskMonitorManager taskMonitor;
    @InjectMocks
    private FirmwareBundleClientImpl fwBundleClient;

    @Test
    public void shouldReturnFirmwareBundleClient() {
        assertThat(FirmwareBundleClientImpl.getClient(), is(notNullValue()));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToAddFirmwareBundleWithoutParams() {
        this.fwBundleClient.addFirmwareBundle(null, new File(ANY_FIRMWARE_BUNDLE_FILE), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToAddFirmwareBundleWithoutFirmwareBundle() {
        this.fwBundleClient.addFirmwareBundle(new RestParams(), null, false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenFirmwareBundleDoesNotExist() {
        File firmwareBundle = mock(File.class);

        given(firmwareBundle.exists()).willReturn(Boolean.FALSE);

        this.fwBundleClient.addFirmwareBundle(new RestParams(), firmwareBundle, false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenFirmwareBundleIsDirectory() {
        File firmwareBundle = mock(File.class);

        given(firmwareBundle.exists()).willReturn(Boolean.TRUE);
        given(firmwareBundle.isFile()).willReturn(Boolean.FALSE);

        this.fwBundleClient.addFirmwareBundle(new RestParams(), firmwareBundle, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForAddFirmwareBundle() {
        File firmwareBundle = mock(File.class);

        given(firmwareBundle.exists()).willReturn(Boolean.TRUE);
        given(firmwareBundle.isFile()).willReturn(Boolean.TRUE);

        given(restClient.sendMultipartPostRequest(any(RestParams.class), any(File.class))).willReturn("");

        this.fwBundleClient.addFirmwareBundle(new RestParams(), firmwareBundle, false);
    }

    @Test
    public void shouldAddFirmwareBundle() {
        String taskResourceValue = "{\"type\":\"TaskResourceV2\"}";
        File firmwareBundle = mock(File.class);

        given(firmwareBundle.exists()).willReturn(Boolean.TRUE);
        given(firmwareBundle.isFile()).willReturn(Boolean.TRUE);
        given(restClient.sendMultipartPostRequest(any(RestParams.class), any(File.class))).willReturn(taskResourceValue);
        given(adaptor.buildDto(anyString())).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), anyString(), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(
                UrlUtils.createRestUrl(expectedRestParams.getHostname(), ResourceUris.FIRMWARE_BUNDLE_URI));

        this.fwBundleClient.addFirmwareBundle(new RestParams(), firmwareBundle, false);

        then(restClient).should().sendMultipartPostRequest(eq(expectedRestParams), any(File.class));
    }

}
