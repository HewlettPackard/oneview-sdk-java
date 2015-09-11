/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKUnauthorizedException extends SDKException {

    /**
     * <p>
     * The user (based on session token passed in the Authorization header) is
     * not allowed to perform the operation on the specified resources.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    public SDKUnauthorizedException(SDKErrorKey sdkErrorKey, Object[] messageParameters, Object[] detailsParameters,
            Object[] recommendedActionsParameters, String errorSource, Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);
    }

}
