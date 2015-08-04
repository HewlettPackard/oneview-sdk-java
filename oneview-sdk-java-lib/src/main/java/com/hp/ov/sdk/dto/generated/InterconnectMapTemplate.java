/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
@JsonPropertyOrder({ "interconnectMapEntryTemplates" })
public class InterconnectMapTemplate implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("interconnectMapEntryTemplates")
    private List<InterconnectMapEntryTemplate> interconnectMapEntryTemplates = new ArrayList<InterconnectMapEntryTemplate>();

    /**
     * 
     * @return The interconnectMapEntryTemplates
     */
    @JsonProperty("interconnectMapEntryTemplates")
    public List<InterconnectMapEntryTemplate> getInterconnectMapEntryTemplates() {
        return interconnectMapEntryTemplates;
    }

    /**
     * 
     * @param interconnectMapEntryTemplates
     *            The interconnectMapEntryTemplates
     */
    @JsonProperty("interconnectMapEntryTemplates")
    public void setInterconnectMapEntryTemplates(final List<InterconnectMapEntryTemplate> interconnectMapEntryTemplates) {
        this.interconnectMapEntryTemplates = interconnectMapEntryTemplates;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(interconnectMapEntryTemplates).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InterconnectMapTemplate) == false) {
            return false;
        }
        final InterconnectMapTemplate rhs = ((InterconnectMapTemplate) other);
        return new EqualsBuilder().append(interconnectMapEntryTemplates, rhs.interconnectMapEntryTemplates).isEquals();
    }

}
