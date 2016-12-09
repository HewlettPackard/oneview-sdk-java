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

import java.io.File;
import java.util.List;

import com.google.common.reflect.Parameter;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.rest.client.common.CreatableResource;
import com.hp.ov.sdk.rest.client.common.DeletableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.RequestInterceptor;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;
import com.hp.ov.sdk.rest.reflect.QueryParam;
import com.hpe.i3s.dto.deployment.artifactsbundle.ArtifactsBundle;
import com.hpe.i3s.dto.deployment.artifactsbundle.CreateArtifactsBundle;
import com.hpe.i3s.dto.deployment.artifactsbundle.TaskUri;
import com.hpe.i3s.dto.deployment.artifactsbundle.UserBackupParams;

@Api(ArtifactsBundleClient.ARTIFACTS_BUNDLE_URI)
public interface ArtifactsBundleClient extends
        SearchableResource<ArtifactsBundle>,
        CreatableResource<CreateArtifactsBundle>,
        DeletableResource {

    String ARTIFACTS_BUNDLE_URI = "/rest/artifact-bundles";
    String ARTIFACTS_BUNDLE_BACKUPS_URI = "/backups";
    String ARTIFACTS_BUNDLE_ARCHIVE_URI = "/archive";
    String ARTIFACTS_BUNDLE_DOWNLOAD_URI = "/download";
    String ARTIFACTS_BUNDLE_STOP_ARTIFACT_CREATE_URI = "/stopArtifactCreate";

    /**
     * Creates an artifact bundle according to the provided <code>file</code> object.
     *
     * @param file {@link File} instance containing the artifacts bundle file.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(method = HttpMethod.POST, forceReturnTask = true)
    TaskResource create(@BodyParam(type = ContentType.MULTIPART_FORM_DATA) File file, RequestOption... options);

    /**
     * Retrieves a paginated collection ({@link ResourceCollection}) containing the details for the
     * available backup bundles.
     *
     * @return {@link ResourceCollection} collection containing the details for the
     * available resources.
     */
    @Endpoint(uri = ARTIFACTS_BUNDLE_BACKUPS_URI)
    ResourceCollection<ArtifactsBundle> getBackupBundles();

    /**
     * Creates a backup bundle with all the artifacts present on the appliance.
     *
     * @param userBackupParams attributes of the backup bundle.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = ARTIFACTS_BUNDLE_BACKUPS_URI, method = HttpMethod.POST)
    TaskResource createBackupBundle(@BodyParam UserBackupParams userBackupParams, RequestOption... options);

    /**
     * Uploads a backup bundle from a local drive and extract all the artifacts present in the uploaded file.
     *
     * @param file {@link File} instance containing the backup artifacts bundle file.
     * @param deploymentGrpUri deployment group uri.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = ARTIFACTS_BUNDLE_BACKUPS_URI + ARTIFACTS_BUNDLE_ARCHIVE_URI,
            method = HttpMethod.POST, forceReturnTask = true)
    TaskResource createBackupArchiveBundle(@BodyParam(type = ContentType.MULTIPART_FORM_DATA) File file, 
    		@QueryParam(key = "deploymentGrpUri") String deploymentGrpUri,  
    		
            RequestOption... options);

    /**
     * Downloads the backup archive bundle identified by the provided <code>resourceId</code>.
     *
     * @param resourceId artifacts bundle identifier.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link String} representing the path to the downloaded bundle.
     */
    @Endpoint(uri = ARTIFACTS_BUNDLE_BACKUPS_URI + ARTIFACTS_BUNDLE_ARCHIVE_URI + "/{resourceId}")
    String downloadBackupArchiveBundle(@PathParam("resourceId") String resourceId, RequestOption... options);

    /**
     * Retrieves the backup bundle identified by the given <code>resourceId</code>.
     *
     * @param resourceId artifacts bundle resource identifier.
     *
     * @return {@link ArtifactsBundle} object containing the result of this request.
     */
    @Endpoint(uri = ARTIFACTS_BUNDLE_BACKUPS_URI + "/{resourceId}")
    ArtifactsBundle getBackupBundle(@PathParam("resourceId") String resourceId);

    /**
     * Extracts the existing backup bundle on the appliance and creates all the artifacts.
     *
     * @param resourceId artifacts bundle resource identifier.
     * @param userBackupParams attributes of the backup.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = ARTIFACTS_BUNDLE_BACKUPS_URI + "/{resourceId}", method = HttpMethod.PUT)
    TaskResource extractBackupBundle(@PathParam("resourceId") String resourceId,
            @BodyParam UserBackupParams userBackupParams, RequestOption... options);

    /**
     * Downloads the content of the selected artifacts bundle to the admin's local drive.
     *
     * @param resourceId artifacts bundle resource identifier.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link String} representing the path to the downloaded bundle.
     */
    @Endpoint(uri = ARTIFACTS_BUNDLE_DOWNLOAD_URI + "/{resourceId}")
    String downloadBundle(@PathParam("resourceId") String resourceId, RequestOption... options);

    /**
     * Extracts the selected artifact bundle and creates the artifacts on the appliance.
     *
     * @param resourceId artifacts bundle resource identifier.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} task containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT,
            requestInterceptor = ExtractBundleRequestInterceptor.class)
    TaskResource extractBundle(@PathParam("resourceId") String resourceId, RequestOption... options);

    class ExtractBundleRequestInterceptor implements RequestInterceptor {
        @Override
        public Request intercept(Request request, List<Parameter> params, Object[] args) {
            request.setContentType(ContentType.TEXT_PLAIN);
            request.setEntity("");

            return request;
        }
    }

    /**
     * Stops the creation of the selected artifact bundle, if the associated task is not in completed state.
     *
     * @param resourceId artifacts bundle resource identifier.
     * @param taskUri object containing the URI of the task that should be interrupted.
     *
     * @return {@link String} object containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}" + ARTIFACTS_BUNDLE_STOP_ARTIFACT_CREATE_URI, method = HttpMethod.PUT)
    String stopBundleCreation(@PathParam("resourceId") String resourceId, @BodyParam TaskUri taskUri);

    /**
     * Updates the resource identified by <code>resourceId</code> according to the
     * provided <code>resource</code> object.
     *
     * @param resourceId artifacts bundle resource identifier.
     * @param resource object containing the details of the resource that should be updated.
     *
     * @return {@link ArtifactsBundle} object containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    ArtifactsBundle update(@PathParam("resourceId") String resourceId, @BodyParam ArtifactsBundle resource);
}
