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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.AddStorageVolumeV2;
import com.hp.ov.sdk.dto.AttachableStorageVolumeCollection;
import com.hp.ov.sdk.dto.ExtraStorageVolumeCollection;
import com.hp.ov.sdk.dto.ExtraStorageVolumeRepair;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.StorageVolumeCollection;
import com.hp.ov.sdk.dto.StorageVolumeSnapshot;
import com.hp.ov.sdk.dto.StorageVolumeSnapshotCollection;
import com.hp.ov.sdk.dto.StorageVolumeV2;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class StorageVolumeClientImplTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";

    @Mock
    private HttpRestClient restClient;
    @Mock
    private ResourceAdaptor resourceAdaptor;
    @Mock
    private TaskAdaptor taskAdaptor;
    @Mock
    private TaskMonitorManager taskMonitor;
    @InjectMocks
    private StorageVolumeClientImpl storageClient;

    @Test
    public void shouldReturnStorageVolumeClient() {
        assertThat(StorageVolumeClientImpl.getClient(), is(notNullValue()));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetStorageVolumeWithoutParams() {
        this.storageClient.getStorageVolume(null, null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetStorageVolume() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getStorageVolume(new RestParams(), null);
    }

    @Test
    public void shouldGetStorageVolume() {
        String storageVolumeValue = "{\"type\":\"StorageVolumeV3\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storageVolumeValue);
        given(resourceAdaptor.buildResourceObject(anyString(), eq(StorageVolumeV2.class)))
                .willReturn(new StorageVolumeV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_URI, ANY_RESOURCE_ID));

        this.storageClient.getStorageVolume(new RestParams(), ANY_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(resourceAdaptor).should().buildResourceObject(storageVolumeValue, StorageVolumeV2.class);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllStorageVolumesWithoutParams() {
        this.storageClient.getAllStorageVolumes(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllStorageVolumes() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getAllStorageVolumes(new RestParams());
    }

    @Test
    public void shouldGetAllStorageVolumes() {
        String storageVolumeCollectionValue = "{\"type\":\"StorageVolumeListV3\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storageVolumeCollectionValue);
        given(resourceAdaptor.buildResourceObject(anyString(), eq(StorageVolumeCollection.class)))
                .willReturn(new StorageVolumeCollection());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_URI));

        this.storageClient.getAllStorageVolumes(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(resourceAdaptor).should().buildResourceObject(storageVolumeCollectionValue, StorageVolumeCollection.class);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetStorageVolumeByNameWithoutParams() {
        String anyStorageVolumeName = "random-NAME";

        this.storageClient.getStorageVolumeByName(null, anyStorageVolumeName);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForStorageVolumeByName() {
        String anyStorageVolumeName = "random-NAME";

        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getStorageVolumeByName(new RestParams(), anyStorageVolumeName);
    }

    @Test(expected = SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenEmptyStorageVolumeCollectionIsReturnedForTheGivenName() {
        String anyStorageVolumeName = "random-NAME";
        String storageVolumeCollectionValue = "{\"type\":\"StorageVolumeListV3\"," +
                "\"members\": [{\"type\":\"AddStorageVolumeV3\"}]}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storageVolumeCollectionValue);
        given(resourceAdaptor.buildResourceObject(anyString(), eq(StorageVolumeCollection.class)))
                .willReturn(new StorageVolumeCollection());

        this.storageClient.getStorageVolumeByName(new RestParams(), anyStorageVolumeName);
    }

    @Test
    public void shouldGetStorageVolumeByName() {
        String anyStorageVolumeName = "random-NAME";
        String storageVolumeCollectionValue = "{\"type\":\"StorageVolumeListV3\"," +
                "\"members\": [{\"type\":\"StorageVolumeV3\"}]}";

        StorageVolumeCollection storageVolumeCollection = new StorageVolumeCollection();
        StorageVolumeV2 storageVolume = new StorageVolumeV2();

        storageVolumeCollection.setMembers(Lists.newArrayList(storageVolume));
        storageVolumeCollection.setCount(1);

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storageVolumeCollectionValue);
        given(resourceAdaptor.buildResourceObject(anyString(), eq(StorageVolumeCollection.class)))
                .willReturn(storageVolumeCollection);

        RestParams expectedRestParams = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + anyStorageVolumeName + "'");
        expectedRestParams.setQuery(query);

        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_URI));

        StorageVolumeV2 response = this.storageClient.getStorageVolumeByName(new RestParams(),
                anyStorageVolumeName);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(resourceAdaptor).should().buildResourceObject(storageVolumeCollectionValue, StorageVolumeCollection.class);

        assertThat(response, is(sameInstance(storageVolume)));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateStorageVolumeWithoutParams() {
        this.storageClient.createStorageVolume(null, new AddStorageVolumeV2(), false, false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateStorageVolumeWithoutRequest() {
        this.storageClient.createStorageVolume(new RestParams(), null, false, false);
    }

    @Test
    public void shouldSynchronouslyCreateStorageVolume() {
        String taskAsJson = "{\"type\" : \"taskResource\"}";
        JSONObject jsonObject = new JSONObject();

        given(resourceAdaptor.buildJsonRequest(any(AddStorageVolumeV2.class), any(Double.class)))
                .willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(taskAsJson);
        given(taskAdaptor.buildDto(any(String.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_URI));

        this.storageClient.createStorageVolume(new RestParams(), new AddStorageVolumeV2(), false, false);

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));
        then(taskAdaptor).should().buildDto(taskAsJson);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateStorageVolumeWithoutParams() {
        this.storageClient.updateStorageVolume(null, ANY_RESOURCE_ID, new StorageVolumeV2(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToUpdateStorageVolumeWithoutRequest() {
        this.storageClient.updateStorageVolume(new RestParams(), ANY_RESOURCE_ID, null, false);
    }

    @Test
    public void shouldUpdateStorageVolume() {
        JSONObject jsonObject = new JSONObject();
        String nonEmptyResponse = "random-RESPONSE";

        given(resourceAdaptor.buildJsonRequest(any(StorageVolumeV2.class), any(Double.class)))
                .willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(nonEmptyResponse);

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.PUT);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_URI, ANY_RESOURCE_ID));

        String response = this.storageClient.updateStorageVolume(new RestParams(),
                ANY_RESOURCE_ID, new StorageVolumeV2(), false);

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));

        assertThat(response, is(equalTo("Updated")));
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToDeleteStorageVolumeWithoutParams() {
        this.storageClient.deleteStorageVolume(null, ANY_RESOURCE_ID, false);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForDeleteStorageVolume() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.deleteStorageVolume(new RestParams(), ANY_RESOURCE_ID, false);
    }

    @Test
    public void shouldSynchronouslyDeleteStorageVolume() {
        String taskAsJson = "{\"type\" : \"taskResource\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(taskAsJson);
        given(taskAdaptor.buildDto(any(String.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_URI, ANY_RESOURCE_ID));

        this.storageClient.deleteStorageVolume(new RestParams(), ANY_RESOURCE_ID, false);

        then(restClient).should().sendRequest(expectedRestParams);
        then(taskAdaptor).should().buildDto(taskAsJson);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAttachableVolumesWithoutParams() {
        this.storageClient.getAttachableVolumes(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAttachableVolumes() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getAttachableVolumes(new RestParams());
    }

    @Test
    public void shouldGetAttachableVolumes() {
        String attachableVolumesCollectionValue = "{\"type\":\"AttachableStorageVolumeList\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(attachableVolumesCollectionValue);
        given(resourceAdaptor.buildResourceObject(anyString(), eq(AttachableStorageVolumeCollection.class)))
                .willReturn(new AttachableStorageVolumeCollection());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_ATTACHABLE_URI));

        this.storageClient.getAttachableVolumes(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(resourceAdaptor).should().buildResourceObject(attachableVolumesCollectionValue,
                AttachableStorageVolumeCollection.class);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetStorageVolumeSnapshotWithoutParams() {
        String anyStorageVolumeId = "random-UUID";
        String anyStorageVolumeSnapshotId = "random-UUID";

        this.storageClient.getStorageVolumeSnapshot(null, anyStorageVolumeId,
                anyStorageVolumeSnapshotId);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetStorageVolumeSnapshot() {
        String anyStorageVolumeId = "random-UUID";
        String anyStorageVolumeSnapshotId = "random-UUID";

        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getStorageVolumeSnapshot(new RestParams(), anyStorageVolumeId,
                anyStorageVolumeSnapshotId);
    }

    @Test
    public void shouldGetStorageVolumeSnapshot() {
        String anyStorageVolumeId = "random-UUID";
        String anyStorageVolumeSnapshotId = "random-UUID";
        String storageVolumeSnapshotValue = "{\"type\":\"Snapshot\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storageVolumeSnapshotValue);
        given(resourceAdaptor.buildResourceObject(anyString(), eq(StorageVolumeSnapshot.class)))
                .willReturn(new StorageVolumeSnapshot());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_URI, anyStorageVolumeId,
                ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI, anyStorageVolumeSnapshotId));

        this.storageClient.getStorageVolumeSnapshot(new RestParams(), anyStorageVolumeId, anyStorageVolumeSnapshotId);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(resourceAdaptor).should().buildResourceObject(storageVolumeSnapshotValue, StorageVolumeSnapshot.class);
    }


    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetAllStorageVolumeSnapshotsWithoutParams() {
        this.storageClient.getAllStorageVolumeSnapshots(null, ANY_RESOURCE_ID);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetAllStorageVolumeSnapshots() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getAllStorageVolumeSnapshots(new RestParams(), ANY_RESOURCE_ID);
    }

    @Test
    public void shouldGetAllStorageVolumesSnapshots() {
        String storageVolumeSnapshotsCollectionValue = "{\"type\":\"SnapshotList\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(storageVolumeSnapshotsCollectionValue);
        given(resourceAdaptor.buildResourceObject(anyString(), eq(StorageVolumeSnapshotCollection.class)))
                .willReturn(new StorageVolumeSnapshotCollection());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_URI, ANY_RESOURCE_ID,
                ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI));

        this.storageClient.getAllStorageVolumeSnapshots(new RestParams(), ANY_RESOURCE_ID);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(resourceAdaptor).should().buildResourceObject(storageVolumeSnapshotsCollectionValue,
                StorageVolumeSnapshotCollection.class);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateStorageVolumeSnapshotWithoutParams() {
        this.storageClient.createStorageVolumeSnapshot(null, ANY_RESOURCE_ID,
                new StorageVolumeSnapshot(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateStorageVolumeSnapshotWithoutRequest() {
        this.storageClient.createStorageVolumeSnapshot(new RestParams(), ANY_RESOURCE_ID, null, false);
    }

    @Test
    public void shouldSynchronouslyCreateStorageVolumeSnapshot() {
        String taskAsJson = "{\"type\" : \"taskResource\"}";
        JSONObject jsonObject = new JSONObject();

        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(taskAsJson);
        given(resourceAdaptor.buildJsonRequest(any(StorageVolumeSnapshot.class), any(Double.class)))
                .willReturn(jsonObject);
        given(taskAdaptor.buildDto(any(String.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_URI, ANY_RESOURCE_ID,
                ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI));

        this.storageClient.createStorageVolumeSnapshot(new RestParams(), ANY_RESOURCE_ID,
                new StorageVolumeSnapshot(), false);

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));
        then(taskAdaptor).should().buildDto(taskAsJson);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToDeleteStorageVolumeSnapshotWithoutParams() {
        this.storageClient.deleteStorageVolumeSnapshot(null, ANY_RESOURCE_ID,
                ANY_RESOURCE_ID, false);
    }

    @Test
    public void shouldSynchronouslyDeleteStorageVolumeSnapshot() {
        String anyStorageVolumeId = "random-UUID";
        String anyStorageVolumeSnapshotId = "random-UUID";
        String taskAsJson = "{\"type\" : \"taskResource\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(taskAsJson);
        given(taskAdaptor.buildDto(any(String.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.DELETE);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_URI, anyStorageVolumeId,
                ResourceUris.STORAGE_VOLUME_SNAPSHOTS_URI, anyStorageVolumeSnapshotId));

        this.storageClient.deleteStorageVolumeSnapshot(new RestParams(), anyStorageVolumeId,
                anyStorageVolumeSnapshotId, false);

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(taskAdaptor).should().buildDto(taskAsJson);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToGetExtraManagedStorageVolumePathsWithoutParams() {
        this.storageClient.getExtraManagedStorageVolumePaths(null);
    }

    @Test(expected = SDKNoResponseException.class)
    public void shouldThrowExceptionWhenServerReturnsNoAnswerForGetExtraManagedStorageVolumePaths() {
        given(restClient.sendRequest(any(RestParams.class))).willReturn("");

        this.storageClient.getExtraManagedStorageVolumePaths(new RestParams());
    }

    @Test
    public void shouldGetExtraManagedStorageVolumePaths() {
        String extraManagedListValue = "{\"type\":\"ExtraAccessList\"}";

        given(restClient.sendRequest(any(RestParams.class))).willReturn(extraManagedListValue);
        given(resourceAdaptor.buildResourceObject(anyString(), eq(ExtraStorageVolumeCollection.class)))
                .willReturn(new ExtraStorageVolumeCollection());

        RestParams expectedRestParams = new RestParams();

        Map<String, String> query = new HashMap<String, String>();
        query.put("alertFixType", "ExtraManagedStorageVolumePaths");
        expectedRestParams.setQuery(query);

        expectedRestParams.setType(HttpMethodType.GET);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_REPAIR_URI));

        this.storageClient.getExtraManagedStorageVolumePaths(new RestParams());

        then(restClient).should().sendRequest(eq(expectedRestParams));
        then(resourceAdaptor).should().buildResourceObject(extraManagedListValue,
                ExtraStorageVolumeCollection.class);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRemoveExtraManagedStorageVolumePathWithoutParams() {
        this.storageClient.repairExtraManagedStorageVolumePath(null, new ExtraStorageVolumeRepair(), false);
    }

    @Test(expected = SDKInvalidArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRemoveExtraManagedStorageVolumePathWithoutRequest() {
        this.storageClient.repairExtraManagedStorageVolumePath(new RestParams(), null, false);
    }

    @Test
    public void shouldSynchronouslyRemoveExtraManagedStorageVolumePath() {
        String taskAsJson = "{\"type\" : \"taskResource\"}";
        JSONObject jsonObject = new JSONObject();

        given(resourceAdaptor.buildJsonRequest(any(ExtraStorageVolumeRepair.class), any(Double.class)))
                .willReturn(jsonObject);
        given(restClient.sendRequest(any(RestParams.class), any(JSONObject.class))).willReturn(taskAsJson);
        given(taskAdaptor.buildDto(any(String.class))).willReturn(new TaskResourceV2());
        given(taskMonitor.checkStatus(any(RestParams.class), any(String.class), any(Integer.class)))
                .willReturn(new TaskResourceV2());

        RestParams expectedRestParams = new RestParams();
        expectedRestParams.setType(HttpMethodType.POST);
        expectedRestParams.setUrl(UrlUtils.createRestUrl(expectedRestParams.getHostname(),
                ResourceUris.STORAGE_VOLUME_REPAIR_URI));

        this.storageClient.repairExtraManagedStorageVolumePath(new RestParams(), new ExtraStorageVolumeRepair(), false);

        then(restClient).should().sendRequest(eq(expectedRestParams), eq(jsonObject));
        then(taskAdaptor).should().buildDto(taskAsJson);
    }

    @Test
    public void shouldReturnEmptyStringForGetId() {
        //given
        StorageVolumeClient localStorageClient = spy(this.storageClient);
        StorageVolumeV2 storageVolume = new StorageVolumeV2();

        doReturn(storageVolume).when(localStorageClient).getStorageVolumeByName(
                any(RestParams.class), anyString());

        //when
        String resourceId = localStorageClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, isEmptyString());
    }

    @Test
    public void shouldReturnResourceIdForGetId() {
        String storageVolumeId = "AF5FBE7D-271F-4F4F-B4B4-D0017970E590";
        String storageVolumeUri = "/rest/storage-volumes/" + storageVolumeId;

        //given
        StorageVolumeClient localStorageClient = spy(this.storageClient);
        StorageVolumeV2 storageVolume = new StorageVolumeV2();

        storageVolume.setUri(storageVolumeUri);

        doReturn(storageVolume).when(localStorageClient).getStorageVolumeByName(
                any(RestParams.class), anyString());

        //when
        String resourceId = localStorageClient.getId(new RestParams(), "random-NAME");

        //then
        assertThat(resourceId, is(equalTo(storageVolumeId)));
    }
}