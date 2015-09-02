package com.hp.ov.sdk.exceptions;

public class SDKUnsupportedMediaTypeException extends SDKException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public SDKUnsupportedMediaTypeException(SDKErrorKey sdkErrorKey, Object[] messageParameters, Object[] detailsParameters,
            Object[] recommendedActionsParameters, String errorSource, Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);

    }

}
