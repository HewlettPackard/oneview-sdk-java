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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "bayNumber", "fwVersion", "role", "ipAddress",
        "ipv6Addresses", "fqdnHostName", "fwBuildDate", "dhcpEnable",
        "dhcpIpv6Enable", "state"
})
public class Oa implements Serializable
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
    @JsonProperty("bayNumber")
    private Integer bayNumber;
    @JsonProperty("fwVersion")
    private String fwVersion;
    @JsonProperty("role")
    private Oa.Role role;
    @JsonProperty("ipAddress")
    private String ipAddress;
    @JsonProperty("ipv6Addresses")
    private List<Ipv6Address> ipv6Addresses = new ArrayList<Ipv6Address>();
    @JsonProperty("fqdnHostName")
    private String fqdnHostName;
    @JsonProperty("fwBuildDate")
    private String fwBuildDate;
    @JsonProperty("dhcpEnable")
    private Boolean dhcpEnable;
    @JsonProperty("dhcpIpv6Enable")
    private Boolean dhcpIpv6Enable;
    @JsonProperty("state")
    private Oa.State state;

    /**
     * 
     * (Required)
     * 
     * @return The bayNumber
     */
    @JsonProperty("bayNumber")
    public Integer getBayNumber()
    {
        return bayNumber;
    }

    /**
     * 
     * (Required)
     * 
     * @param bayNumber
     *        The bayNumber
     */
    @JsonProperty("bayNumber")
    public void setBayNumber(Integer bayNumber)
    {
        this.bayNumber = bayNumber;
    }

    /**
     * 
     * @return The fwVersion
     */
    @JsonProperty("fwVersion")
    public String getFwVersion()
    {
        return fwVersion;
    }

    /**
     * 
     * @param fwVersion
     *        The fwVersion
     */
    @JsonProperty("fwVersion")
    public void setFwVersion(String fwVersion)
    {
        this.fwVersion = fwVersion;
    }

    /**
     * 
     * @return The role
     */
    @JsonProperty("role")
    public Oa.Role getRole()
    {
        return role;
    }

    /**
     * 
     * @param role
     *        The role
     */
    @JsonProperty("role")
    public void setRole(Oa.Role role)
    {
        this.role = role;
    }

    /**
     * 
     * @return The ipAddress
     */
    @JsonProperty("ipAddress")
    public String getIpAddress()
    {
        return ipAddress;
    }

    /**
     * 
     * @param ipAddress
     *        The ipAddress
     */
    @JsonProperty("ipAddress")
    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    /**
     * 
     * @return The ipv6Addresses
     */
    @JsonProperty("ipv6Addresses")
    public List<Ipv6Address> getIpv6Addresses()
    {
        return ipv6Addresses;
    }

    /**
     * 
     * @param ipv6Addresses
     *        The ipv6Addresses
     */
    @JsonProperty("ipv6Addresses")
    public void setIpv6Addresses(List<Ipv6Address> ipv6Addresses)
    {
        this.ipv6Addresses = ipv6Addresses;
    }

    /**
     * 
     * @return The fqdnHostName
     */
    @JsonProperty("fqdnHostName")
    public String getFqdnHostName()
    {
        return fqdnHostName;
    }

    /**
     * 
     * @param fqdnHostName
     *        The fqdnHostName
     */
    @JsonProperty("fqdnHostName")
    public void setFqdnHostName(String fqdnHostName)
    {
        this.fqdnHostName = fqdnHostName;
    }

    /**
     * 
     * @return The fwBuildDate
     */
    @JsonProperty("fwBuildDate")
    public String getFwBuildDate()
    {
        return fwBuildDate;
    }

    /**
     * 
     * @param fwBuildDate
     *        The fwBuildDate
     */
    @JsonProperty("fwBuildDate")
    public void setFwBuildDate(String fwBuildDate)
    {
        this.fwBuildDate = fwBuildDate;
    }

    /**
     * 
     * @return The dhcpEnable
     */
    @JsonProperty("dhcpEnable")
    public Boolean getDhcpEnable()
    {
        return dhcpEnable;
    }

    /**
     * 
     * @param dhcpEnable
     *        The dhcpEnable
     */
    @JsonProperty("dhcpEnable")
    public void setDhcpEnable(Boolean dhcpEnable)
    {
        this.dhcpEnable = dhcpEnable;
    }

    /**
     * 
     * @return The dhcpIpv6Enable
     */
    @JsonProperty("dhcpIpv6Enable")
    public Boolean getDhcpIpv6Enable()
    {
        return dhcpIpv6Enable;
    }

    /**
     * 
     * @param dhcpIpv6Enable
     *        The dhcpIpv6Enable
     */
    @JsonProperty("dhcpIpv6Enable")
    public void setDhcpIpv6Enable(Boolean dhcpIpv6Enable)
    {
        this.dhcpIpv6Enable = dhcpIpv6Enable;
    }

    /**
     * 
     * @return The state
     */
    @JsonProperty("state")
    public Oa.State getState()
    {
        return state;
    }

    /**
     * 
     * @param state
     *        The state
     */
    @JsonProperty("state")
    public void setState(Oa.State state)
    {
        this.state = state;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(bayNumber).append(fwVersion)
                .append(role).append(ipAddress).append(ipv6Addresses)
                .append(fqdnHostName).append(fwBuildDate).append(dhcpEnable)
                .append(dhcpIpv6Enable).append(state).toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof Oa) == false)
        {
            return false;
        }
        Oa rhs = ((Oa) other);
        return new EqualsBuilder().append(bayNumber, rhs.bayNumber)
                .append(fwVersion, rhs.fwVersion).append(role, rhs.role)
                .append(ipAddress, rhs.ipAddress)
                .append(ipv6Addresses, rhs.ipv6Addresses)
                .append(fqdnHostName, rhs.fqdnHostName)
                .append(fwBuildDate, rhs.fwBuildDate)
                .append(dhcpEnable, rhs.dhcpEnable)
                .append(dhcpIpv6Enable, rhs.dhcpIpv6Enable)
                .append(state, rhs.state).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum Role
    {

        Unknown ("Unknown"),
        OaAbsent ("OaAbsent"),
        Standby ("Standby"),
        Transition (
                "Transition"),
        Active ("Active");
        private final String value;
        private static Map<String, Oa.Role> constants = new HashMap<String, Oa.Role>();

        static
        {
            for (Oa.Role c : values())
            {
                constants.put(c.value, c);
            }
        }

        private Role(String value)
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
        public static Oa.Role fromValue(String value)
        {
            Oa.Role constant = constants.get(value);
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

    @Generated("org.jsonschema2pojo")
    public static enum State
    {

        Online ("Online"),
        Offline ("Offline");
        private final String value;
        private static Map<String, Oa.State> constants = new HashMap<String, Oa.State>();

        static
        {
            for (Oa.State c : values())
            {
                constants.put(c.value, c);
            }
        }

        private State(String value)
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
        public static Oa.State fromValue(String value)
        {
            Oa.State constant = constants.get(value);
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
