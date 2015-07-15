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
        "logicalDrives", "manageLocalStorage", "initialize"
})
public class LocalStorage implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @JsonProperty("logicalDrives")
    private List<LogicalDrife> logicalDrives = new ArrayList<LogicalDrife>();
    @JsonProperty("manageLocalStorage")
    private Boolean manageLocalStorage;
    @JsonProperty("initialize")
    private Boolean initialize;

    /**
     * 
     * @return The logicalDrives
     */
    @JsonProperty("logicalDrives")
    public List<LogicalDrife> getLogicalDrives()
    {
        return logicalDrives;
    }

    /**
     * 
     * @param logicalDrives
     *        The logicalDrives
     */
    @JsonProperty("logicalDrives")
    public void setLogicalDrives(final List<LogicalDrife> logicalDrives)
    {
        this.logicalDrives = logicalDrives;
    }

    /**
     * 
     * @return The manageLocalStorage
     */
    @JsonProperty("manageLocalStorage")
    public Boolean getManageLocalStorage()
    {
        return manageLocalStorage;
    }

    /**
     * 
     * @param manageLocalStorage
     *        The manageLocalStorage
     */
    @JsonProperty("manageLocalStorage")
    public void setManageLocalStorage(final Boolean manageLocalStorage)
    {
        this.manageLocalStorage = manageLocalStorage;
    }

    /**
     * 
     * @return The initialize
     */
    @JsonProperty("initialize")
    public Boolean getInitialize()
    {
        return initialize;
    }

    /**
     * 
     * @param initialize
     *        The initialize
     */
    @JsonProperty("initialize")
    public void setInitialize(final Boolean initialize)
    {
        this.initialize = initialize;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(logicalDrives)
                .append(manageLocalStorage).append(initialize).toHashCode();
    }

    @Override
    public boolean equals(final Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof LocalStorage) == false)
        {
            return false;
        }
        final LocalStorage rhs = ((LocalStorage) other);
        return new EqualsBuilder().append(logicalDrives, rhs.logicalDrives)
                .append(manageLocalStorage, rhs.manageLocalStorage)
                .append(initialize, rhs.initialize).isEquals();
    }

}
