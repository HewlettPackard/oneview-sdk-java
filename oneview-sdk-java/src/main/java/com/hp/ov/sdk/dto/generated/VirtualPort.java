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
        "mac", "portFunction", "portNumber", "wwnn", "wwpn"
})
public class VirtualPort implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("mac")
    private String mac;
    @JsonProperty("portFunction")
    private String portFunction;
    @JsonProperty("portNumber")
    private Integer portNumber;
    @JsonProperty("wwnn")
    private String wwnn;
    @JsonProperty("wwpn")
    private String wwpn;

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
     * @return The portFunction
     */
    @JsonProperty("portFunction")
    public String getPortFunction()
    {
        return portFunction;
    }

    /**
     * 
     * @param portFunction
     *        The portFunction
     */
    @JsonProperty("portFunction")
    public void setPortFunction(final String portFunction)
    {
        this.portFunction = portFunction;
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
     * @return The wwnn
     */
    @JsonProperty("wwnn")
    public String getWwnn()
    {
        return wwnn;
    }

    /**
     * 
     * @param wwnn
     *        The wwnn
     */
    @JsonProperty("wwnn")
    public void setWwnn(final String wwnn)
    {
        this.wwnn = wwnn;
    }

    /**
     * 
     * @return The wwpn
     */
    @JsonProperty("wwpn")
    public String getWwpn()
    {
        return wwpn;
    }

    /**
     * 
     * @param wwpn
     *        The wwpn
     */
    @JsonProperty("wwpn")
    public void setWwpn(final String wwpn)
    {
        this.wwpn = wwpn;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(mac).append(portFunction)
                .append(portNumber).append(wwnn).append(wwpn).toHashCode();
    }

    @Override
    public boolean equals(final Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof VirtualPort) == false)
        {
            return false;
        }
        final VirtualPort rhs = ((VirtualPort) other);
        return new EqualsBuilder().append(mac, rhs.mac)
                .append(portFunction, rhs.portFunction)
                .append(portNumber, rhs.portNumber).append(wwnn, rhs.wwnn)
                .append(wwpn, rhs.wwpn).isEquals();
    }

}
