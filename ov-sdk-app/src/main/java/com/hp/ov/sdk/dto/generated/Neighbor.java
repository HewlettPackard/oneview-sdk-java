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
        "remoteChassisId", "remoteChassisIdType",
        "remoteMgmtAddress", "remoteMgmtAddressType", "remotePortDescription",
        "remotePortId", "remotePortIdType", "remoteSystemCapabilities",
        "remoteSystemDescription", "remoteSystemName", "remoteType"
})
public class Neighbor implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("remoteChassisId")
    private String remoteChassisId;
    @JsonProperty("remoteChassisIdType")
    private String remoteChassisIdType;
    @JsonProperty("remoteMgmtAddress")
    private String remoteMgmtAddress;
    @JsonProperty("remoteMgmtAddressType")
    private String remoteMgmtAddressType;
    @JsonProperty("remotePortDescription")
    private String remotePortDescription;
    @JsonProperty("remotePortId")
    private String remotePortId;
    @JsonProperty("remotePortIdType")
    private String remotePortIdType;
    @JsonProperty("remoteSystemCapabilities")
    private String remoteSystemCapabilities;
    @JsonProperty("remoteSystemDescription")
    private String remoteSystemDescription;
    @JsonProperty("remoteSystemName")
    private String remoteSystemName;
    @JsonProperty("remoteType")
    private String remoteType;

    /**
     * 
     * @return The remoteChassisId
     */
    @JsonProperty("remoteChassisId")
    public String getRemoteChassisId()
    {
        return remoteChassisId;
    }

    /**
     * 
     * @param remoteChassisId
     *        The remoteChassisId
     */
    @JsonProperty("remoteChassisId")
    public void setRemoteChassisId(String remoteChassisId)
    {
        this.remoteChassisId = remoteChassisId;
    }

    /**
     * 
     * @return The remoteChassisIdType
     */
    @JsonProperty("remoteChassisIdType")
    public String getRemoteChassisIdType()
    {
        return remoteChassisIdType;
    }

    /**
     * 
     * @param remoteChassisIdType
     *        The remoteChassisIdType
     */
    @JsonProperty("remoteChassisIdType")
    public void setRemoteChassisIdType(String remoteChassisIdType)
    {
        this.remoteChassisIdType = remoteChassisIdType;
    }

    /**
     * 
     * @return The remoteMgmtAddress
     */
    @JsonProperty("remoteMgmtAddress")
    public String getRemoteMgmtAddress()
    {
        return remoteMgmtAddress;
    }

    /**
     * 
     * @param remoteMgmtAddress
     *        The remoteMgmtAddress
     */
    @JsonProperty("remoteMgmtAddress")
    public void setRemoteMgmtAddress(String remoteMgmtAddress)
    {
        this.remoteMgmtAddress = remoteMgmtAddress;
    }

    /**
     * 
     * @return The remoteMgmtAddressType
     */
    @JsonProperty("remoteMgmtAddressType")
    public String getRemoteMgmtAddressType()
    {
        return remoteMgmtAddressType;
    }

    /**
     * 
     * @param remoteMgmtAddressType
     *        The remoteMgmtAddressType
     */
    @JsonProperty("remoteMgmtAddressType")
    public void setRemoteMgmtAddressType(String remoteMgmtAddressType)
    {
        this.remoteMgmtAddressType = remoteMgmtAddressType;
    }

    /**
     * 
     * @return The remotePortDescription
     */
    @JsonProperty("remotePortDescription")
    public String getRemotePortDescription()
    {
        return remotePortDescription;
    }

    /**
     * 
     * @param remotePortDescription
     *        The remotePortDescription
     */
    @JsonProperty("remotePortDescription")
    public void setRemotePortDescription(String remotePortDescription)
    {
        this.remotePortDescription = remotePortDescription;
    }

    /**
     * 
     * @return The remotePortId
     */
    @JsonProperty("remotePortId")
    public String getRemotePortId()
    {
        return remotePortId;
    }

    /**
     * 
     * @param remotePortId
     *        The remotePortId
     */
    @JsonProperty("remotePortId")
    public void setRemotePortId(String remotePortId)
    {
        this.remotePortId = remotePortId;
    }

    /**
     * 
     * @return The remotePortIdType
     */
    @JsonProperty("remotePortIdType")
    public String getRemotePortIdType()
    {
        return remotePortIdType;
    }

    /**
     * 
     * @param remotePortIdType
     *        The remotePortIdType
     */
    @JsonProperty("remotePortIdType")
    public void setRemotePortIdType(String remotePortIdType)
    {
        this.remotePortIdType = remotePortIdType;
    }

    /**
     * 
     * @return The remoteSystemCapabilities
     */
    @JsonProperty("remoteSystemCapabilities")
    public String getRemoteSystemCapabilities()
    {
        return remoteSystemCapabilities;
    }

    /**
     * 
     * @param remoteSystemCapabilities
     *        The remoteSystemCapabilities
     */
    @JsonProperty("remoteSystemCapabilities")
    public void setRemoteSystemCapabilities(String remoteSystemCapabilities)
    {
        this.remoteSystemCapabilities = remoteSystemCapabilities;
    }

    /**
     * 
     * @return The remoteSystemDescription
     */
    @JsonProperty("remoteSystemDescription")
    public String getRemoteSystemDescription()
    {
        return remoteSystemDescription;
    }

    /**
     * 
     * @param remoteSystemDescription
     *        The remoteSystemDescription
     */
    @JsonProperty("remoteSystemDescription")
    public void setRemoteSystemDescription(String remoteSystemDescription)
    {
        this.remoteSystemDescription = remoteSystemDescription;
    }

    /**
     * 
     * @return The remoteSystemName
     */
    @JsonProperty("remoteSystemName")
    public String getRemoteSystemName()
    {
        return remoteSystemName;
    }

    /**
     * 
     * @param remoteSystemName
     *        The remoteSystemName
     */
    @JsonProperty("remoteSystemName")
    public void setRemoteSystemName(String remoteSystemName)
    {
        this.remoteSystemName = remoteSystemName;
    }

    /**
     * 
     * @return The remoteType
     */
    @JsonProperty("remoteType")
    public String getRemoteType()
    {
        return remoteType;
    }

    /**
     * 
     * @param remoteType
     *        The remoteType
     */
    @JsonProperty("remoteType")
    public void setRemoteType(String remoteType)
    {
        this.remoteType = remoteType;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(remoteChassisId)
                .append(remoteChassisIdType).append(remoteMgmtAddress)
                .append(remoteMgmtAddressType).append(remotePortDescription)
                .append(remotePortId).append(remotePortIdType)
                .append(remoteSystemCapabilities)
                .append(remoteSystemDescription).append(remoteSystemName)
                .append(remoteType).toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof Neighbor) == false)
        {
            return false;
        }
        Neighbor rhs = ((Neighbor) other);
        return new EqualsBuilder().append(remoteChassisId, rhs.remoteChassisId)
                .append(remoteChassisIdType, rhs.remoteChassisIdType)
                .append(remoteMgmtAddress, rhs.remoteMgmtAddress)
                .append(remoteMgmtAddressType, rhs.remoteMgmtAddressType)
                .append(remotePortDescription, rhs.remotePortDescription)
                .append(remotePortId, rhs.remotePortId)
                .append(remotePortIdType, rhs.remotePortIdType)
                .append(remoteSystemCapabilities, rhs.remoteSystemCapabilities)
                .append(remoteSystemDescription, rhs.remoteSystemDescription)
                .append(remoteSystemName, rhs.remoteSystemName)
                .append(remoteType, rhs.remoteType).isEquals();
    }

}
