/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKNoSuchUrlException extends SDKException {

    /**
     * The URL is incorrect
     */
    private static final long serialVersionUID = 1L;

    public SDKNoSuchUrlException(final SDKErrorKey sdkErrorKey, final Object[] messageParameters, final Object[] detailsParameters,
            final Object[] recommendedActionsParameters, final String errorSource, final Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);
    }

}
