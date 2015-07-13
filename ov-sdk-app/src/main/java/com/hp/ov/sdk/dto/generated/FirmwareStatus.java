/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
        "status", "messages", "version", "name"
})
public class FirmwareStatus implements Serializable
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
    @JsonProperty("status")
    private String status;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("messages")
    private List<Message> messages = new ArrayList<Message>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("version")
    private String version;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("name")
    private String name;

    /**
     * 
     * (Required)
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
     * (Required)
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
     * (Required)
     * 
     * @return The messages
     */
    @JsonProperty("messages")
    public List<Message> getMessages()
    {
        return messages;
    }

    /**
     * 
     * (Required)
     * 
     * @param messages
     *        The messages
     */
    @JsonProperty("messages")
    public void setMessages(List<Message> messages)
    {
        this.messages = messages;
    }

    /**
     * 
     * (Required)
     * 
     * @return The version
     */
    @JsonProperty("version")
    public String getVersion()
    {
        return version;
    }

    /**
     * 
     * (Required)
     * 
     * @param version
     *        The version
     */
    @JsonProperty("version")
    public void setVersion(String version)
    {
        this.version = version;
    }

    /**
     * 
     * (Required)
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
     * (Required)
     * 
     * @param name
     *        The name
     */
    @JsonProperty("name")
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(status).append(messages)
                .append(version).append(name).toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof FirmwareStatus) == false)
        {
            return false;
        }
        FirmwareStatus rhs = ((FirmwareStatus) other);
        return new EqualsBuilder().append(status, rhs.status)
                .append(messages, rhs.messages).append(version, rhs.version)
                .append(name, rhs.name).isEquals();
    }

}
