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
@JsonPropertyOrder({ "bootable", "raidLevel" })
public class LogicalDrife implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("bootable")
    private Boolean bootable;
    @JsonProperty("raidLevel")
    private String raidLevel;

    /**
     * 
     * @return The bootable
     */
    @JsonProperty("bootable")
    public Boolean getBootable() {
        return bootable;
    }

    /**
     * 
     * @param bootable
     *            The bootable
     */
    @JsonProperty("bootable")
    public void setBootable(final Boolean bootable) {
        this.bootable = bootable;
    }

    /**
     * 
     * @return The raidLevel
     */
    @JsonProperty("raidLevel")
    public String getRaidLevel() {
        return raidLevel;
    }

    /**
     * 
     * @param raidLevel
     *            The raidLevel
     */
    @JsonProperty("raidLevel")
    public void setRaidLevel(final String raidLevel) {
        this.raidLevel = raidLevel;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(bootable).append(raidLevel).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LogicalDrife) == false) {
            return false;
        }
        final LogicalDrife rhs = ((LogicalDrife) other);
        return new EqualsBuilder().append(bootable, rhs.bootable).append(raidLevel, rhs.raidLevel).isEquals();
    }

}
