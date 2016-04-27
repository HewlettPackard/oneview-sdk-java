/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class DeviceSlot implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String deviceName;
    private DeviceSlot.Location location;
    private Integer oaSlotNumber;
    private List<PhysicalPort> physicalPorts = new ArrayList<PhysicalPort>();
    private Integer slotNumber;

    /**
     * 
     * @return The deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 
     * @param deviceName
     *            The deviceName
     */
    public void setDeviceName(final String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * 
     * @return The location
     */
    public DeviceSlot.Location getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *            The location
     */
    public void setLocation(final DeviceSlot.Location location) {
        this.location = location;
    }

    /**
     * 
     * @return The oaSlotNumber
     */
    public Integer getOaSlotNumber() {
        return oaSlotNumber;
    }

    /**
     * 
     * @param oaSlotNumber
     *            The oaSlotNumber
     */
    public void setOaSlotNumber(final Integer oaSlotNumber) {
        this.oaSlotNumber = oaSlotNumber;
    }

    /**
     * 
     * @return The physicalPorts
     */
    public List<PhysicalPort> getPhysicalPorts() {
        return physicalPorts;
    }

    /**
     * 
     * @param physicalPorts
     *            The physicalPorts
     */
    public void setPhysicalPorts(final List<PhysicalPort> physicalPorts) {
        this.physicalPorts = physicalPorts;
    }

    /**
     * 
     * @return The slotNumber
     */
    public Integer getSlotNumber() {
        return slotNumber;
    }

    /**
     * 
     * @param slotNumber
     *            The slotNumber
     */
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

        @Override
        public String toString() {
            return this.value;
        }

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
