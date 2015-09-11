/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKConflictDueToStateException extends SDKException {

    /**
     * <p>
     * The request could not be completed due to a conflict with the current
     * state of the resource(s)
     * </p>
     */
    private static final long serialVersionUID = 1L;

    public SDKConflictDueToStateException(SDKErrorKey sdkErrorKey, Object[] messageParameters, Object[] detailsParameters,
            Object[] recommendedActionsParameters, String errorSource, Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);

    }

}
