/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "ports" })
public class ProfilePorts implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("ports")
    private List<Object> ports = new ArrayList<Object>();

    /**
     * 
     * @return The ports
     */
    @JsonProperty("ports")
    public List<Object> getPorts() {
        return ports;
    }

    /**
     * 
     * @param ports
     *            The ports
     */
    @JsonProperty("ports")
    public void setPorts(final List<Object> ports) {
        this.ports = ports;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(ports).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProfilePorts) == false) {
            return false;
        }
        final ProfilePorts rhs = ((ProfilePorts) other);
        return new EqualsBuilder().append(ports, rhs.ports).isEquals();
    }

}
