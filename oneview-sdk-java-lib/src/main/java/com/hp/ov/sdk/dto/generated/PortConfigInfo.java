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
@JsonPropertyOrder({ "desiredSpeed", "portUri", "location" })
public class PortConfigInfo implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("desiredSpeed")
    private PortConfigInfo.DesiredSpeed desiredSpeed;
    @JsonProperty("portUri")
    private String portUri;
    @JsonProperty("location")
    private Location location;

    /**
     * 
     * @return The desiredSpeed
     */
    @JsonProperty("desiredSpeed")
    public PortConfigInfo.DesiredSpeed getDesiredSpeed() {
        return desiredSpeed;
    }

    /**
     * 
     * @param desiredSpeed
     *            The desiredSpeed
     */
    @JsonProperty("desiredSpeed")
    public void setDesiredSpeed(final PortConfigInfo.DesiredSpeed desiredSpeed) {
        this.desiredSpeed = desiredSpeed;
    }

    /**
     * 
     * @return The portUri
     */
    @JsonProperty("portUri")
    public String getPortUri() {
        return portUri;
    }

    /**
     * 
     * @param portUri
     *            The portUri
     */
    @JsonProperty("portUri")
    public void setPortUri(final String portUri) {
        this.portUri = portUri;
    }

    /**
     * 
     * @return The location
     */
    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    /**
     * 
     * @param location
     *            The location
     */
    @JsonProperty("location")
    public void setLocation(final Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(desiredSpeed).append(portUri).append(location).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PortConfigInfo) == false) {
            return false;
        }
        final PortConfigInfo rhs = ((PortConfigInfo) other);
        return new EqualsBuilder().append(desiredSpeed, rhs.desiredSpeed).append(portUri, rhs.portUri)
                .append(location, rhs.location).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum DesiredSpeed {

        Speed0M("Speed0M"), Speed1M("Speed1M"), Speed10M("Speed10M"), Speed100M("Speed100M"), Speed1G("Speed1G"), Speed2G("Speed2G"), Speed2_5G(
                "Speed2_5G"), Speed4G("Speed4G"), Speed8G("Speed8G"), Speed10G("Speed10G"), Auto("Auto"), Speed20G("Speed20G"), Speed40G(
                "Speed40G"), Unknown("Unknown");
        private final String value;
        private static Map<String, PortConfigInfo.DesiredSpeed> constants = new HashMap<String, PortConfigInfo.DesiredSpeed>();

        static {
            for (final PortConfigInfo.DesiredSpeed c : values()) {
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
        public static PortConfigInfo.DesiredSpeed fromValue(final String value) {
            final PortConfigInfo.DesiredSpeed constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
