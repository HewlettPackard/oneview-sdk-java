package com.hp.ov.sdk.exceptions;

public class SDKInternalServerErrorException extends SDKException

{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public SDKInternalServerErrorException(SDKErrorKey sdkErrorKey, Object[] messageParameters, Object[] detailsParameters,
            Object[] recommendedActionsParameters, String errorSource, Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);
    }

}
