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

import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;
import com.hpe.i3s.dto.deployment.goldenimage.GoldenImage;
import com.hpe.i3s.dto.deployment.goldenimage.GoldenImageFile;

@Api(GoldenImageClient.GOLDEN_IMAGE_URI)
public interface GoldenImageClient extends
        SearchableResource<GoldenImage> {

    String GOLDEN_IMAGE_URI = "/rest/golden-images";
    String GOLDEN_IMAGE_DOWNLOAD_URI = "/download";
    String GOLDEN_IMAGE_ARCHIVED_URI = "/archive";

    /**
     * Creates a resource according to the provided <code>resource</code> object.
     *
     * @param resource object containing the details of the resource that should be created.
     *
     * @return {@link GoldenImage} object containing the result of this request.
     */
    @Endpoint(method = HttpMethod.POST)
    String create(@BodyParam GoldenImage resource);

    /**
     * Creates a golden image resource from the file that is uploaded from a local drive.
     * <p>Only the .zip format file can be used for upload.</p>
     *
     * @param goldenImage object containing the details of the golden image that should be created.
     *                    Details include the file (.zip format, name and description that should
     *                    be used to identify the golden image).
     *
     * @return {@link GoldenImage} object containing the result of this request.
     */
    @Endpoint(method = HttpMethod.POST)
    String create(@BodyParam(type = ContentType.MULTIPART_FORM_DATA) GoldenImageFile goldenImage);

    /**
     * Download the details of the golden image capture logs which has been
     * archived based on the specific attribute ID.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     *
     * @return the archived logs for the specified golden image.
     */
    @Endpoint(uri = GOLDEN_IMAGE_ARCHIVED_URI + "/{resourceId}")
    String getArchivedLogs(@PathParam("resourceId") String resourceId);

    /**
     * Downloads the content of the specified golden image.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     *
     * @return content of the specified golden image.
     */
    @Endpoint(uri = GOLDEN_IMAGE_DOWNLOAD_URI + "/{resourceId}")
    String download(@PathParam("resourceId") String resourceId);

    /**
     * Updates the resource identified by <code>resourceId</code> according to the
     * provided <code>resource</code> object.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param resource object containing the details of the resource that should be created.
     *
     * @return {@link GoldenImage} object containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    GoldenImage update(@PathParam("resourceId") String resourceId, @BodyParam GoldenImage resource);

    /**
     * Deletes the resource identified by the provided <code>resourceId</code>.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     *
     * @return {@link String} object containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.DELETE)
    String delete(@PathParam("resourceId") String resourceId);

}
