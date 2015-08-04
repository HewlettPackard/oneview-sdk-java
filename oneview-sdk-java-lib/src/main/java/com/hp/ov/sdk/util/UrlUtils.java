/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
