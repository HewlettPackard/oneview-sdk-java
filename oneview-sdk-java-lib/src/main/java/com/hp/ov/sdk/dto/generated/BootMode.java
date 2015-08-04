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
@JsonPropertyOrder({ "manageMode", "pxeBootPolicy", "mode" })
public class BootMode implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @JsonProperty("manageMode")
    private Boolean manageMode = false;
    @JsonProperty("pxeBootPolicy")
    private String pxeBootPolicy;
    @JsonProperty("mode")
    private String mode;

    /**
     * 
     * @return The manageMode
     */
    @JsonProperty("manageMode")
    public Boolean getManageMode() {
        return manageMode;
    }

    /**
     * 
     * @param manageMode
     *            The manageMode
     */
    @JsonProperty("manageMode")
    public void setManageMode(final Boolean manageMode) {
        this.manageMode = manageMode;
    }

    /**
     * 
     * @return The pxeBootPolicy
     */
    @JsonProperty("pxeBootPolicy")
    public String getPxeBootPolicy() {
        return pxeBootPolicy;
    }

    /**
     * 
     * @param pxeBootPolicy
     *            The pxeBootPolicy
     */
    @JsonProperty("pxeBootPolicy")
    public void setPxeBootPolicy(final String pxeBootPolicy) {
        this.pxeBootPolicy = pxeBootPolicy;
    }

    /**
     * 
     * @return The mode
     */
    @JsonProperty("mode")
    public String getMode() {
        return mode;
    }

    /**
     * 
     * @param mode
     *            The mode
     */
    @JsonProperty("mode")
    public void setMode(final String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(manageMode).append(pxeBootPolicy).append(mode).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BootMode) == false) {
            return false;
        }
        final BootMode rhs = ((BootMode) other);
        return new EqualsBuilder().append(manageMode, rhs.manageMode).append(pxeBootPolicy, rhs.pxeBootPolicy)
                .append(mode, rhs.mode).isEquals();
    }

}
