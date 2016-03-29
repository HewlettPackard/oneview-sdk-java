/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.any;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ExtraStorageVolume;
import com.hp.ov.sdk.dto.ExtraStorageVolumeCollection;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.StorageVolumeAttachment;
import com.hp.ov.sdk.dto.StorageVolumeAttachmentCollection;
import com.hp.ov.sdk.dto.StorageVolumeAttachmentPath;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class StorageVolumeAttachmentsClientImplTest {

    private static final String ANY_ATTACHMENT_ID = "random-UUID";
    private static final String ANY_PATH_ID = "random-UUID";

    @Mock
    private HttpRestClient restClient;
    @Mock
    private ResourceAdaptor adaptor;
    @Mock
    private TaskAdaptor taskAdaptor;
    @Mock
    private TaskMonitorManager taskMonitor;
    @InjectMocks
    private StorageVolumeAttachmentsClientImpl storageClient;

    @Test
    public void shouldReturnStorageVolumeAttachmentClient() {
        assertThat(StorageVolumeAttachmentsClientImpl.getClient(), is(notNullValue()));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetStorageVolumeAttachmentWithoutParams() {
        this.storageClient.getStorageVolumeAttachment(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetStorageVolumeAttachment() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getStorageVolumeAttachment(new RestParams(), null);
    }

    @Test
    public void shouldGetStorageVolumeAttachment() {
        String storageVolumeAttachmentValue = "{\"type\":\"StorageVolumeAttachment\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storageVolumeAttachmentValue);
        given(adaptor.buildResourceObject(anyString(), eq(StorageVolumeAttachment.class)))
                .willReturn(new StorageVolumeAttachment());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI, ANY_ATTACHMENT_ID));

        this.storageClient.getStorageVolumeAttachment(new RestParams(), ANY_ATTACHMENT_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(storageVolumeAttachmentValue),
                eq(StorageVolumeAttachment.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllStorageVolumeAttachmentsWithoutParams() {
        this.storageClient.getAllStorageVolumeAttachments(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllStorageVolumeAttachments() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getAllStorageVolumeAttachments(new RestParams());
    }

    @Test
    public void shouldGetAllStorageVolumeAttachments() {
        String storageVolumeAttachmentsValue = "{\"type\":\"StorageVolumeAttachmentList\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storageVolumeAttachmentsValue);
        given(adaptor.buildResourceObject(anyString(), eq(StorageVolumeAttachmentCollection.class)))
                .willReturn(new StorageVolumeAttachmentCollection());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI));

        this.storageClient.getAllStorageVolumeAttachments(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(storageVolumeAttachmentsValue),
                eq(StorageVolumeAttachmentCollection.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetStorageVolumeAttachmentPathWithoutParams() {
        this.storageClient.getStorageVolumeAttachmentPath(null, ANY_ATTACHMENT_ID, ANY_PATH_ID);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetStorageVolumeAttachmentPath() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getStorageVolumeAttachmentPath(new RestParams(),
                ANY_ATTACHMENT_ID, ANY_PATH_ID);
    }

    @Test
    public void shouldGetStorageVolumeAttachmentPath() {
        String storageVolumeAttachmentPathValue = "{\"type\":\"StorageVolumeAttachmentPath\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storageVolumeAttachmentPathValue);
        given(adaptor.buildResourceObject(anyString(), eq(StorageVolumeAttachmentPath.class)))
                .willReturn(new StorageVolumeAttachmentPath());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI, ANY_ATTACHMENT_ID,
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_PATH_URI, ANY_PATH_ID));

        this.storageClient.getStorageVolumeAttachmentPath(new RestParams(), ANY_ATTACHMENT_ID, ANY_PATH_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(storageVolumeAttachmentPathValue),
                eq(StorageVolumeAttachmentPath.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllStorageVolumeAttachmentPathsWithoutParams() {
        this.storageClient.getAllStorageVolumeAttachmentPaths(null, ANY_ATTACHMENT_ID);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllStorageVolumeAttachmentPaths() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getAllStorageVolumeAttachmentPaths(new RestParams(), ANY_ATTACHMENT_ID);
    }

    @Test
    public void shouldGetAllStorageVolumeAttachmentPaths() {
        String storageVolumeAttachmentPathsValue = "[{\"type\":\"StorageVolumeAttachmentPath\"}]";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storageVolumeAttachmentPathsValue);
        given(adaptor.buildListOfResourceObject(anyString(), any(Type.class))).willReturn(new ArrayList<>());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_URI, ANY_ATTACHMENT_ID,
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_PATH_URI));

        this.storageClient.getAllStorageVolumeAttachmentPaths(new RestParams(), ANY_ATTACHMENT_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildListOfResourceObject(eq(storageVolumeAttachmentPathsValue), any(Type.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetExtraUnmanagedStorageVolumeAttachmentsWithoutParams() {
        this.storageClient.getExtraUnmanagedStorageVolumeAttachments(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetExtraUnmanagedStorageVolumeAttachments() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getExtraUnmanagedStorageVolumeAttachments(new RestParams());
    }

    @Test
    public void shouldGetExtraUnmanagedStorageVolumeAttachments() {
        String extraStorageVolumeAttachmentsValue = "[{\"type\":\"ExtraAccessList\"}]";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(extraStorageVolumeAttachmentsValue);
        given(adaptor.buildResourceObject(anyString(), eq(ExtraStorageVolumeCollection.class)))
                .willReturn(new ExtraStorageVolumeCollection());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestQueryUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_REPAIR_URI, StorageVolumeAttachmentsClient.REPAIR_FILTER));

        this.storageClient.getExtraUnmanagedStorageVolumeAttachments(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(adaptor).should().buildResourceObject(eq(extraStorageVolumeAttachmentsValue),
                eq(ExtraStorageVolumeCollection.class));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRepairExtraUnmanagedStorageVolumeAttachmentWithoutParams() {
        this.storageClient.repairExtraUnmanagedStorageVolumeAttachment(null,
                new ExtraStorageVolumeRepair(), false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForRepairExtraUnmanagedStorageVolumeAttachment() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.repairExtraUnmanagedStorageVolumeAttachment(new RestParams(),
                new ExtraStorageVolumeRepair(), false);
    }

    @Test
    public void shouldSynchronousRepairExtraUnmanagedStorageVolumeAttachment() {
        String taskAsJson = "{\"type\" : \"taskResource\"}";
        JSONObject jsonObject = new JSONObject();

        given(adaptor.buildJsonRequest(any(ExtraStorageVolume.class), any(Double.class))).willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(taskAsJson);
        given(taskAdaptor.buildDto(any(String.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_ATTACHMENT_REPAIR_URI));

        this.storageClient.repairExtraUnmanagedStorageVolumeAttachment(new RestParams(),
                new ExtraStorageVolumeRepair(), false);

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));
        then(taskAdaptor).should().buildDto(taskAsJson);
    }
}