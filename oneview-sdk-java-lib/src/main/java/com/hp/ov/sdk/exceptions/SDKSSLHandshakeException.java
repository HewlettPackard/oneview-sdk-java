/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKSSLHandshakeException extends SDKException {

    /**
     * <p>
     * The KeyStore and TrustStore certificates are a mismatch to that in
     * server.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    public SDKSSLHandshakeException(SDKErrorKey sdkErrorKey, Object[] messageParameters, Object[] detailsParameters,
            Object[] recommendedActionsParameters, String errorSource, Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);
    }

}
