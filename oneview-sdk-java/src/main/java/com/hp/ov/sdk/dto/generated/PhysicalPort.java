/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "interconnectPort", "interconnectUri", "mac",
        "portNumber", "type", "virtualPorts", "wwn"
})
public class PhysicalPort implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("interconnectPort")
    private Integer interconnectPort;
    @JsonProperty("interconnectUri")
    private String interconnectUri;
    @JsonProperty("mac")
    private String mac;
    @JsonProperty("portNumber")
    private Integer portNumber;
    @JsonProperty("type")
    private PhysicalPort.Type type;
    @JsonProperty("virtualPorts")
    private List<VirtualPort> virtualPorts = new ArrayList<VirtualPort>();
    @JsonProperty("wwn")
    private String wwn;

    /**
     * 
     * @return The interconnectPort
     */
    @JsonProperty("interconnectPort")
    public Integer getInterconnectPort()
    {
        return interconnectPort;
    }

    /**
     * 
     * @param interconnectPort
     *        The interconnectPort
     */
    @JsonProperty("interconnectPort")
    public void setInterconnectPort(final Integer interconnectPort)
    {
        this.interconnectPort = interconnectPort;
    }

    /**
     * 
     * @return The interconnectUri
     */
    @JsonProperty("interconnectUri")
    public String getInterconnectUri()
    {
        return interconnectUri;
    }

    /**
     * 
     * @param interconnectUri
     *        The interconnectUri
     */
    @JsonProperty("interconnectUri")
    public void setInterconnectUri(final String interconnectUri)
    {
        this.interconnectUri = interconnectUri;
    }

    /**
     * 
     * @return The mac
     */
    @JsonProperty("mac")
    public String getMac()
    {
        return mac;
    }

    /**
     * 
     * @param mac
     *        The mac
     */
    @JsonProperty("mac")
    public void setMac(final String mac)
    {
        this.mac = mac;
    }

    /**
     * 
     * @return The portNumber
     */
    @JsonProperty("portNumber")
    public Integer getPortNumber()
    {
        return portNumber;
    }

    /**
     * 
     * @param portNumber
     *        The portNumber
     */
    @JsonProperty("portNumber")
    public void setPortNumber(final Integer portNumber)
    {
        this.portNumber = portNumber;
    }

    /**
     * 
     * @return The type
     */
    @JsonProperty("type")
    public PhysicalPort.Type getType()
    {
        return type;
    }

    /**
     * 
     * @param type
     *        The type
     */
    @JsonProperty("type")
    public void setType(final PhysicalPort.Type type)
    {
        this.type = type;
    }

    /**
     * 
     * @return The virtualPorts
     */
    @JsonProperty("virtualPorts")
    public List<VirtualPort> getVirtualPorts()
    {
        return virtualPorts;
    }

    /**
     * 
     * @param virtualPorts
     *        The virtualPorts
     */
    @JsonProperty("virtualPorts")
    public void setVirtualPorts(final List<VirtualPort> virtualPorts)
    {
        this.virtualPorts = virtualPorts;
    }

    /**
     * 
     * @return The wwn
     */
    @JsonProperty("wwn")
    public String getWwn()
    {
        return wwn;
    }

    /**
     * 
     * @param wwn
     *        The wwn
     */
    @JsonProperty("wwn")
    public void setWwn(final String wwn)
    {
        this.wwn = wwn;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(interconnectPort)
                .append(interconnectUri).append(mac).append(portNumber)
                .append(type).append(virtualPorts).append(wwn).toHashCode();
    }

    @Override
    public boolean equals(final Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof PhysicalPort) == false)
        {
            return false;
        }
        final PhysicalPort rhs = ((PhysicalPort) other);
        return new EqualsBuilder()
                .append(interconnectPort, rhs.interconnectPort)
                .append(interconnectUri, rhs.interconnectUri)
                .append(mac, rhs.mac).append(portNumber, rhs.portNumber)
                .append(type, rhs.type).append(virtualPorts, rhs.virtualPorts)
                .append(wwn, rhs.wwn).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum Type
    {

        Ethernet ("Ethernet"),
        FibreChannel ("FibreChannel"),
        InfiniBand (
                "InfiniBand");
        private final String value;
        private static Map<String, PhysicalPort.Type> constants = new HashMap<String, PhysicalPort.Type>();

        static
        {
            for (final PhysicalPort.Type c : values())
            {
                constants.put(c.value, c);
            }
        }

        private Type(final String value)
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
        public static PhysicalPort.Type fromValue(final String value)
        {
            final PhysicalPort.Type constant = constants.get(value);
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
