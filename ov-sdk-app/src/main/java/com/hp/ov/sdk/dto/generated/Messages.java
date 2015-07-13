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
        "serverHardware", "eTag", "firmwareStatus", "connections",
        "type"
})
public class Messages implements Serializable
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
    @JsonProperty("serverHardware")
    private List<ServerHardware> serverHardware = new ArrayList<ServerHardware>();
    @JsonProperty("eTag")
    private String eTag;
    @JsonProperty("firmwareStatus")
    private FirmwareStatus firmwareStatus;
    @JsonProperty("connections")
    private List<Connection> connections = new ArrayList<Connection>();
    @JsonProperty("type")
    private String type;

    /**
     * 
     * (Required)
     * 
     * @return The serverHardware
     */
    @JsonProperty("serverHardware")
    public List<ServerHardware> getServerHardware()
    {
        return serverHardware;
    }

    /**
     * 
     * (Required)
     * 
     * @param serverHardware
     *        The serverHardware
     */
    @JsonProperty("serverHardware")
    public void setServerHardware(List<ServerHardware> serverHardware)
    {
        this.serverHardware = serverHardware;
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
    public void setETag(String eTag)
    {
        this.eTag = eTag;
    }

    /**
     * 
     * @return The firmwareStatus
     */
    @JsonProperty("firmwareStatus")
    public FirmwareStatus getFirmwareStatus()
    {
        return firmwareStatus;
    }

    /**
     * 
     * @param firmwareStatus
     *        The firmwareStatus
     */
    @JsonProperty("firmwareStatus")
    public void setFirmwareStatus(FirmwareStatus firmwareStatus)
    {
        this.firmwareStatus = firmwareStatus;
    }

    /**
     * 
     * @return The connections
     */
    @JsonProperty("connections")
    public List<Connection> getConnections()
    {
        return connections;
    }

    /**
     * 
     * @param connections
     *        The connections
     */
    @JsonProperty("connections")
    public void setConnections(List<Connection> connections)
    {
        this.connections = connections;
    }

    /**
     * 
     * @return The type
     */
    @JsonProperty("type")
    public String getType()
    {
        return type;
    }

    /**
     * 
     * @param type
     *        The type
     */
    @JsonProperty("type")
    public void setType(String type)
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
        return new HashCodeBuilder().append(serverHardware).append(eTag)
                .append(firmwareStatus).append(connections).append(type)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof Messages) == false)
        {
            return false;
        }
        Messages rhs = ((Messages) other);
        return new EqualsBuilder().append(serverHardware, rhs.serverHardware)
                .append(eTag, rhs.eTag)
                .append(firmwareStatus, rhs.firmwareStatus)
                .append(connections, rhs.connections).append(type, rhs.type)
                .isEquals();
    }

}
