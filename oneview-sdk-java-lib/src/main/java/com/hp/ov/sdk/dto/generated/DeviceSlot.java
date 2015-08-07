/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
@JsonPropertyOrder({ "deviceName", "location", "oaSlotNumber", "physicalPorts", "slotNumber" })
public class DeviceSlot implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("deviceName")
    private String deviceName;
    @JsonProperty("location")
    private DeviceSlot.Location location;
    @JsonProperty("oaSlotNumber")
    private Integer oaSlotNumber;
    @JsonProperty("physicalPorts")
    private List<PhysicalPort> physicalPorts = new ArrayList<PhysicalPort>();
    @JsonProperty("slotNumber")
    private Integer slotNumber;

    /**
     * 
     * @return The deviceName
     */
    @JsonProperty("deviceName")
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 
     * @param deviceName
     *            The deviceName
     */
    @JsonProperty("deviceName")
    public void setDeviceName(final String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * 
     * @return The location
     */
    @JsonProperty("location")
    public DeviceSlot.Location getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *            The location
     */
    @JsonProperty("location")
    public void setLocation(final DeviceSlot.Location location) {
        this.location = location;
    }

    /**
     * 
     * @return The oaSlotNumber
     */
    @JsonProperty("oaSlotNumber")
    public Integer getOaSlotNumber() {
        return oaSlotNumber;
    }

    /**
     * 
     * @param oaSlotNumber
     *            The oaSlotNumber
     */
    @JsonProperty("oaSlotNumber")
    public void setOaSlotNumber(final Integer oaSlotNumber) {
        this.oaSlotNumber = oaSlotNumber;
    }

    /**
     * 
     * @return The physicalPorts
     */
    @JsonProperty("physicalPorts")
    public List<PhysicalPort> getPhysicalPorts() {
        return physicalPorts;
    }

    /**
     * 
     * @param physicalPorts
     *            The physicalPorts
     */
    @JsonProperty("physicalPorts")
    public void setPhysicalPorts(final List<PhysicalPort> physicalPorts) {
        this.physicalPorts = physicalPorts;
    }

    /**
     * 
     * @return The slotNumber
     */
    @JsonProperty("slotNumber")
    public Integer getSlotNumber() {
        return slotNumber;
    }

    /**
     * 
     * @param slotNumber
     *            The slotNumber
     */
    @JsonProperty("slotNumber")
    public void setSlotNumber(final Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(deviceName).append(location).append(oaSlotNumber).append(physicalPorts)
                .append(slotNumber).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DeviceSlot) == false) {
            return false;
        }
        final DeviceSlot rhs = ((DeviceSlot) other);
        return new EqualsBuilder().append(deviceName, rhs.deviceName).append(location, rhs.location)
                .append(oaSlotNumber, rhs.oaSlotNumber).append(physicalPorts, rhs.physicalPorts).append(slotNumber, rhs.slotNumber)
                .isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum Location {

        Lom("Lom"), Flb("Flb"), Mezz("Mezz");
        private final String value;
        private static Map<String, DeviceSlot.Location> constants = new HashMap<String, DeviceSlot.Location>();

        static {
            for (final DeviceSlot.Location c : values()) {
                constants.put(c.value, c);
            }
        }

        private Location(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static DeviceSlot.Location fromValue(final String value) {
            final DeviceSlot.Location constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
