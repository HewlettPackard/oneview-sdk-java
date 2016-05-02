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
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Since;

public class PowerSupplyBay implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer bayNumber;
    @Since(200)
    private PowerSupplyBay.ChangeState changeState;
    private PowerSupplyBay.DevicePresence devicePresence;
    private PowerSupplyBay.Status status;
    private String model;
    private String serialNumber;
    private String partNumber;
    private String sparePartNumber;

    /**
     *
     * @return The bayNumber
     */
    public Integer getBayNumber() {
        return bayNumber;
    }

    /**
     *
     * @param bayNumber
     *            The bayNumber
     */
    public void setBayNumber(final Integer bayNumber) {
        this.bayNumber = bayNumber;
    }

    /**
     *
     * @return The devicePresence
     */
    public PowerSupplyBay.DevicePresence getDevicePresence() {
        return devicePresence;
    }

    /**
     *
     * @param devicePresence
     *            The devicePresence
     */
    public void setDevicePresence(final PowerSupplyBay.DevicePresence devicePresence) {
        this.devicePresence = devicePresence;
    }

    /**
     *
     * @return The status
     */
    public PowerSupplyBay.Status getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *            The status
     */
    public void setStatus(final PowerSupplyBay.Status status) {
        this.status = status;
    }

    /**
     *
     * @return The model
     */
    public String getModel() {
        return model;
    }

    /**
     *
     * @param model
     *            The model
     */
    public void setModel(final String model) {
        this.model = model;
    }

    /**
     *
     * @return The serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     *
     * @param serialNumber
     *            The serialNumber
     */
    public void setSerialNumber(final String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     *
     * @return The partNumber
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     *
     * @param partNumber
     *            The partNumber
     */
    public void setPartNumber(final String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     *
     * @return The sparePartNumber
     */
    public String getSparePartNumber() {
        return sparePartNumber;
    }

    /**
     *
     * @param sparePartNumber
     *            The sparePartNumber
     */
    public void setSparePartNumber(final String sparePartNumber) {
        this.sparePartNumber = sparePartNumber;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(bayNumber).append(devicePresence).append(status).append(model).append(serialNumber)
                .append(partNumber).append(sparePartNumber).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PowerSupplyBay) == false) {
            return false;
        }
        final PowerSupplyBay rhs = ((PowerSupplyBay) other);
        return new EqualsBuilder().append(bayNumber, rhs.bayNumber).append(devicePresence, rhs.devicePresence)
                .append(status, rhs.status).append(model, rhs.model).append(serialNumber, rhs.serialNumber)
                .append(partNumber, rhs.partNumber).append(sparePartNumber, rhs.sparePartNumber).isEquals();
    }

    public static enum DevicePresence {

        PresenceNoOp("PresenceNoOp"), PresenceUnknown("PresenceUnknown"), Absent("Absent"), Present("Present"), Subsumed("Subsumed");
        private final String value;
        private static Map<String, PowerSupplyBay.DevicePresence> constants = new HashMap<String, PowerSupplyBay.DevicePresence>();

        static {
            for (final PowerSupplyBay.DevicePresence c : values()) {
                constants.put(c.value, c);
            }
        }

        private DevicePresence(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static PowerSupplyBay.DevicePresence fromValue(final String value) {
            final PowerSupplyBay.DevicePresence constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public static enum Status {

        Unknown("Unknown"), OK("OK"), Disabled("Disabled"), Warning("Warning"), Critical("Critical");
        private final String value;
        private static Map<String, PowerSupplyBay.Status> constants = new HashMap<String, PowerSupplyBay.Status>();

        static {
            for (final PowerSupplyBay.Status c : values()) {
                constants.put(c.value, c);
            }
        }

        private Status(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static PowerSupplyBay.Status fromValue(final String value) {
            final PowerSupplyBay.Status constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public static enum ChangeState {

        Insert("Insert"),
        None("None"),
        ProcessingInsert("ProcessingInsert"),
        Remove("Remove");

        private final String value;
        private static Map<String, PowerSupplyBay.ChangeState> constants = new HashMap<String, PowerSupplyBay.ChangeState>();

        static {
            for (final PowerSupplyBay.ChangeState c : values()) {
                constants.put(c.value, c);
            }
        }

        private ChangeState(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static PowerSupplyBay.ChangeState fromValue(final String value) {
            final PowerSupplyBay.ChangeState constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
