package com.hp.ov.sdk.exceptions;

public class SDKSSLHandshakeException extends SDKException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public SDKSSLHandshakeException(SDKErrorKey sdkErrorKey, Object[] messageParameters, Object[] detailsParameters,
            Object[] recommendedActionsParameters, String errorSource, Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);
    }

}
