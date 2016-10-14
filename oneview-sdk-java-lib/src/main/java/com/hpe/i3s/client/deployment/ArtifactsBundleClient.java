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

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.rest.client.common.CreatableResource;
import com.hp.ov.sdk.rest.client.common.DeletableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.client.common.UpdatableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;
import com.hpe.i3s.dto.deployment.artifactsbundle.ArtifactsBundle;
import com.hpe.i3s.dto.deployment.artifactsbundle.UserBackupParams;

@Api(ArtifactsBundleClient.ARTIFACTS_BUNDLE_URI)
public interface ArtifactsBundleClient extends
        SearchableResource<ArtifactsBundle>,
        CreatableResource<ArtifactsBundle> ,
        UpdatableResource<ArtifactsBundle>,
        DeletableResource {

    String ARTIFACTS_BUNDLE_URI = "/rest/artifact-bundles";
    String ARTIFACTS_BUNDLE_BACKUPS_URI = "/backups";
    String ARTIFACTS_BUNDLE_ARCHIVE_URI = "/archive";
    String ARTIFACTS_BUNDLE_DOWNLOAD_URI = "/download";
    String ARTIFACTS_BUNDLE_STOP_ARTIFACT_CREATE_URI = "/stopArtifactCreate";

    /**
     * Creates an artifact bundle according to the provided <code>filePath</code> object.
     *
     * @param filePath String containing the fully qualified path to the artifacts bundle file.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(method = HttpMethod.POST)
    TaskResource create(String filePath, RequestOption... options);

    /**
     * Retrieves a paginated collection ({@link ResourceCollection}) containing the details for the
     * available backup bundles.
     *
     * @return {@link ResourceCollection} Collection containing the details for the
     * available resources.
     */
    @Endpoint(uri = ARTIFACTS_BUNDLE_BACKUPS_URI)
    ResourceCollection<ArtifactsBundle> getBackupBundles();

    /**
     * Creates a backup bundle with all the artifacts present on the appliance.
     *
     * @param userBackupParams Attributes of the backup.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = ARTIFACTS_BUNDLE_BACKUPS_URI, method = HttpMethod.POST)
    TaskResource createBackupBundle(@BodyParam UserBackupParams userBackupParams, RequestOption... options);

    /**
     * Upload a backup bundle from a local drive and extract all the artifacts present in the uploaded file.
     *
     * @param filePath String containing the fully qualified path to the artifacts bundle file.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = ARTIFACTS_BUNDLE_BACKUPS_URI + ARTIFACTS_BUNDLE_ARCHIVE_URI, method = HttpMethod.POST)
    TaskResource createBackupArchiveBundle(String filePath, RequestOption... options);

    /**
     * Retrieves the backup archive bundle identified by the given <code>resourceId</code>.
     *
     * @param resourceId Artifacts Bundle resource identifier.
     *
     * @return {@link ArtifactsBundle} object containing the result of this request.
     */
    @Endpoint(uri = ARTIFACTS_BUNDLE_BACKUPS_URI + ARTIFACTS_BUNDLE_ARCHIVE_URI + "/{resourceId}")
    ArtifactsBundle getBackupArchiveBundle(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves the backup bundle identified by the given <code>resourceId</code>.
     *
     * @param resourceId Artifacts Bundle resource identifier.
     *
     * @return {@link ArtifactsBundle} object containing the result of this request.
     */
    @Endpoint(uri = ARTIFACTS_BUNDLE_BACKUPS_URI + "/{resourceId}")
    ArtifactsBundle getBackupBundle(@PathParam("resourceId") String resourceId);

    /**
     * Extracts the existing backup bundle on the appliance and creates all the artifacts.
     *
     * @param resourceId resource identifier
     * @param userBackupParams Attributes of the backup.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = ARTIFACTS_BUNDLE_BACKUPS_URI + "/{resourceId}", method = HttpMethod.PUT)
    TaskResource extractBackupBundle(@PathParam("resourceId") String resourceId,
            @BodyParam UserBackupParams userBackupParams,
            RequestOption... options);

    /**
     * Downloads the content of the selected artifacts bundle to the admin's local drive.
     *
     * @param resourceId resource identifier
     *
     * @return {@link String} object containing the result of this request.
     */
    @Endpoint(uri = ARTIFACTS_BUNDLE_DOWNLOAD_URI + "/{resourceId}")
    String downloadBundle(@PathParam("resourceId") String resourceId);

    /**
     * Extracts the selected artifact bundle and creates the artifacts on the appliance.
     *
     * @param resourceId resource identifier
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    TaskResource extractBundle(@PathParam("resourceId") String resourceId, RequestOption... options);

    /**
     * Stops creation of the selected artifact bundle, if the associated task is not in completed state.
     *
     * @param resourceId resource identifier
     * @param body The task URI in the format "taskUri":"/rest/tasks/task_ID"
     *
     * @return {@link String} object containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}" + ARTIFACTS_BUNDLE_STOP_ARTIFACT_CREATE_URI, method = HttpMethod.PUT)
    String stopBundleCreation(@PathParam("resourceId") String resourceId, @BodyParam String body);

}
