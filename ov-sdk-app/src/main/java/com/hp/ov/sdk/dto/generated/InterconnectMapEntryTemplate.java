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
        "permittedInterconnectTypeUri", "logicalLocation",
        "logicalDownlinkUri"
})
public class InterconnectMapEntryTemplate implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("permittedInterconnectTypeUri")
    private String permittedInterconnectTypeUri;
    @JsonProperty("logicalLocation")
    private LogicalLocation logicalLocation;
    @JsonProperty("logicalDownlinkUri")
    private String logicalDownlinkUri;

    /**
     * 
     * @return The permittedInterconnectTypeUri
     */
    @JsonProperty("permittedInterconnectTypeUri")
    public String getPermittedInterconnectTypeUri()
    {
        return permittedInterconnectTypeUri;
    }

    /**
     * 
     * @param permittedInterconnectTypeUri
     *        The permittedInterconnectTypeUri
     */
    @JsonProperty("permittedInterconnectTypeUri")
    public void setPermittedInterconnectTypeUri(
            String permittedInterconnectTypeUri)
    {
        this.permittedInterconnectTypeUri = permittedInterconnectTypeUri;
    }

    /**
     * 
     * @return The logicalLocation
     */
    @JsonProperty("logicalLocation")
    public LogicalLocation getLogicalLocation()
    {
        return logicalLocation;
    }

    /**
     * 
     * @param logicalLocation
     *        The logicalLocation
     */
    @JsonProperty("logicalLocation")
    public void setLogicalLocation(LogicalLocation logicalLocation)
    {
        this.logicalLocation = logicalLocation;
    }

    /**
     * 
     * @return The logicalDownlinkUri
     */
    @JsonProperty("logicalDownlinkUri")
    public String getLogicalDownlinkUri()
    {
        return logicalDownlinkUri;
    }

    /**
     * 
     * @param logicalDownlinkUri
     *        The logicalDownlinkUri
     */
    @JsonProperty("logicalDownlinkUri")
    public void setLogicalDownlinkUri(String logicalDownlinkUri)
    {
        this.logicalDownlinkUri = logicalDownlinkUri;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(permittedInterconnectTypeUri)
                .append(logicalLocation).append(logicalDownlinkUri)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof InterconnectMapEntryTemplate) == false)
        {
            return false;
        }
        InterconnectMapEntryTemplate rhs = ((InterconnectMapEntryTemplate) other);
        return new EqualsBuilder()
                .append(permittedInterconnectTypeUri,
                        rhs.permittedInterconnectTypeUri)
                .append(logicalLocation, rhs.logicalLocation)
                .append(logicalDownlinkUri, rhs.logicalDownlinkUri).isEquals();
    }

}
