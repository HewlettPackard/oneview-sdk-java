/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SdkRuntimeException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final String errorCode;
    private final String messageKey;
    private final Object[] messageParameters;
    private final String detailsKey;
    private final Object[] detailsParameters;
    private final String recommendedActionsKey;
    private final Object[] recommendedActionsParameters;
    private final String errorSource;

    public SdkRuntimeException(final String errorCode, final String messageKey, final Object[] messageParameters,
            final String detailsKey, final Object[] detailsParameters, final String recommendedActionsKey,
            final Object[] recommendedActionsParameters, final String errorSource, final Throwable cause) {
        super(cause);

        this.errorCode = errorCode;
        this.messageKey = messageKey;
        this.messageParameters = (messageParameters == null ? new Object[0] : messageParameters);
        this.detailsKey = detailsKey;
        this.detailsParameters = (detailsParameters == null ? new Object[0] : detailsParameters);
        this.recommendedActionsKey = recommendedActionsKey;
        this.recommendedActionsParameters = (recommendedActionsParameters == null ? new Object[0] : recommendedActionsParameters);
        this.errorSource = errorSource;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public Object[] getMessageParameters() {
        return messageParameters;
    }

    public String getDetailsKey() {
        return detailsKey;
    }

    public Object[] getDetailsParameters() {
        return detailsParameters;
    }

    public String getRecommendedActionsKey() {
        return recommendedActionsKey;
    }

    public Object[] getRecommendedActionsParameters() {
        return recommendedActionsParameters;
    }

    public String getErrorSource() {
        return errorSource;
    }
}
