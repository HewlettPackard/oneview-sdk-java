/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKResourceNotFoundException extends SDKException {

    /**
     * <p>
     * The URI path (excluding filters) specifies a non-existent resource or
     * collection
     * </p>
     */
    private static final long serialVersionUID = 1L;

    public SDKResourceNotFoundException(final SDKErrorKey sdkErrorKey, final Object[] messageParameters,
            final Object[] detailsParameters, final Object[] recommendedActionsParameters, final String errorSource,
            final Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);
    }

}
