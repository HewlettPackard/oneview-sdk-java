/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
// UrlUtils.java - (insert one line description here)
// (C) Copyright 2015 Hewlett-Packard Development Company, L.P.

package com.hp.ov.sdk.util;

import org.springframework.stereotype.Component;

import com.hp.ov.sdk.constants.SdkConstants;

/**
 * 
 */
@Component
public class UrlUtils {

    public String getResourceIdFromUri(final String uri) {
        final String resourceId = uri.substring(uri.lastIndexOf("/") + 1);
        return resourceId;
    }

    public String createFilterString(final String name) {
        return SdkConstants.FILTER_PREFIX + name + SdkConstants.FILTER_APPEND;
    }

    public String createQueryString(final String name) {
        return SdkConstants.QUERY_PREFIX + name + SdkConstants.QUERY_APPEND;
    }

    public String createRestUrl(final String hostname, final String uri) {

        return SdkConstants.HTTPS + hostname + uri;
    }

    public String createRestUrl(final String hostname, final String uri, final String resourceId) {

        return SdkConstants.HTTPS + hostname + uri + "/" + resourceId;
    }

    public String createRestUrl(final String hostname, final String uri, final String resourceId, final String subElement) {

        return SdkConstants.HTTPS + hostname + uri + "/" + resourceId + "/" + subElement;
    }

    public String createRestUrl(final String hostname, final String uri, final String resourceId, final String subElement,
            final String targetId) {

        return SdkConstants.HTTPS + hostname + uri + "/" + resourceId + "/" + subElement + "/" + targetId;
    }

    public String createRestQueryUrl(final String hostname, final String uri, final String query) {

        return SdkConstants.HTTPS + hostname + uri + "?" + query.replaceAll(" ", "%20");
    }
}
