/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "bayNumber", "devicePresence", "status", "model", "serialNumber", "partNumber", "sparePartNumber" })
public class PowerSupplyBay implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty("bayNumber")
    private Integer bayNumber;
    @JsonProperty("devicePresence")
    private PowerSupplyBay.DevicePresence devicePresence;
    @JsonProperty("status")
    private PowerSupplyBay.Status status;
    @JsonProperty("model")
    private String model;
    @JsonProperty("serialNumber")
    private String serialNumber;
    @JsonProperty("partNumber")
    private String partNumber;
    @JsonProperty("sparePartNumber")
    private String sparePartNumber;

    /**
     * 
     * @return The bayNumber
     */
    @JsonProperty("bayNumber")
    public Integer getBayNumber() {
        return bayNumber;
    }

    /**
     * 
     * @param bayNumber
     *            The bayNumber
     */
    @JsonProperty("bayNumber")
    public void setBayNumber(final Integer bayNumber) {
        this.bayNumber = bayNumber;
    }

    /**
     * 
     * @return The devicePresence
     */
    @JsonProperty("devicePresence")
    public PowerSupplyBay.DevicePresence getDevicePresence() {
        return devicePresence;
    }

    /**
     * 
     * @param devicePresence
     *            The devicePresence
     */
    @JsonProperty("devicePresence")
    public void setDevicePresence(final PowerSupplyBay.DevicePresence devicePresence) {
        this.devicePresence = devicePresence;
    }

    /**
     * 
     * @return The status
     */
    @JsonProperty("status")
    public PowerSupplyBay.Status getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *            The status
     */
    @JsonProperty("status")
    public void setStatus(final PowerSupplyBay.Status status) {
        this.status = status;
    }

    /**
     * 
     * @return The model
     */
    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    /**
     * 
     * @param model
     *            The model
     */
    @JsonProperty("model")
    public void setModel(final String model) {
        this.model = model;
    }

    /**
     * 
     * @return The serialNumber
     */
    @JsonProperty("serialNumber")
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * 
     * @param serialNumber
     *            The serialNumber
     */
    @JsonProperty("serialNumber")
    public void setSerialNumber(final String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 
     * @return The partNumber
     */
    @JsonProperty("partNumber")
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * 
     * @param partNumber
     *            The partNumber
     */
    @JsonProperty("partNumber")
    public void setPartNumber(final String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * 
     * @return The sparePartNumber
     */
    @JsonProperty("sparePartNumber")
    public String getSparePartNumber() {
        return sparePartNumber;
    }

    /**
     * 
     * @param sparePartNumber
     *            The sparePartNumber
     */
    @JsonProperty("sparePartNumber")
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

    @Generated("org.jsonschema2pojo")
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

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static PowerSupplyBay.DevicePresence fromValue(final String value) {
            final PowerSupplyBay.DevicePresence constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
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

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static PowerSupplyBay.Status fromValue(final String value) {
            final PowerSupplyBay.Status constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
