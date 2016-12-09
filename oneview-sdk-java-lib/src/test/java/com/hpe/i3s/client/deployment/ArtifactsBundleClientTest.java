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

import static com.hpe.i3s.client.deployment.ArtifactsBundleClient.ARTIFACTS_BUNDLE_ARCHIVE_URI;
import static com.hpe.i3s.client.deployment.ArtifactsBundleClient.ARTIFACTS_BUNDLE_BACKUPS_URI;
import static com.hpe.i3s.client.deployment.ArtifactsBundleClient.ARTIFACTS_BUNDLE_DOWNLOAD_URI;
import static com.hpe.i3s.client.deployment.ArtifactsBundleClient.ARTIFACTS_BUNDLE_STOP_ARTIFACT_CREATE_URI;
import static com.hpe.i3s.client.deployment.ArtifactsBundleClient.ARTIFACTS_BUNDLE_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.lang.reflect.Type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.DownloadPath;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;
import com.hpe.i3s.dto.deployment.artifactsbundle.ArtifactsBundle;
import com.hpe.i3s.dto.deployment.artifactsbundle.CreateArtifactsBundle;
import com.hpe.i3s.dto.deployment.artifactsbundle.TaskUri;
import com.hpe.i3s.dto.deployment.artifactsbundle.UserBackupParams;

@RunWith(MockitoJUnitRunner.class)
public class ArtifactsBundleClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_FILE_PATH = "random-file-path";
    private static final String ANY_TASK_URI = "/rest/tasks/4545db75-1861-45a2-8468-f11ff07381rf";
    private static final String DEPLOYMENT_GROUP_RESOURCE_ID = "40ca28c0-d7cd-4312-be24-46f57e5737e4";

    private BaseClient baseClient = mock(BaseClient.class);
    private ArtifactsBundleClient client = Reflection.newProxy(ArtifactsBundleClient.class,
            new ClientRequestHandler<>(baseClient, ArtifactsBundleClient.class));

    @Test
    public void shouldGetArtifactsBundle() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = ARTIFACTS_BUNDLE_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(ArtifactsBundle.class).getType());
    }

    @Test
    public void shouldGetAllArtifactsBundles() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, ARTIFACTS_BUNDLE_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<ArtifactsBundle>>() {}.getType());
    }

    @Test
    public void shouldGetArtifactsBundleCollectionByName() {
        client.getByName(ANY_RESOURCE_NAME);

        Request expectedRequest = new Request(HttpMethod.GET, ARTIFACTS_BUNDLE_URI);
        expectedRequest.addQuery(UrlParameter.getFilterByNameParameter(ANY_RESOURCE_NAME));

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<ArtifactsBundle>>() {}.getType());
    }

    @Test
    public void shouldCreateArtifactsBundle() {
        CreateArtifactsBundle artifactsBundle = new CreateArtifactsBundle();

        client.create(artifactsBundle, TaskTimeout.of(123));

        Request expectedRequest = new Request(HttpMethod.POST, ARTIFACTS_BUNDLE_URI, artifactsBundle);
        expectedRequest.setTimeout(123);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldCreateArtifactsBundleFromFile() {
        File file = new File(ANY_FILE_PATH);
        client.create(file, DownloadPath.at(ANY_FILE_PATH));

        Request expectedRequest = new Request(HttpMethod.POST, ARTIFACTS_BUNDLE_URI, file);
        expectedRequest.setDownloadPath(ANY_FILE_PATH);
        expectedRequest.setContentType(ContentType.MULTIPART_FORM_DATA);
        expectedRequest.setForceReturnTask(true);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetBackupBundles() {
        client.getBackupBundles();

        String expectedUri = ARTIFACTS_BUNDLE_URI
                + ARTIFACTS_BUNDLE_BACKUPS_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<ArtifactsBundle>>() {}.getType());
    }

    @Test
    public void shouldCreateBackupBundle() {
        UserBackupParams userBackupParams = new UserBackupParams();

        client.createBackupBundle(userBackupParams, TaskTimeout.of(123));
        String expectedUri = ARTIFACTS_BUNDLE_URI + ARTIFACTS_BUNDLE_BACKUPS_URI;
        Request expectedRequest = new Request(HttpMethod.POST, expectedUri, userBackupParams);
        expectedRequest.setTimeout(123);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldCreateBackupArchiveBundle() {
        File file = new File(ANY_FILE_PATH);

        String deploymentGrpUri = DeploymentGroupClient.DEPLOYMENT_GROUP_URI + "/" + DEPLOYMENT_GROUP_RESOURCE_ID;
        UrlParameter query = new UrlParameter("deploymentGrpUri", deploymentGrpUri);
        client.createBackupArchiveBundle(file, deploymentGrpUri, DownloadPath.at(ANY_FILE_PATH));

        String expectedUri = ARTIFACTS_BUNDLE_URI
                + ARTIFACTS_BUNDLE_BACKUPS_URI
                + ARTIFACTS_BUNDLE_ARCHIVE_URI;
        
        Request expectedRequest = new Request(HttpMethod.POST, expectedUri, file);
        expectedRequest.addQuery(query);
        expectedRequest.setDownloadPath(ANY_FILE_PATH);
        expectedRequest.setContentType(ContentType.MULTIPART_FORM_DATA);
        expectedRequest.setForceReturnTask(true);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetBackupArchiveBundle() {
        client.downloadBackupArchiveBundle(ANY_RESOURCE_ID);

        String expectedUri = ARTIFACTS_BUNDLE_URI
                + ARTIFACTS_BUNDLE_BACKUPS_URI
                + ARTIFACTS_BUNDLE_ARCHIVE_URI
                + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldGetBackupBundle() {
        client.getBackupBundle(ANY_RESOURCE_ID);

        String expectedUri = ARTIFACTS_BUNDLE_URI
                + ARTIFACTS_BUNDLE_BACKUPS_URI
                + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(ArtifactsBundle.class).getType());
    }

    @Test
    public void shouldExtractBackupBundle() {
        UserBackupParams userBackupParams = new UserBackupParams();
        client.extractBackupBundle(ANY_RESOURCE_ID, userBackupParams, TaskTimeout.of(123));

        String expectedUri = ARTIFACTS_BUNDLE_URI
                + ARTIFACTS_BUNDLE_BACKUPS_URI
                + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, userBackupParams);
        expectedRequest.setTimeout(123);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldDownloadBundle() {
        client.downloadBundle(ANY_RESOURCE_ID);

        String expectedUri = ARTIFACTS_BUNDLE_URI
                + ARTIFACTS_BUNDLE_DOWNLOAD_URI
                + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

    @Test
    public void shouldExtractBundle() {
        client.extractBundle(ANY_RESOURCE_ID, TaskTimeout.of(123));

        String expectedUri = ARTIFACTS_BUNDLE_URI
                + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, "");
        expectedRequest.setContentType(ContentType.TEXT_PLAIN);
        expectedRequest.setTimeout(123);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldStopBundleCreation() {
        TaskUri taskUri = new TaskUri();

        taskUri.setTaskUri(ANY_TASK_URI);

        client.stopBundleCreation(ANY_RESOURCE_ID, taskUri);

        String expectedUri = ARTIFACTS_BUNDLE_URI
                + "/" + ANY_RESOURCE_ID
                + ARTIFACTS_BUNDLE_STOP_ARTIFACT_CREATE_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, taskUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(String.class).getType());
    }

}
