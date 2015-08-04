/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
@JsonPropertyOrder({ "portName", "portNumber", "pairedPortName", "portCapability", "downlinkCapable", "uplinkCapable" })
public class PortInfo implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("portName")
    private String portName;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("portNumber")
    private Integer portNumber;
    @JsonProperty("pairedPortName")
    private String pairedPortName;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("portCapability")
    private List<String> portCapability = new ArrayList<String>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("downlinkCapable")
    private Boolean downlinkCapable;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("uplinkCapable")
    private Boolean uplinkCapable;

    /**
     * 
     * (Required)
     * 
     * @return The portName
     */
    @JsonProperty("portName")
    public String getPortName() {
        return portName;
    }

    /**
     * 
     * (Required)
     * 
     * @param portName
     *            The portName
     */
    @JsonProperty("portName")
    public void setPortName(final String portName) {
        this.portName = portName;
    }

    /**
     * 
     * (Required)
     * 
     * @return The portNumber
     */
    @JsonProperty("portNumber")
    public Integer getPortNumber() {
        return portNumber;
    }

    /**
     * 
     * (Required)
     * 
     * @param portNumber
     *            The portNumber
     */
    @JsonProperty("portNumber")
    public void setPortNumber(final Integer portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * 
     * @return The pairedPortName
     */
    @JsonProperty("pairedPortName")
    public String getPairedPortName() {
        return pairedPortName;
    }

    /**
     * 
     * @param pairedPortName
     *            The pairedPortName
     */
    @JsonProperty("pairedPortName")
    public void setPairedPortName(final String pairedPortName) {
        this.pairedPortName = pairedPortName;
    }

    /**
     * 
     * (Required)
     * 
     * @return The portCapability
     */
    @JsonProperty("portCapability")
    public List<String> getPortCapability() {
        return portCapability;
    }

    /**
     * 
     * (Required)
     * 
     * @param portCapability
     *            The portCapability
     */
    @JsonProperty("portCapability")
    public void setPortCapability(final List<String> portCapability) {
        this.portCapability = portCapability;
    }

    /**
     * 
     * (Required)
     * 
     * @return The downlinkCapable
     */
    @JsonProperty("downlinkCapable")
    public Boolean getDownlinkCapable() {
        return downlinkCapable;
    }

    /**
     * 
     * (Required)
     * 
     * @param downlinkCapable
     *            The downlinkCapable
     */
    @JsonProperty("downlinkCapable")
    public void setDownlinkCapable(final Boolean downlinkCapable) {
        this.downlinkCapable = downlinkCapable;
    }

    /**
     * 
     * (Required)
     * 
     * @return The uplinkCapable
     */
    @JsonProperty("uplinkCapable")
    public Boolean getUplinkCapable() {
        return uplinkCapable;
    }

    /**
     * 
     * (Required)
     * 
     * @param uplinkCapable
     *            The uplinkCapable
     */
    @JsonProperty("uplinkCapable")
    public void setUplinkCapable(final Boolean uplinkCapable) {
        this.uplinkCapable = uplinkCapable;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(portName).append(portNumber).append(pairedPortName).append(portCapability)
                .append(downlinkCapable).append(uplinkCapable).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PortInfo) == false) {
            return false;
        }
        final PortInfo rhs = ((PortInfo) other);
        return new EqualsBuilder().append(portName, rhs.portName).append(portNumber, rhs.portNumber)
                .append(pairedPortName, rhs.pairedPortName).append(portCapability, rhs.portCapability)
                .append(downlinkCapable, rhs.downlinkCapable).append(uplinkCapable, rhs.uplinkCapable).isEquals();
    }

}
