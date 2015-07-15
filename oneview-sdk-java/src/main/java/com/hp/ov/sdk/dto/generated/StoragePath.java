/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "storageTargets", "storageTargetType", "connectionId",
        "isEnabled", "status"
})
public class StoragePath implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("storageTargets")
    private List<String> storageTargets = new ArrayList<String>();
    @JsonProperty("storageTargetType")
    private StoragePath.StorageTargetType storageTargetType;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("connectionId")
    private Integer connectionId;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("isEnabled")
    private Boolean isEnabled;
    @JsonProperty("status")
    private String status;

    /**
     * 
     * @return The storageTargets
     */
    @JsonProperty("storageTargets")
    public List<String> getStorageTargets()
    {
        return storageTargets;
    }

    /**
     * 
     * @param storageTargets
     *        The storageTargets
     */
    @JsonProperty("storageTargets")
    public void setStorageTargets(final List<String> storageTargets)
    {
        this.storageTargets = storageTargets;
    }

    /**
     * 
     * @return The storageTargetType
     */
    @JsonProperty("storageTargetType")
    public StoragePath.StorageTargetType getStorageTargetType()
    {
        return storageTargetType;
    }

    /**
     * 
     * @param storageTargetType
     *        The storageTargetType
     */
    @JsonProperty("storageTargetType")
    public void setStorageTargetType(
            final StoragePath.StorageTargetType storageTargetType)
    {
        this.storageTargetType = storageTargetType;
    }

    /**
     * 
     * (Required)
     * 
     * @return The connectionId
     */
    @JsonProperty("connectionId")
    public Integer getConnectionId()
    {
        return connectionId;
    }

    /**
     * 
     * (Required)
     * 
     * @param connectionId
     *        The connectionId
     */
    @JsonProperty("connectionId")
    public void setConnectionId(final Integer connectionId)
    {
        this.connectionId = connectionId;
    }

    /**
     * 
     * (Required)
     * 
     * @return The isEnabled
     */
    @JsonProperty("isEnabled")
    public Boolean getIsEnabled()
    {
        return isEnabled;
    }

    /**
     * 
     * (Required)
     * 
     * @param isEnabled
     *        The isEnabled
     */
    @JsonProperty("isEnabled")
    public void setIsEnabled(final Boolean isEnabled)
    {
        this.isEnabled = isEnabled;
    }

    /**
     * 
     * @return The status
     */
    @JsonProperty("status")
    public String getStatus()
    {
        return status;
    }

    /**
     * 
     * @param status
     *        The status
     */
    @JsonProperty("status")
    public void setStatus(final String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(storageTargets)
                .append(storageTargetType).append(connectionId)
                .append(isEnabled).append(status).toHashCode();
    }

    @Override
    public boolean equals(final Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof StoragePath) == false)
        {
            return false;
        }
        final StoragePath rhs = ((StoragePath) other);
        return new EqualsBuilder().append(storageTargets, rhs.storageTargets)
                .append(storageTargetType, rhs.storageTargetType)
                .append(connectionId, rhs.connectionId)
                .append(isEnabled, rhs.isEnabled).append(status, rhs.status)
                .isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum StorageTargetType
    {

        Auto ("Auto"),
        TargetPorts ("TargetPorts");
        private final String value;
        private static Map<String, StoragePath.StorageTargetType> constants = new HashMap<String, StoragePath.StorageTargetType>();

        static
        {
            for (final StoragePath.StorageTargetType c : values())
            {
                constants.put(c.value, c);
            }
        }

        private StorageTargetType(final String value)
        {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString()
        {
            return this.value;
        }

        @JsonCreator
        public static StoragePath.StorageTargetType fromValue(final String value)
        {
            final StoragePath.StorageTargetType constant = constants.get(value);
            if (constant == null)
            {
                throw new IllegalArgumentException(value);
            }
            else
            {
                return constant;
            }
        }

    }

}
