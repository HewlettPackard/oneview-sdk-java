/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKForbiddenException extends SDKException {

    /**
     * <p>
     * User attempted to update an attribute that is read-only or the user does
     * not have appropriate permissions to update the attribute.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    public SDKForbiddenException(SDKErrorKey sdkErrorKey, Object[] messageParameters, Object[] detailsParameters,
            Object[] recommendedActionsParameters, String errorSource, Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);

    }

}
