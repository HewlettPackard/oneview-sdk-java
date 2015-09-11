/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKServiceUnavailableException extends SDKException {

    /**
     * <p>
     * The server is currently unable to handle the request due to temporary
     * overloading or maintenance of the server.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    public SDKServiceUnavailableException(SDKErrorKey sdkErrorKey, Object[] messageParameters, Object[] detailsParameters,
            Object[] recommendedActionsParameters, String errorSource, Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);

    }

}
