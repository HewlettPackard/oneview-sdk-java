/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "connectionId", "status", "messages" })
public class Connection implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("connectionId")
    private Double connectionId;
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
     * @return The connectionId
     */
    @JsonProperty("connectionId")
    public Double getConnectionId() {
        return connectionId;
    }

    /**
     * 
     * (Required)
     * 
     * @param connectionId
     *            The connectionId
     */
    @JsonProperty("connectionId")
    public void setConnectionId(final Double connectionId) {
        this.connectionId = connectionId;
    }

    /**
     * 
     * (Required)
     * 
     * @return The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * (Required)
     * 
     * @param status
     *            The status
     */
    @JsonProperty("status")
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * 
     * (Required)
     * 
     * @return The messages
     */
    @JsonProperty("messages")
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * 
     * (Required)
     * 
     * @param messages
     *            The messages
     */
    @JsonProperty("messages")
    public void setMessages(final List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(connectionId).append(status).append(messages).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Connection) == false) {
            return false;
        }
        final Connection rhs = ((Connection) other);
        return new EqualsBuilder().append(connectionId, rhs.connectionId).append(status, rhs.status).append(messages, rhs.messages)
                .isEquals();
    }

}
