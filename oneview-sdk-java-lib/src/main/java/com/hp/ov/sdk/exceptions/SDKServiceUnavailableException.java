package com.hp.ov.sdk.exceptions;

public class SDKServiceUnavailableException extends SDKException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public SDKServiceUnavailableException(SDKErrorKey sdkErrorKey, Object[] messageParameters, Object[] detailsParameters,
            Object[] recommendedActionsParameters, String errorSource, Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);

    }

}
