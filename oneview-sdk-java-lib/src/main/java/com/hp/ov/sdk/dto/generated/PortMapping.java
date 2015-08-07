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
@JsonPropertyOrder({ "midplanePort", "interconnectBay" })
public class PortMapping implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("midplanePort")
    private Integer midplanePort;
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
     * @return The midplanePort
     */
    @JsonProperty("midplanePort")
    public Integer getMidplanePort() {
        return midplanePort;
    }

    /**
     * 
     * (Required)
     * 
     * @param midplanePort
     *            The midplanePort
     */
    @JsonProperty("midplanePort")
    public void setMidplanePort(final Integer midplanePort) {
        this.midplanePort = midplanePort;
    }

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(midplanePort).append(interconnectBay).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PortMapping) == false) {
            return false;
        }
        final PortMapping rhs = ((PortMapping) other);
        return new EqualsBuilder().append(midplanePort, rhs.midplanePort).append(interconnectBay, rhs.interconnectBay).isEquals();
    }

}
