/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
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
@JsonPropertyOrder({
        "bayNumber", "interconnectUri", "logicalInterconnectUri"
})
public class InterconnectBay implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("bayNumber")
    private Integer bayNumber;
    @JsonProperty("interconnectUri")
    private String interconnectUri;
    @JsonProperty("logicalInterconnectUri")
    private String logicalInterconnectUri;

    /**
     * 
     * @return The bayNumber
     */
    @JsonProperty("bayNumber")
    public Integer getBayNumber()
    {
        return bayNumber;
    }

    /**
     * 
     * @param bayNumber
     *        The bayNumber
     */
    @JsonProperty("bayNumber")
    public void setBayNumber(final Integer bayNumber)
    {
        this.bayNumber = bayNumber;
    }

    /**
     * 
     * @return The interconnectUri
     */
    @JsonProperty("interconnectUri")
    public String getInterconnectUri()
    {
        return interconnectUri;
    }

    /**
     * 
     * @param interconnectUri
     *        The interconnectUri
     */
    @JsonProperty("interconnectUri")
    public void setInterconnectUri(final String interconnectUri)
    {
        this.interconnectUri = interconnectUri;
    }

    /**
     * 
     * @return The logicalInterconnectUri
     */
    @JsonProperty("logicalInterconnectUri")
    public String getLogicalInterconnectUri()
    {
        return logicalInterconnectUri;
    }

    /**
     * 
     * @param logicalInterconnectUri
     *        The logicalInterconnectUri
     */
    @JsonProperty("logicalInterconnectUri")
    public void setLogicalInterconnectUri(final String logicalInterconnectUri)
    {
        this.logicalInterconnectUri = logicalInterconnectUri;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(bayNumber).append(interconnectUri)
                .append(logicalInterconnectUri).toHashCode();
    }

    @Override
    public boolean equals(final Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof InterconnectBay) == false)
        {
            return false;
        }
        final InterconnectBay rhs = ((InterconnectBay) other);
        return new EqualsBuilder().append(bayNumber, rhs.bayNumber)
                .append(interconnectUri, rhs.interconnectUri)
                .append(logicalInterconnectUri, rhs.logicalInterconnectUri)
                .isEquals();
    }

}
