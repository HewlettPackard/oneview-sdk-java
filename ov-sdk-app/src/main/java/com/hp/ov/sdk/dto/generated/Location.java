/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "locationEntries"
})
public class Location implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("locationEntries")
    private List<LocationEntry> locationEntries = new ArrayList<LocationEntry>();

    /**
     * 
     * @return The locationEntries
     */
    @JsonProperty("locationEntries")
    public List<LocationEntry> getLocationEntries()
    {
        return locationEntries;
    }

    /**
     * 
     * @param locationEntries
     *        The locationEntries
     */
    @JsonProperty("locationEntries")
    public void setLocationEntries(List<LocationEntry> locationEntries)
    {
        this.locationEntries = locationEntries;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(locationEntries).toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof Location) == false)
        {
            return false;
        }
        Location rhs = ((Location) other);
        return new EqualsBuilder().append(locationEntries, rhs.locationEntries)
                .isEquals();
    }

}
