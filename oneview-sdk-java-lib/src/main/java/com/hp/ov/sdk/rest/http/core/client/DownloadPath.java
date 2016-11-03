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

package com.hp.ov.sdk.rest.http.core.client;

/**
 * Some operations return a file as the entity of a response. In this cases, it is
 * possible to specify a destination folder using an implementation of {@link RequestOption}
 * called {@link com.hp.ov.sdk.rest.http.core.client.DownloadPath}.
 *
 * <p>Once a directory path has been configured, the returned file will be saved in that specific
 * folder.
 *
 * <p>If no path is specified, the default behavior is to use the value specified on
 * the SDK configuration (using object {@link SDKConfiguration}).
 * Below is an example that shows how the download path can be specified:
 *
 * <pre>{@code
 *     SomeClient client = oneViewClient.someClient();
 *     SomeResource resource = new SomeResource();
 *     client.downloadFile(resourceID, new DownloadPath("~/Desktop/"));
 * }</pre>
 */
public class DownloadPath implements RequestOption {

    private final String path;

    public DownloadPath(String path) {
        this.path = path;
    }

    public static DownloadPath at(String path) {
        return new DownloadPath(path);
    }

    @Override
    public void apply(Request request) {
        request.setDownloadPath(this.path);
    }
}
