/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "sampleInterval", "enableTelemetry", "sampleCount",
        "description", "status", "name", "state", "eTag", "created",
        "modified", "category", "uri", "type"
})
public class TelemetryConfiguration implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("sampleInterval")
    private Integer sampleInterval = 300;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("enableTelemetry")
    private Boolean enableTelemetry = true;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("sampleCount")
    private Integer sampleCount = 12;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
    @JsonProperty("name")
    private String name;
    @JsonProperty("state")
    private String state;
    @JsonProperty("eTag")
    private String eTag;
    @JsonProperty("created")
    private String created;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("category")
    private String category;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("type")
    private String type;

    /**
     * 
     * (Required)
     * 
     * @return The sampleInterval
     */
    @JsonProperty("sampleInterval")
    public Integer getSampleInterval()
    {
        return sampleInterval;
    }

    /**
     * 
     * (Required)
     * 
     * @param sampleInterval
     *        The sampleInterval
     */
    @JsonProperty("sampleInterval")
    public void setSampleInterval(final Integer sampleInterval)
    {
        this.sampleInterval = sampleInterval;
    }

    /**
     * 
     * (Required)
     * 
     * @return The enableTelemetry
     */
    @JsonProperty("enableTelemetry")
    public Boolean getEnableTelemetry()
    {
        return enableTelemetry;
    }

    /**
     * 
     * (Required)
     * 
     * @param enableTelemetry
     *        The enableTelemetry
     */
    @JsonProperty("enableTelemetry")
    public void setEnableTelemetry(final Boolean enableTelemetry)
    {
        this.enableTelemetry = enableTelemetry;
    }

    /**
     * 
     * (Required)
     * 
     * @return The sampleCount
     */
    @JsonProperty("sampleCount")
    public Integer getSampleCount()
    {
        return sampleCount;
    }

    /**
     * 
     * (Required)
     * 
     * @param sampleCount
     *        The sampleCount
     */
    @JsonProperty("sampleCount")
    public void setSampleCount(final Integer sampleCount)
    {
        this.sampleCount = sampleCount;
    }

    /**
     * 
     * @return The description
     */
    @JsonProperty("description")
    public String getDescription()
    {
        return description;
    }

    /**
     * 
     * @param description
     *        The description
     */
    @JsonProperty("description")
    public void setDescription(final String description)
    {
        this.description = description;
    }

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
    public void setStatus(final String status)
    {
        this.status = status;
    }

    /**
     * 
     * @return The name
     */
    @JsonProperty("name")
    public String getName()
    {
        return name;
    }

    /**
     * 
     * @param name
     *        The name
     */
    @JsonProperty("name")
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * 
     * @return The state
     */
    @JsonProperty("state")
    public String getState()
    {
        return state;
    }

    /**
     * 
     * @param state
     *        The state
     */
    @JsonProperty("state")
    public void setState(final String state)
    {
        this.state = state;
    }

    /**
     * 
     * @return The eTag
     */
    @JsonProperty("eTag")
    public String getETag()
    {
        return eTag;
    }

    /**
     * 
     * @param eTag
     *        The eTag
     */
    @JsonProperty("eTag")
    public void setETag(final String eTag)
    {
        this.eTag = eTag;
    }

    /**
     * 
     * @return The created
     */
    @JsonProperty("created")
    public String getCreated()
    {
        return created;
    }

    /**
     * 
     * @param created
     *        The created
     */
    @JsonProperty("created")
    public void setCreated(final String created)
    {
        this.created = created;
    }

    /**
     * 
     * @return The modified
     */
    @JsonProperty("modified")
    public String getModified()
    {
        return modified;
    }

    /**
     * 
     * @param modified
     *        The modified
     */
    @JsonProperty("modified")
    public void setModified(final String modified)
    {
        this.modified = modified;
    }

    /**
     * 
     * @return The category
     */
    @JsonProperty("category")
    public String getCategory()
    {
        return category;
    }

    /**
     * 
     * @param category
     *        The category
     */
    @JsonProperty("category")
    public void setCategory(final String category)
    {
        this.category = category;
    }

    /**
     * 
     * @return The uri
     */
    @JsonProperty("uri")
    public String getUri()
    {
        return uri;
    }

    /**
     * 
     * @param uri
     *        The uri
     */
    @JsonProperty("uri")
    public void setUri(final String uri)
    {
        this.uri = uri;
    }

    /**
     * @return the type
     */
    @JsonProperty("type")
    public String getType()
    {
        return type;
    }

    /**
     * @param type
     *        the type to set
     */
    @JsonProperty("type")
    public void setType(final String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(type).append(sampleInterval)
                .append(enableTelemetry).append(sampleCount)
                .append(description).append(status).append(name).append(state)
                .append(eTag).append(created).append(modified).append(category)
                .append(uri).toHashCode();
    }

    @Override
    public boolean equals(final Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof TelemetryConfiguration) == false)
        {
            return false;
        }
        final TelemetryConfiguration rhs = ((TelemetryConfiguration) other);
        return new EqualsBuilder().append(type, rhs.type)
                .append(sampleInterval, rhs.sampleInterval)
                .append(enableTelemetry, rhs.enableTelemetry)
                .append(sampleCount, rhs.sampleCount)
                .append(description, rhs.description)
                .append(status, rhs.status).append(name, rhs.name)
                .append(state, rhs.state).append(eTag, rhs.eTag)
                .append(created, rhs.created).append(modified, rhs.modified)
                .append(category, rhs.category).append(uri, rhs.uri).isEquals();
    }

}
