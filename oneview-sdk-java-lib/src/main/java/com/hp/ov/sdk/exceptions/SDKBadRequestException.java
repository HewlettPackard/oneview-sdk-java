/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKBadRequestException extends SDKException {

    /**
     * <p>
     * Invalid request URI, invalid header, or invalid request parameter sent in
     * request. GET, DELETE- The syntax of a request parameter (filter, query,
     * start, count, sort) is invalid or refers to invalid resource attribute
     * names POST, PUT- Field validation failed: One or more field values sent
     * in an add/update request are not acceptable, because the format or
     * content of the field is invalid.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    public SDKBadRequestException(final SDKErrorKey sdkErrorKey, final Object[] messageParameters,
            final Object[] detailsParameters, final Object[] recommendedActionsParameters, final String errorSource,
            final Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);
    }

}
