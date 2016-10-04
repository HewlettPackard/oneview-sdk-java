/*
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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

package com.hp.ov.sdk.util;

import com.hp.ov.sdk.constants.SdkConstants;

public final class UrlUtils {

    public static String createUrl(String resourceUri, String ... pathSegments) {
        StringBuilder builder = new StringBuilder(resourceUri);

        for (String pathSegment : pathSegments) {
            builder.append("/").append(pathSegment);
        }
        return builder.toString();
    }

    public static String getResourceIdFromUri(final String uri) {
        return uri.substring(uri.lastIndexOf("/") + 1);
    }

    public static String createRestUrl(final String hostname, final String uri) {
        return SdkConstants.HTTPS + hostname + uri;
    }

}
