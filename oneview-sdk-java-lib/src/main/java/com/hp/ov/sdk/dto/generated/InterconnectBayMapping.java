/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
@JsonPropertyOrder({ "interconnectBay", "logicalInterconnectGroupUri" })
public class InterconnectBayMapping implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("interconnectBay")
    private Integer interconnectBay;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("logicalInterconnectGroupUri")
    private String logicalInterconnectGroupUri;

    /**
     * 
     * (Required)
     * 
     * @return The interconnectBay
     */
    @JsonProperty("interconnectBay")
    public Integer getInterconnectBay() {
        return interconnectBay;
    }

    /**
     * 
     * (Required)
     * 
     * @param interconnectBay
     *            The interconnectBay
     */
    @JsonProperty("interconnectBay")
    public void setInterconnectBay(final Integer interconnectBay) {
        this.interconnectBay = interconnectBay;
    }

    /**
     * 
     * (Required)
     * 
     * @return The logicalInterconnectGroupUri
     */
    @JsonProperty("logicalInterconnectGroupUri")
    public String getLogicalInterconnectGroupUri() {
        return logicalInterconnectGroupUri;
    }

    /**
     * 
     * (Required)
     * 
     * @param logicalInterconnectGroupUri
     *            The logicalInterconnectGroupUri
     */
    @JsonProperty("logicalInterconnectGroupUri")
    public void setLogicalInterconnectGroupUri(final String logicalInterconnectGroupUri) {
        this.logicalInterconnectGroupUri = logicalInterconnectGroupUri;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(interconnectBay).append(logicalInterconnectGroupUri).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InterconnectBayMapping) == false) {
            return false;
        }
        final InterconnectBayMapping rhs = ((InterconnectBayMapping) other);
        return new EqualsBuilder().append(interconnectBay, rhs.interconnectBay)
                .append(logicalInterconnectGroupUri, rhs.logicalInterconnectGroupUri).isEquals();
    }

}
