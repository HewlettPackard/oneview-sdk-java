/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
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
@JsonPropertyOrder({
        "manageBios", "overriddenSettings"
})
public class Bios implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @JsonProperty("manageBios")
    private Boolean manageBios;
    @JsonProperty("overriddenSettings")
    private List<OverriddenSetting> overriddenSettings = new ArrayList<OverriddenSetting>();

    /**
     * 
     * @return The manageBios
     */
    @JsonProperty("manageBios")
    public Boolean getManageBios()
    {
        return manageBios;
    }

    /**
     * 
     * @param manageBios
     *        The manageBios
     */
    @JsonProperty("manageBios")
    public void setManageBios(final Boolean manageBios)
    {
        this.manageBios = manageBios;
    }

    /**
     * 
     * @return The overriddenSettings
     */
    @JsonProperty("overriddenSettings")
    public List<OverriddenSetting> getOverriddenSettings()
    {
        return overriddenSettings;
    }

    /**
     * 
     * @param overriddenSettings
     *        The overriddenSettings
     */
    @JsonProperty("overriddenSettings")
    public void setOverriddenSettings(final List<OverriddenSetting> overriddenSettings)
    {
        this.overriddenSettings = overriddenSettings;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(manageBios)
                .append(overriddenSettings).toHashCode();
    }

    @Override
    public boolean equals(final Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof Bios) == false)
        {
            return false;
        }
        final Bios rhs = ((Bios) other);
        return new EqualsBuilder().append(manageBios, rhs.manageBios)
                .append(overriddenSettings, rhs.overriddenSettings).isEquals();
    }

}
