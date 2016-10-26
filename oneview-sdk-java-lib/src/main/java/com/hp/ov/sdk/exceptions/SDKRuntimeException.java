/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.hp.ov.sdk.exceptions;

/**
 * This exception is thrown by the execution of the method or constructor
 * and propagate outside the method or constructor boundary
 */
public class SDKRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorCode;
    private String messageKey;
    private Object[] messageParameters;
    private String detailsKey;
    private Object[] detailsParameters;
    private String recommendedActionsKey;
    private Object[] recommendedActionsParameters;
    private String errorSource;

    public SDKRuntimeException(String errorCode,
            String messageKey,
            Object[] messageParameters,
            String detailsKey,
            Object[] detailsParameters,
            String recommendedActionsKey,
            Object[] recommendedActionsParameters,
            String errorSource,
            Throwable cause) {

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

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the messageKey
     */
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * @param messageKey the messageKey to set
     */
    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    /**
     * @return the messageParameters
     */
    public Object[] getMessageParameters() {
        return messageParameters;
    }

    /**
     * @param messageParameters the messageParameters to set
     */
    public void setMessageParameters(Object[] messageParameters) {
        this.messageParameters = messageParameters;
    }

    /**
     * @return the detailsKey
     */
    public String getDetailsKey() {
        return detailsKey;
    }

    /**
     * @param detailsKey the detailsKey to set
     */
    public void setDetailsKey(String detailsKey) {
        this.detailsKey = detailsKey;
    }

    /**
     * @return the detailsParameters
     */
    public Object[] getDetailsParameters() {
        return detailsParameters;
    }

    /**
     * @param detailsParameters the detailsParameters to set
     */
    public void setDetailsParameters(Object[] detailsParameters) {
        this.detailsParameters = detailsParameters;
    }

    /**
     * @return the recommendedActionsKey
     */
    public String getRecommendedActionsKey() {
        return recommendedActionsKey;
    }

    /**
     * @param recommendedActionsKey the recommendedActionsKey to set
     */
    public void setRecommendedActionsKey(String recommendedActionsKey) {
        this.recommendedActionsKey = recommendedActionsKey;
    }

    /**
     * @return the recommendedActionsParameters
     */
    public Object[] getRecommendedActionsParameters() {
        return recommendedActionsParameters;
    }

    /**
     * @param recommendedActionsParameters the recommendedActionsParameters to set
     */
    public void setRecommendedActionsParameters(Object[] recommendedActionsParameters) {
        this.recommendedActionsParameters = recommendedActionsParameters;
    }

    /**
     * @return the errorSource
     */
    public String getErrorSource() {
        return errorSource;
    }

    /**
     * @param errorSource the errorSource to set
     */
    public void setErrorSource(String errorSource) {
        this.errorSource = errorSource;
    }
}
