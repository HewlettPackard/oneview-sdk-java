/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKPreconditionFailedException extends SDKException {

    /**
     * User attempted to update a resource but provided an unacceptable ETag
     * Also returned when an invalid API version is sent in the X-API-Version
     * header
     */
    private static final long serialVersionUID = 1L;

    public SDKPreconditionFailedException(SDKErrorKey sdkErrorKey, Object[] messageParameters, Object[] detailsParameters,
            Object[] recommendedActionsParameters, String errorSource, Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);
    }

}
