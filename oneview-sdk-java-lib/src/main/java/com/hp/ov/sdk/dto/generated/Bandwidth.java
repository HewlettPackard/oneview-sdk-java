/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "maximumBandwidth", "typicalBandwidth" })
public class Bandwidth implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("maximumBandwidth")
    private Double maximumBandwidth;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("typicalBandwidth")
    private Double typicalBandwidth;

    /**
     * 
     * (Required)
     * 
     * @return The maximumBandwidth
     */
    @JsonProperty("maximumBandwidth")
    public Double getMaximumBandwidth() {
        return maximumBandwidth;
    }

    /**
     * 
     * (Required)
     * 
     * @param maximumBandwidth
     *            The maximumBandwidth
     */
    @JsonProperty("maximumBandwidth")
    public void setMaximumBandwidth(final Double maximumBandwidth) {
        this.maximumBandwidth = maximumBandwidth;
    }

    /**
     * 
     * (Required)
     * 
     * @return The typicalBandwidth
     */
    @JsonProperty("typicalBandwidth")
    public Double getTypicalBandwidth() {
        return typicalBandwidth;
    }

    /**
     * 
     * (Required)
     * 
     * @param typicalBandwidth
     *            The typicalBandwidth
     */
    @JsonProperty("typicalBandwidth")
    public void setTypicalBandwidth(final Double typicalBandwidth) {
        this.typicalBandwidth = typicalBandwidth;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(maximumBandwidth).append(typicalBandwidth).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Bandwidth) == false) {
            return false;
        }
        final Bandwidth rhs = ((Bandwidth) other);
        return new EqualsBuilder().append(maximumBandwidth, rhs.maximumBandwidth).append(typicalBandwidth, rhs.typicalBandwidth)
                .isEquals();
    }

}
