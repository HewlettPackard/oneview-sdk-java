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
@JsonPropertyOrder({ "forceInstallFirmware", "firmwareBaselineUri", "manageFirmware" })
public class Firmware implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("forceInstallFirmware")
    private Boolean forceInstallFirmware;
    @JsonProperty("firmwareBaselineUri")
    private String firmwareBaselineUri;
    @JsonProperty("manageFirmware")
    private Boolean manageFirmware;

    /**
     * 
     * @return The forceInstallFirmware
     */
    @JsonProperty("forceInstallFirmware")
    public Boolean getForceInstallFirmware() {
        return forceInstallFirmware;
    }

    /**
     * 
     * @param forceInstallFirmware
     *            The forceInstallFirmware
     */
    @JsonProperty("forceInstallFirmware")
    public void setForceInstallFirmware(final Boolean forceInstallFirmware) {
        this.forceInstallFirmware = forceInstallFirmware;
    }

    /**
     * 
     * @return The firmwareBaselineUri
     */
    @JsonProperty("firmwareBaselineUri")
    public String getFirmwareBaselineUri() {
        return firmwareBaselineUri;
    }

    /**
     * 
     * @param firmwareBaselineUri
     *            The firmwareBaselineUri
     */
    @JsonProperty("firmwareBaselineUri")
    public void setFirmwareBaselineUri(final String firmwareBaselineUri) {
        this.firmwareBaselineUri = firmwareBaselineUri;
    }

    /**
     * 
     * @return The manageFirmware
     */
    @JsonProperty("manageFirmware")
    public Boolean getManageFirmware() {
        return manageFirmware;
    }

    /**
     * 
     * @param manageFirmware
     *            The manageFirmware
     */
    @JsonProperty("manageFirmware")
    public void setManageFirmware(final Boolean manageFirmware) {
        this.manageFirmware = manageFirmware;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(forceInstallFirmware).append(firmwareBaselineUri).append(manageFirmware).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Firmware) == false) {
            return false;
        }
        final Firmware rhs = ((Firmware) other);
        return new EqualsBuilder().append(forceInstallFirmware, rhs.forceInstallFirmware)
                .append(firmwareBaselineUri, rhs.firmwareBaselineUri).append(manageFirmware, rhs.manageFirmware).isEquals();
    }

}
