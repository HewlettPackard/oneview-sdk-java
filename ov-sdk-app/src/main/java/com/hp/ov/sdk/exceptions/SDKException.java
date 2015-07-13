/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKException extends SdkRuntimeException
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final SDKErrorKey errorKey;

    public SDKException(SDKErrorKey sdkErrorKey,
            Object[] messageParameters,
            Object[] detailsParameters,
            Object[] recommendedActionsParameters,
            String errorSource,
            Throwable cause)
    {
        super(sdkErrorKey.getErrorCode(),
                sdkErrorKey.getMessageKey(), messageParameters,
                sdkErrorKey.getDetailsKey(), detailsParameters,
                sdkErrorKey.getRecommendedActionsKey(), recommendedActionsParameters,
                errorSource, cause);
        this.errorKey = sdkErrorKey;

    }

    public SDKErrorKey getErrorKey()
    {
        return errorKey;
    }

}
