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
@JsonPropertyOrder({ "trapSeverities", "enetTrapCategories", "fcTrapCategories", "vcmTrapCategories", "trapFormat",
        "trapDestination", "communityString" })
public class TrapDestination implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("trapSeverities")
    private List<String> trapSeverities = new ArrayList<String>();
    @JsonProperty("enetTrapCategories")
    private List<String> enetTrapCategories = new ArrayList<String>();
    @JsonProperty("fcTrapCategories")
    private List<String> fcTrapCategories = new ArrayList<String>();
    @JsonProperty("vcmTrapCategories")
    private List<String> vcmTrapCategories = new ArrayList<String>();
    @JsonProperty("trapFormat")
    private TrapDestination.TrapFormat trapFormat = TrapDestination.TrapFormat.fromValue("SNMPv1");
    @JsonProperty("trapDestination")
    private String trapDestination = "";
    @JsonProperty("communityString")
    private String communityString = "public";

    /**
     * 
     * @return The trapSeverities
     */
    @JsonProperty("trapSeverities")
    public List<String> getTrapSeverities() {
        return trapSeverities;
    }

    /**
     * 
     * @param trapSeverities
     *            The trapSeverities
     */
    @JsonProperty("trapSeverities")
    public void setTrapSeverities(final List<String> trapSeverities) {
        this.trapSeverities = trapSeverities;
    }

    /**
     * 
     * @return The enetTrapCategories
     */
    @JsonProperty("enetTrapCategories")
    public List<String> getEnetTrapCategories() {
        return enetTrapCategories;
    }

    /**
     * 
     * @param enetTrapCategories
     *            The enetTrapCategories
     */
    @JsonProperty("enetTrapCategories")
    public void setEnetTrapCategories(final List<String> enetTrapCategories) {
        this.enetTrapCategories = enetTrapCategories;
    }

    /**
     * 
     * @return The fcTrapCategories
     */
    @JsonProperty("fcTrapCategories")
    public List<String> getFcTrapCategories() {
        return fcTrapCategories;
    }

    /**
     * 
     * @param fcTrapCategories
     *            The fcTrapCategories
     */
    @JsonProperty("fcTrapCategories")
    public void setFcTrapCategories(final List<String> fcTrapCategories) {
        this.fcTrapCategories = fcTrapCategories;
    }

    /**
     * 
     * @return The vcmTrapCategories
     */
    @JsonProperty("vcmTrapCategories")
    public List<String> getVcmTrapCategories() {
        return vcmTrapCategories;
    }

    /**
     * 
     * @param vcmTrapCategories
     *            The vcmTrapCategories
     */
    @JsonProperty("vcmTrapCategories")
    public void setVcmTrapCategories(final List<String> vcmTrapCategories) {
        this.vcmTrapCategories = vcmTrapCategories;
    }

    /**
     * 
     * @return The trapFormat
     */
    @JsonProperty("trapFormat")
    public TrapDestination.TrapFormat getTrapFormat() {
        return trapFormat;
    }

    /**
     * 
     * @param trapFormat
     *            The trapFormat
     */
    @JsonProperty("trapFormat")
    public void setTrapFormat(final TrapDestination.TrapFormat trapFormat) {
        this.trapFormat = trapFormat;
    }

    /**
     * 
     * @return The trapDestination
     */
    @JsonProperty("trapDestination")
    public String getTrapDestination() {
        return trapDestination;
    }

    /**
     * 
     * @param trapDestination
     *            The trapDestination
     */
    @JsonProperty("trapDestination")
    public void setTrapDestination(final String trapDestination) {
        this.trapDestination = trapDestination;
    }

    /**
     * 
     * @return The communityString
     */
    @JsonProperty("communityString")
    public String getCommunityString() {
        return communityString;
    }

    /**
     * 
     * @param communityString
     *            The communityString
     */
    @JsonProperty("communityString")
    public void setCommunityString(final String communityString) {
        this.communityString = communityString;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(trapSeverities).append(enetTrapCategories).append(fcTrapCategories)
                .append(vcmTrapCategories).append(trapFormat).append(trapDestination).append(communityString).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TrapDestination) == false) {
            return false;
        }
        final TrapDestination rhs = ((TrapDestination) other);
        return new EqualsBuilder().append(trapSeverities, rhs.trapSeverities).append(enetTrapCategories, rhs.enetTrapCategories)
                .append(fcTrapCategories, rhs.fcTrapCategories).append(vcmTrapCategories, rhs.vcmTrapCategories)
                .append(trapFormat, rhs.trapFormat).append(trapDestination, rhs.trapDestination)
                .append(communityString, rhs.communityString).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum TrapFormat {

        SNMPv1("SNMPv1"), SNMPv2("SNMPv2");
        private final String value;
        private static Map<String, TrapDestination.TrapFormat> constants = new HashMap<String, TrapDestination.TrapFormat>();

        static {
            for (final TrapDestination.TrapFormat c : values()) {
                constants.put(c.value, c);
            }
        }

        private TrapFormat(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static TrapDestination.TrapFormat fromValue(final String value) {
            final TrapDestination.TrapFormat constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
