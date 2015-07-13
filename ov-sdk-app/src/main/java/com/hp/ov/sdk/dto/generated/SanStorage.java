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
        "volumeAttachments", "hostOSType", "manageSanStorage"
})
public class SanStorage implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @JsonProperty("volumeAttachments")
    private List<VolumeAttachment> volumeAttachments = new ArrayList<VolumeAttachment>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("hostOSType")
    private String hostOSType;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("manageSanStorage")
    private Boolean manageSanStorage;

    /**
     * 
     * @return The volumeAttachments
     */
    @JsonProperty("volumeAttachments")
    public List<VolumeAttachment> getVolumeAttachments()
    {
        return volumeAttachments;
    }

    /**
     * 
     * @param volumeAttachments
     *        The volumeAttachments
     */
    @JsonProperty("volumeAttachments")
    public void setVolumeAttachments(List<VolumeAttachment> volumeAttachments)
    {
        this.volumeAttachments = volumeAttachments;
    }

    /**
     * 
     * (Required)
     * 
     * @return The hostOSType
     */
    @JsonProperty("hostOSType")
    public String getHostOSType()
    {
        return hostOSType;
    }

    /**
     * 
     * (Required)
     * 
     * @param hostOSType
     *        The hostOSType
     */
    @JsonProperty("hostOSType")
    public void setHostOSType(String hostOSType)
    {
        this.hostOSType = hostOSType;
    }

    /**
     * 
     * (Required)
     * 
     * @return The manageSanStorage
     */
    @JsonProperty("manageSanStorage")
    public Boolean getManageSanStorage()
    {
        return manageSanStorage;
    }

    /**
     * 
     * (Required)
     * 
     * @param manageSanStorage
     *        The manageSanStorage
     */
    @JsonProperty("manageSanStorage")
    public void setManageSanStorage(Boolean manageSanStorage)
    {
        this.manageSanStorage = manageSanStorage;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(volumeAttachments)
                .append(hostOSType).append(manageSanStorage).toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof SanStorage) == false)
        {
            return false;
        }
        SanStorage rhs = ((SanStorage) other);
        return new EqualsBuilder()
                .append(volumeAttachments, rhs.volumeAttachments)
                .append(hostOSType, rhs.hostOSType)
                .append(manageSanStorage, rhs.manageSanStorage).isEquals();
    }

}
