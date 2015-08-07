/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ErrorMessage implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Map<String, String> data;
    private String details;
    private String errorCode;
    private String errorSource;
    private String message;
    private List<ErrorMessage> nestedErrors = new ArrayList<ErrorMessage>();
    private List<String> recommendedActions = new ArrayList<String>();

    /**
     * @return the data
     */
    public Map<String, String> getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(final Map<String, String> data) {
        this.data = data;
    }

    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details
     *            the details to set
     */
    public void setDetails(final String details) {
        this.details = details;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorSource
     */
    public String getErrorSource() {
        return errorSource;
    }

    /**
     * @param errorSource
     *            the errorSource to set
     */
    public void setErrorSource(final String errorSource) {
        this.errorSource = errorSource;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return the nestedErrors
     */
    public List<ErrorMessage> getNestedErrors() {
        return nestedErrors;
    }

    /**
     * @param nestedErrors
     *            the nestedErrors to set
     */
    public void setNestedErrors(final List<ErrorMessage> nestedErrors) {
        this.nestedErrors = nestedErrors;
    }

    /**
     * @return the recommendedActions
     */
    public List<String> getRecommendedActions() {
        return recommendedActions;
    }

    /**
     * @param recommendedActions
     *            the recommendedActions to set
     */
    public void setRecommendedActions(final List<String> recommendedActions) {
        this.recommendedActions = recommendedActions;
    }

}
