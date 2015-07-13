/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKMsmbConnectionNotFoundException extends SDKException
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public SDKMsmbConnectionNotFoundException(SDKErrorKey sdkErrorKey,
            Object[] messageParameters, Object[] detailsParameters,
            Object[] recommendedActionsParameters, String errorSource,
            Throwable cause)
    {
        super(sdkErrorKey, messageParameters, detailsParameters,
                recommendedActionsParameters, errorSource, cause);
    }

}
