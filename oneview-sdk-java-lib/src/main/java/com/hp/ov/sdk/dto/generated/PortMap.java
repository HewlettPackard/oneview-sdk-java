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

/**
 * A list of adapters/slots, their ports and attributes. This information is
 * available for blade servers but not rack servers.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "deviceSlots" })
public class PortMap implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("deviceSlots")
    private List<DeviceSlot> deviceSlots = new ArrayList<DeviceSlot>();

    /**
     * 
     * @return The deviceSlots
     */
    @JsonProperty("deviceSlots")
    public List<DeviceSlot> getDeviceSlots() {
        return deviceSlots;
    }

    /**
     * 
     * @param deviceSlots
     *            The deviceSlots
     */
    @JsonProperty("deviceSlots")
    public void setDeviceSlots(final List<DeviceSlot> deviceSlots) {
        this.deviceSlots = deviceSlots;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(deviceSlots).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PortMap) == false) {
            return false;
        }
        final PortMap rhs = ((PortMap) other);
        return new EqualsBuilder().append(deviceSlots, rhs.deviceSlots).isEquals();
    }

}
