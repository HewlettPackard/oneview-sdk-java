/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SDKSocketException extends SDKException {

    /**
     * <p>
     * This occurs when the TrustStore and KeyStore file is not found. This also
     * occurs when the connection between one-view and connecting code is
     * lost(physical link disconnection) Can also be due to
     * SSLHandshakeException. Check the certificates.
     * </p>
     */
    private static final long serialVersionUID = 1L;

    public SDKSocketException(SDKErrorKey sdkErrorKey, Object[] messageParameters, Object[] detailsParameters,
            Object[] recommendedActionsParameters, String errorSource, Throwable cause) {
        super(sdkErrorKey, messageParameters, detailsParameters, recommendedActionsParameters, errorSource, cause);
    }

}
