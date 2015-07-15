/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.hp.ov.sdk.dto.JsonRequest;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "bandwidth", "smartLink", "namePrefix", "vlanIdRange",
        "privateNetwork", "purpose", "type", "jsonRequest"
})
public class BulkEthernetNetwork implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("bandwidth")
    private Bandwidth bandwidth;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("smartLink")
    private Boolean smartLink;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("namePrefix")
    private String namePrefix;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("vlanIdRange")
    private String vlanIdRange;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("privateNetwork")
    private Boolean privateNetwork;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("purpose")
    private BulkEthernetNetwork.Purpose purpose;
    @JsonProperty("type")
    private String type;
    @JsonProperty("jsonRequest")
    private JsonRequest jsonRequest;

    /**
     * 
     * @return The bandwidth
     */
    @JsonProperty("bandwidth")
    public Bandwidth getBandwidth()
    {
        return bandwidth;
    }

    /**
     * 
     * @param bandwidth
     *        The bandwidth
     */
    @JsonProperty("bandwidth")
    public void setBandwidth(final Bandwidth bandwidth)
    {
        this.bandwidth = bandwidth;
    }

    /**
     * 
     * (Required)
     * 
     * @return The smartLink
     */
    @JsonProperty("smartLink")
    public Boolean getSmartLink()
    {
        return smartLink;
    }

    /**
     * 
     * (Required)
     * 
     * @param smartLink
     *        The smartLink
     */
    @JsonProperty("smartLink")
    public void setSmartLink(final Boolean smartLink)
    {
        this.smartLink = smartLink;
    }

    /**
     * 
     * (Required)
     * 
     * @return The namePrefix
     */
    @JsonProperty("namePrefix")
    public String getNamePrefix()
    {
        return namePrefix;
    }

    /**
     * 
     * (Required)
     * 
     * @param namePrefix
     *        The namePrefix
     */
    @JsonProperty("namePrefix")
    public void setNamePrefix(final String namePrefix)
    {
        this.namePrefix = namePrefix;
    }

    /**
     * 
     * (Required)
     * 
     * @return The vlanIdRange
     */
    @JsonProperty("vlanIdRange")
    public String getVlanIdRange()
    {
        return vlanIdRange;
    }

    /**
     * 
     * (Required)
     * 
     * @param vlanIdRange
     *        The vlanIdRange
     */
    @JsonProperty("vlanIdRange")
    public void setVlanIdRange(final String vlanIdRange)
    {
        this.vlanIdRange = vlanIdRange;
    }

    /**
     * 
     * (Required)
     * 
     * @return The privateNetwork
     */
    @JsonProperty("privateNetwork")
    public Boolean getPrivateNetwork()
    {
        return privateNetwork;
    }

    /**
     * 
     * (Required)
     * 
     * @param privateNetwork
     *        The privateNetwork
     */
    @JsonProperty("privateNetwork")
    public void setPrivateNetwork(final Boolean privateNetwork)
    {
        this.privateNetwork = privateNetwork;
    }

    /**
     * 
     * (Required)
     * 
     * @return The purpose
     */
    @JsonProperty("purpose")
    public BulkEthernetNetwork.Purpose getPurpose()
    {
        return purpose;
    }

    /**
     * 
     * (Required)
     * 
     * @param purpose
     *        The purpose
     */
    @JsonProperty("purpose")
    public void setPurpose(final BulkEthernetNetwork.Purpose purpose)
    {
        this.purpose = purpose;
    }

    /**
     * 
     * @return The type
     */
    public String getType()
    {
        return type;
    }

    /**
     * 
     * @param type
     *        The type
     */
    public void setType(final String type)
    {
        this.type = type;
    }

    /**
     * 
     * @return The jsonRequest
     */
    public JsonRequest getJsonRequest()
    {
        return jsonRequest;
    }

    /**
     * 
     * @param jsonRequest
     *        The jsonRequest
     */
    public void setJsonRequest(final JsonRequest jsonRequest)
    {
        this.jsonRequest = jsonRequest;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(jsonRequest).append(bandwidth)
                .append(smartLink).append(namePrefix).append(vlanIdRange)
                .append(privateNetwork).append(purpose).toHashCode();
    }

    @Override
    public boolean equals(final Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof BulkEthernetNetwork) == false)
        {
            return false;
        }
        final BulkEthernetNetwork rhs = ((BulkEthernetNetwork) other);
        return new EqualsBuilder().append(jsonRequest, rhs.jsonRequest)
                .append(bandwidth, rhs.bandwidth)
                .append(smartLink, rhs.smartLink)
                .append(namePrefix, rhs.namePrefix)
                .append(vlanIdRange, rhs.vlanIdRange)
                .append(privateNetwork, rhs.privateNetwork)
                .append(purpose, rhs.purpose).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum Purpose
    {

        General ("General"),
        Management ("Management"),
        VMMigration ("VMMigration"),
        FaultTolerance (
                "FaultTolerance");
        private final String value;
        private static Map<String, BulkEthernetNetwork.Purpose> constants = new HashMap<String, BulkEthernetNetwork.Purpose>();

        static
        {
            for (final BulkEthernetNetwork.Purpose c : values())
            {
                constants.put(c.value, c);
            }
        }

        private Purpose(final String value)
        {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString()
        {
            return this.value;
        }

        @JsonCreator
        public static BulkEthernetNetwork.Purpose fromValue(final String value)
        {
            final BulkEthernetNetwork.Purpose constant = constants.get(value);
            if (constant == null)
            {
                throw new IllegalArgumentException(value);
            }
            else
            {
                return constant;
            }
        }

    }

}
