/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKUnsupportedMediaTypeException extends SDKException {

    /**
     * <p>
     * The media type of the request body (as specified in the Content-Type
     * header) is not supported by the server.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    public SDKUnsupportedMediaTypeException(SDKErrorKey sdkErrorKey,
                                            Object[] messageParameters,
                                            Object[] detailsParameters,
                                            Object[] recommendedActionsParameters,
                                            String errorSource, Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters,
              recommendedActionsParameters, errorSource, cause);

    }

}
