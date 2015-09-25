/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

public class SdkRuntimeException extends RuntimeException {

    /**
     * <p>
     * This exception is thrown by the execution of the method or constructor
     * and propagate outside the method or constructor boundary
     * </p>
     */
    private static final long serialVersionUID = 1L;

    private String errorCode;
    private String messageKey;
    private Object[] messageParameters;
    private String detailsKey;
    private Object[] detailsParameters;
    private String recommendedActionsKey;
    private Object[] recommendedActionsParameters;
    private String errorSource;

    public SdkRuntimeException(final String errorCode,
                               final String messageKey,
                               final Object[] messageParameters,
                               final String detailsKey,
                               final Object[] detailsParameters,
                               final String recommendedActionsKey,
                               final Object[] recommendedActionsParameters,
                               final String errorSource,
                               final Throwable cause) {
        super(cause);

        setErrorCode(errorCode);
        setMessageKey(messageKey);
        setMessageParameters(messageParameters);
        setDetailsKey(detailsKey);
        setDetailsParameters(detailsParameters);
        setRecommendedActionsKey(recommendedActionsKey);
        setRecommendedActionsParameters(recommendedActionsParameters);
        setErrorSource(errorSource);
    }

    public String getErrorCode() {
        return errorCode;
    }

    protected void setErrorCode(final String errorCode) {
       this.errorCode = errorCode;
    }

    public String getMessageKey() {
        return messageKey;
    }

    protected void setMessageKey(final String key) {
        this.messageKey = key;
    }

    public Object[] getMessageParameters() {
        if (this.messageParameters == null) {
           return new Object[0];
        }
        return messageParameters;
    }

    protected void setMessageParameters(final Object[] parameters) {
       if (parameters == null) {
          this.messageParameters = new Object[0];
       } else {
          this.messageParameters = parameters;
       }
    }

    public String getDetailsKey() {
        return detailsKey;
    }

    protected void setDetailsKey(final String key) {
        this.detailsKey = key;
    }

    public Object[] getDetailsParameters() {
        if (this.detailsParameters == null) {
           return new Object[0];
        }
        return detailsParameters;
    }

    protected void setDetailsParameters(final Object[] parameters) {
       if (parameters == null) {
          this.detailsParameters = new Object[0];
       } else {
          this.detailsParameters = parameters;
       }
    }

    public String getRecommendedActionsKey() {
        return recommendedActionsKey;
    }

    protected void setRecommendedActionsKey(final String key) {
        this.recommendedActionsKey = key;
    }

    public Object[] getRecommendedActionsParameters() {
        if (this.recommendedActionsParameters == null) {
           return new Object[0];
        }
        return recommendedActionsParameters;
    }

    protected void setRecommendedActionsParameters(final Object[] parameters) {
       if (parameters == null) {
          this.recommendedActionsParameters = new Object[0];
       } else {
          this.recommendedActionsParameters = parameters;
       }
    }

    public String getErrorSource() {
        return errorSource;
    }

    protected void setErrorSource(final String source) {
        this.errorSource = source;
    }
}
