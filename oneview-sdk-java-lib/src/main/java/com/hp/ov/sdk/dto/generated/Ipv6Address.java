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
@JsonPropertyOrder({ "address", "type" })
public class Ipv6Address implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("address")
    private String address;
    @JsonProperty("type")
    private Ipv6Address.Type type;

    /**
     * 
     * @return The address
     */
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *            The address
     */
    @JsonProperty("address")
    public void setAddress(final String address) {
        this.address = address;
    }

    /**
     * 
     * @return The type
     */
    @JsonProperty("type")
    public Ipv6Address.Type getType() {
        return type;
    }

    /**
     * 
     * @param type
     *            The type
     */
    @JsonProperty("type")
    public void setType(final Ipv6Address.Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(address).append(type).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Ipv6Address) == false) {
            return false;
        }
        final Ipv6Address rhs = ((Ipv6Address) other);
        return new EqualsBuilder().append(address, rhs.address).append(type, rhs.type).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum Type {

        LinkLocal("LinkLocal"), RouterAdv("RouterAdv"), NotSet("NotSet"), Unknown("Unknown"), Static("Static");
        private final String value;
        private static Map<String, Ipv6Address.Type> constants = new HashMap<String, Ipv6Address.Type>();

        static {
            for (final Ipv6Address.Type c : values()) {
                constants.put(c.value, c);
            }
        }

        private Type(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Ipv6Address.Type fromValue(final String value) {
            final Ipv6Address.Type constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
