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
@JsonPropertyOrder({ "capacity", "psuId", "inputVoltage", "side", "powerType" })
public class PsuList implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * The size of the power supply in Watts.
     * 
     */
    @JsonProperty("capacity")
    private Integer capacity;
    /**
     * A small integer identifying the slot or bay number of the power supply
     * starting at one (1).
     * 
     */
    @JsonProperty("psuId")
    private Integer psuId;
    /**
     * The line voltage (input) to the power supply (sampled or nominal).
     * 
     */
    @JsonProperty("inputVoltage")
    private Integer inputVoltage;
    /**
     * The logical power delivery grouping of the power supply. In a known
     * non-redundant configuration, all power supplies should be considered on
     * side A. In a known AC-redundant configuration, the 2 sets of redundant
     * power supplies should be identified as side A/B groupings. For an
     * AC_REDUNDNAT C-Class BladeSystem slot 1,2,3 is on Side B (right from the
     * front), and 4,5,6 are on Side A (left from the front). If there is no
     * inherent side-ness, then the side will be Dynamic.
     * 
     */
    @JsonProperty("side")
    private PsuList.Side side;
    /**
     * The type of input power (AC or DC) of the power supply.
     * 
     */
    @JsonProperty("powerType")
    private PsuList.PowerType powerType;

    /**
     * The size of the power supply in Watts.
     * 
     * @return The capacity
     */
    @JsonProperty("capacity")
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * The size of the power supply in Watts.
     * 
     * @param capacity
     *            The capacity
     */
    @JsonProperty("capacity")
    public void setCapacity(final Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * A small integer identifying the slot or bay number of the power supply
     * starting at one (1).
     * 
     * @return The psuId
     */
    @JsonProperty("psuId")
    public Integer getPsuId() {
        return psuId;
    }

    /**
     * A small integer identifying the slot or bay number of the power supply
     * starting at one (1).
     * 
     * @param psuId
     *            The psuId
     */
    @JsonProperty("psuId")
    public void setPsuId(final Integer psuId) {
        this.psuId = psuId;
    }

    /**
     * The line voltage (input) to the power supply (sampled or nominal).
     * 
     * @return The inputVoltage
     */
    @JsonProperty("inputVoltage")
    public Integer getInputVoltage() {
        return inputVoltage;
    }

    /**
     * The line voltage (input) to the power supply (sampled or nominal).
     * 
     * @param inputVoltage
     *            The inputVoltage
     */
    @JsonProperty("inputVoltage")
    public void setInputVoltage(final Integer inputVoltage) {
        this.inputVoltage = inputVoltage;
    }

    /**
     * The logical power delivery grouping of the power supply. In a known
     * non-redundant configuration, all power supplies should be considered on
     * side A. In a known AC-redundant configuration, the 2 sets of redundant
     * power supplies should be identified as side A/B groupings. For an
     * AC_REDUNDNAT C-Class BladeSystem slot 1,2,3 is on Side B (right from the
     * front), and 4,5,6 are on Side A (left from the front). If there is no
     * inherent side-ness, then the side will be Dynamic.
     * 
     * @return The side
     */
    @JsonProperty("side")
    public PsuList.Side getSide() {
        return side;
    }

    /**
     * The logical power delivery grouping of the power supply. In a known
     * non-redundant configuration, all power supplies should be considered on
     * side A. In a known AC-redundant configuration, the 2 sets of redundant
     * power supplies should be identified as side A/B groupings. For an
     * AC_REDUNDNAT C-Class BladeSystem slot 1,2,3 is on Side B (right from the
     * front), and 4,5,6 are on Side A (left from the front). If there is no
     * inherent side-ness, then the side will be Dynamic.
     * 
     * @param side
     *            The side
     */
    @JsonProperty("side")
    public void setSide(final PsuList.Side side) {
        this.side = side;
    }

    /**
     * The type of input power (AC or DC) of the power supply.
     * 
     * @return The powerType
     */
    @JsonProperty("powerType")
    public PsuList.PowerType getPowerType() {
        return powerType;
    }

    /**
     * The type of input power (AC or DC) of the power supply.
     * 
     * @param powerType
     *            The powerType
     */
    @JsonProperty("powerType")
    public void setPowerType(final PsuList.PowerType powerType) {
        this.powerType = powerType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(capacity).append(psuId).append(inputVoltage).append(side).append(powerType)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PsuList) == false) {
            return false;
        }
        final PsuList rhs = ((PsuList) other);
        return new EqualsBuilder().append(capacity, rhs.capacity).append(psuId, rhs.psuId).append(inputVoltage, rhs.inputVoltage)
                .append(side, rhs.side).append(powerType, rhs.powerType).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum PowerType {

        Unknown("Unknown"), AC("AC"), DC("DC");
        private final String value;
        private static Map<String, PsuList.PowerType> constants = new HashMap<String, PsuList.PowerType>();

        static {
            for (final PsuList.PowerType c : values()) {
                constants.put(c.value, c);
            }
        }

        private PowerType(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static PsuList.PowerType fromValue(final String value) {
            final PsuList.PowerType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum Side {

        Unspecified("Unspecified"), A("A"), B("B"), Dynamic("Dynamic");
        private final String value;
        private static Map<String, PsuList.Side> constants = new HashMap<String, PsuList.Side>();

        static {
            for (final PsuList.Side c : values()) {
                constants.put(c.value, c);
            }
        }

        private Side(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static PsuList.Side fromValue(final String value) {
            final PsuList.Side constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
