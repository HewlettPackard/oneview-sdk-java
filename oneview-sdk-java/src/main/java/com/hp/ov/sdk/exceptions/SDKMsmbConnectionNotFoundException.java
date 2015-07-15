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

    public SDKMsmbConnectionNotFoundException(final SDKErrorKey sdkErrorKey,
            final Object[] messageParameters, final Object[] detailsParameters,
            final Object[] recommendedActionsParameters, final String errorSource,
            final Throwable cause)
    {
        super(sdkErrorKey, messageParameters, detailsParameters,
                recommendedActionsParameters, errorSource, cause);
    }

}
