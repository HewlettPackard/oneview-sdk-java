/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
@JsonPropertyOrder({ "logicalLocation", "desiredSpeed" })
public class LogicalPortConfigInfo implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("logicalLocation")
    private LogicalLocation logicalLocation;
    @JsonProperty("desiredSpeed")
    private LogicalPortConfigInfo.DesiredSpeed desiredSpeed;

    /**
     * 
     * @return The logicalLocation
     */
    @JsonProperty("logicalLocation")
    public LogicalLocation getLogicalLocation() {
        return logicalLocation;
    }

    /**
     * 
     * @param logicalLocation
     *            The logicalLocation
     */
    @JsonProperty("logicalLocation")
    public void setLogicalLocation(final LogicalLocation logicalLocation) {
        this.logicalLocation = logicalLocation;
    }

    /**
     * 
     * @return The desiredSpeed
     */
    @JsonProperty("desiredSpeed")
    public LogicalPortConfigInfo.DesiredSpeed getDesiredSpeed() {
        return desiredSpeed;
    }

    /**
     * 
     * @param desiredSpeed
     *            The desiredSpeed
     */
    @JsonProperty("desiredSpeed")
    public void setDesiredSpeed(final LogicalPortConfigInfo.DesiredSpeed desiredSpeed) {
        this.desiredSpeed = desiredSpeed;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(logicalLocation).append(desiredSpeed).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LogicalPortConfigInfo) == false) {
            return false;
        }
        final LogicalPortConfigInfo rhs = ((LogicalPortConfigInfo) other);
        return new EqualsBuilder().append(logicalLocation, rhs.logicalLocation).append(desiredSpeed, rhs.desiredSpeed).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum DesiredSpeed {

        Speed0M("Speed0M"), Speed1M("Speed1M"), Speed10M("Speed10M"), Speed100M("Speed100M"), Speed1G("Speed1G"), Speed2G("Speed2G"), Speed2_5G(
                "Speed2_5G"), Speed4G("Speed4G"), Speed8G("Speed8G"), Speed10G("Speed10G"), Auto("Auto"), Speed20G("Speed20G"), Speed40G(
                "Speed40G"), Unknown("Unknown");
        private final String value;
        private static Map<String, LogicalPortConfigInfo.DesiredSpeed> constants = new HashMap<String, LogicalPortConfigInfo.DesiredSpeed>();

        static {
            for (final LogicalPortConfigInfo.DesiredSpeed c : values()) {
                constants.put(c.value, c);
            }
        }

        private DesiredSpeed(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static LogicalPortConfigInfo.DesiredSpeed fromValue(final String value) {
            final LogicalPortConfigInfo.DesiredSpeed constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
