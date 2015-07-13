/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "status", "messageId", "errorSource", "sourceName",
        "activityUri", "message", "recommendedActions", "createdTime"
})
public class Message implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("status")
    private String status;
    @JsonProperty("messageId")
    private String messageId;
    @JsonProperty("errorSource")
    private String errorSource;
    @JsonProperty("sourceName")
    private String sourceName;
    @JsonProperty("activityUri")
    private String activityUri;
    @JsonProperty("message")
    private String message;
    @JsonProperty("recommendedActions")
    private String recommendedActions;
    @JsonProperty("createdTime")
    private String createdTime;

    /**
     * 
     * @return The status
     */
    @JsonProperty("status")
    public String getStatus()
    {
        return status;
    }

    /**
     * 
     * @param status
     *        The status
     */
    @JsonProperty("status")
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * 
     * @return The messageId
     */
    @JsonProperty("messageId")
    public String getMessageId()
    {
        return messageId;
    }

    /**
     * 
     * @param messageId
     *        The messageId
     */
    @JsonProperty("messageId")
    public void setMessageId(String messageId)
    {
        this.messageId = messageId;
    }

    /**
     * 
     * @return The errorSource
     */
    @JsonProperty("errorSource")
    public String getErrorSource()
    {
        return errorSource;
    }

    /**
     * 
     * @param errorSource
     *        The errorSource
     */
    @JsonProperty("errorSource")
    public void setErrorSource(String errorSource)
    {
        this.errorSource = errorSource;
    }

    /**
     * 
     * @return The sourceName
     */
    @JsonProperty("sourceName")
    public String getSourceName()
    {
        return sourceName;
    }

    /**
     * 
     * @param sourceName
     *        The sourceName
     */
    @JsonProperty("sourceName")
    public void setSourceName(String sourceName)
    {
        this.sourceName = sourceName;
    }

    /**
     * 
     * @return The activityUri
     */
    @JsonProperty("activityUri")
    public String getActivityUri()
    {
        return activityUri;
    }

    /**
     * 
     * @param activityUri
     *        The activityUri
     */
    @JsonProperty("activityUri")
    public void setActivityUri(String activityUri)
    {
        this.activityUri = activityUri;
    }

    /**
     * 
     * @return The message
     */
    @JsonProperty("message")
    public String getMessage()
    {
        return message;
    }

    /**
     * 
     * @param message
     *        The message
     */
    @JsonProperty("message")
    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
     * 
     * @return The recommendedActions
     */
    @JsonProperty("recommendedActions")
    public String getRecommendedActions()
    {
        return recommendedActions;
    }

    /**
     * 
     * @param recommendedActions
     *        The recommendedActions
     */
    @JsonProperty("recommendedActions")
    public void setRecommendedActions(String recommendedActions)
    {
        this.recommendedActions = recommendedActions;
    }

    /**
     * 
     * @return The createdTime
     */
    @JsonProperty("createdTime")
    public String getCreatedTime()
    {
        return createdTime;
    }

    /**
     * 
     * @param createdTime
     *        The createdTime
     */
    @JsonProperty("createdTime")
    public void setCreatedTime(String createdTime)
    {
        this.createdTime = createdTime;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(status).append(messageId)
                .append(errorSource).append(sourceName).append(activityUri)
                .append(message).append(recommendedActions).append(createdTime)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof Message) == false)
        {
            return false;
        }
        Message rhs = ((Message) other);
        return new EqualsBuilder().append(status, rhs.status)
                .append(messageId, rhs.messageId)
                .append(errorSource, rhs.errorSource)
                .append(sourceName, rhs.sourceName)
                .append(activityUri, rhs.activityUri)
                .append(message, rhs.message)
                .append(recommendedActions, rhs.recommendedActions)
                .append(createdTime, rhs.createdTime).isEquals();
    }

}
