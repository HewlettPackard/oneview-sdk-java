/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKApplianceNotReachableException extends SDKException {

    /**
     * <p>
     * The connection is not reachable or is disconnected due to timeout.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    public SDKApplianceNotReachableException(final SDKErrorKey sdkErrorKey, final Object[] messageParameters,
            final Object[] detailsParameters, final Object[] recommendedActionsParameters, final String errorSource,
            final Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);
    }

}
