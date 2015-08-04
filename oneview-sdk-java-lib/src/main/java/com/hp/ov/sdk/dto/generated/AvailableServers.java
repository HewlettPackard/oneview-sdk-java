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
@JsonPropertyOrder({ "serverHardwareUri", "enclosureGroupUri", "serverHardwareTypeUri", "powerState", "name" })
public class AvailableServers implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("serverHardwareUri")
    private String serverHardwareUri;
    @JsonProperty("enclosureGroupUri")
    private String enclosureGroupUri;
    @JsonProperty("serverHardwareTypeUri")
    private String serverHardwareTypeUri;
    @JsonProperty("powerState")
    private String powerState;
    @JsonProperty("name")
    private String name;

    /**
     * 
     * @return The serverHardwareUri
     */
    @JsonProperty("serverHardwareUri")
    public String getServerHardwareUri() {
        return serverHardwareUri;
    }

    /**
     * 
     * @param serverHardwareUri
     *            The serverHardwareUri
     */
    @JsonProperty("serverHardwareUri")
    public void setServerHardwareUri(final String serverHardwareUri) {
        this.serverHardwareUri = serverHardwareUri;
    }

    /**
     * 
     * @return The enclosureGroupUri
     */
    @JsonProperty("enclosureGroupUri")
    public String getEnclosureGroupUri() {
        return enclosureGroupUri;
    }

    /**
     * 
     * @param enclosureGroupUri
     *            The enclosureGroupUri
     */
    @JsonProperty("enclosureGroupUri")
    public void setEnclosureGroupUri(final String enclosureGroupUri) {
        this.enclosureGroupUri = enclosureGroupUri;
    }

    /**
     * 
     * @return The serverHardwareTypeUri
     */
    @JsonProperty("serverHardwareTypeUri")
    public String getServerHardwareTypeUri() {
        return serverHardwareTypeUri;
    }

    /**
     * 
     * @param serverHardwareTypeUri
     *            The serverHardwareTypeUri
     */
    @JsonProperty("serverHardwareTypeUri")
    public void setServerHardwareTypeUri(final String serverHardwareTypeUri) {
        this.serverHardwareTypeUri = serverHardwareTypeUri;
    }

    /**
     * 
     * @return The powerState
     */
    @JsonProperty("powerState")
    public String getPowerState() {
        return powerState;
    }

    /**
     * 
     * @param powerState
     *            The powerState
     */
    @JsonProperty("powerState")
    public void setPowerState(final String powerState) {
        this.powerState = powerState;
    }

    /**
     * 
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *            The name
     */
    @JsonProperty("name")
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(serverHardwareUri).append(enclosureGroupUri).append(serverHardwareTypeUri)
                .append(powerState).append(name).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AvailableServers) == false) {
            return false;
        }
        final AvailableServers rhs = ((AvailableServers) other);
        return new EqualsBuilder().append(serverHardwareUri, rhs.serverHardwareUri)
                .append(enclosureGroupUri, rhs.enclosureGroupUri).append(serverHardwareTypeUri, rhs.serverHardwareTypeUri)
                .append(powerState, rhs.powerState).append(name, rhs.name).isEquals();
    }

}
