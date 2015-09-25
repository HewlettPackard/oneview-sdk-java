/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKException extends SdkRuntimeException {

    /**
     * <p>
     * The generic exception class from which all one-view sdk exception extend
     * from.
     * </p>
     */
    private static final long serialVersionUID = 1L;
    private SDKErrorKey errorKey;

    public SDKException(final SDKErrorKey sdkErrorKey,
                        final Object[] messageParameters,
                        final Object[] detailsParameters,
                        final Object[] recommendedActionsParameters,
                        final String errorSource, final Throwable cause) {
        super(sdkErrorKey.getErrorCode(), sdkErrorKey.getMessageKey(),
              messageParameters, sdkErrorKey.getDetailsKey(),
              detailsParameters, sdkErrorKey.getRecommendedActionsKey(),
              recommendedActionsParameters, errorSource, cause);
        setErrorKey(sdkErrorKey);
    }

    public SDKErrorKey getErrorKey() {
        return errorKey;
    }

    protected void setErrorKey(final SDKErrorKey key) {
       // We could simulate final by only allowing it to be set if null;
       this.errorKey = key;
    }
}
