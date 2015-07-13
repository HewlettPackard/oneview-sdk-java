/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;

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
        "id", "value"
})
public class OverriddenSetting implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    private String id;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("value")
    private String value;

    /**
     * 
     * (Required)
     * 
     * @return The id
     */
    @JsonProperty("id")
    public String getId()
    {
        return id;
    }

    /**
     * 
     * (Required)
     * 
     * @param id
     *        The id
     */
    @JsonProperty("id")
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * 
     * (Required)
     * 
     * @return The value
     */
    @JsonProperty("value")
    public String getValue()
    {
        return value;
    }

    /**
     * 
     * (Required)
     * 
     * @param value
     *        The value
     */
    @JsonProperty("value")
    public void setValue(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(id).append(value).toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof OverriddenSetting) == false)
        {
            return false;
        }
        OverriddenSetting rhs = ((OverriddenSetting) other);
        return new EqualsBuilder().append(id, rhs.id).append(value, rhs.value)
                .isEquals();
    }

}
