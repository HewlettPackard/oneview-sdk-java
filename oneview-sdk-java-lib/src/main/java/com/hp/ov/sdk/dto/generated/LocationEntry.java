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
@JsonPropertyOrder({ "relativeValue", "value", "type" })
public class LocationEntry implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("relativeValue")
    private Integer relativeValue;
    @JsonProperty("value")
    private String value;
    @JsonProperty("type")
    private LocationEntry.Type type;

    /**
     * 
     * @return The value
     */
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *            The value
     */
    @JsonProperty("value")
    public void setValue(final String value) {
        this.value = value;
    }

    /**
     * 
     * @return The type
     */
    @JsonProperty("type")
    public LocationEntry.Type getType() {
        return type;
    }

    /**
     * 
     * @param type
     *            The type
     */
    @JsonProperty("type")
    public void setType(final LocationEntry.Type type) {
        this.type = type;
    }

    /**
     * @return the relativeValue
     */
    public Integer getRelativeValue() {
        return relativeValue;
    }

    /**
     * @param relativeValue
     *            the relativeValue to set
     */
    public void setRelativeValue(final Integer relativeValue) {
        this.relativeValue = relativeValue;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(relativeValue).append(value).append(type).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LocationEntry) == false) {
            return false;
        }
        final LocationEntry rhs = ((LocationEntry) other);
        return new EqualsBuilder().append(relativeValue, rhs.relativeValue).append(value, rhs.value).append(type, rhs.type)
                .isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum Type {

        Port("Port"), Bay("Bay"), Enclosure("Enclosure");
        private final String value;
        private static Map<String, LocationEntry.Type> constants = new HashMap<String, LocationEntry.Type>();

        static {
            for (final LocationEntry.Type c : values()) {
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
        public static LocationEntry.Type fromValue(final String value) {
            final LocationEntry.Type constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
