/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKInternalException extends SDKException {

    /**
     * <p>
     * This exception is thrown when there is a object to json conversion
     * failure.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    public SDKInternalException(final SDKErrorKey sdkErrorKey, final Object[] messageParameters, final Object[] detailsParameters,
            final Object[] recommendedActionsParameters, final String errorSource, final Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);
    }

}
