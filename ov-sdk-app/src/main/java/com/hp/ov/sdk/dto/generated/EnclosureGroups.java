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
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "uri", "category", "name", "created", "modified", "eTag",
        "status", "state", "stackingMode", "portMappingCount", "portMappings",
        "interconnectBayMappingCount", "interconnectBayMappings",
        "description", "type"
})
public class EnclosureGroups implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("category")
    private String category;
    @JsonProperty("name")
    private String name;
    @JsonProperty("created")
    private String created;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("eTag")
    private String eTag;
    @JsonProperty("status")
    private String status;
    @JsonProperty("state")
    private String state;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("stackingMode")
    private EnclosureGroups.StackingMode stackingMode;
    @JsonProperty("portMappingCount")
    private Integer portMappingCount;
    @JsonProperty("portMappings")
    private List<PortMapping> portMappings = new ArrayList<PortMapping>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("interconnectBayMappingCount")
    private Integer interconnectBayMappingCount;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("interconnectBayMappings")
    private List<InterconnectBayMapping> interconnectBayMappings = new ArrayList<InterconnectBayMapping>();
    @JsonProperty("description")
    private String description;
    @JsonProperty("type")
    private String type;

    /**
     * 
     * @return The type
     */
    @JsonProperty("type")
    public String getType()
    {
        return type;
    }

    /**
     * 
     * @param type
     *        The type
     */
    @JsonProperty("type")
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * 
     * @return The uri
     */
    @JsonProperty("uri")
    public String getUri()
    {
        return uri;
    }

    /**
     * 
     * @param uri
     *        The uri
     */
    @JsonProperty("uri")
    public void setUri(String uri)
    {
        this.uri = uri;
    }

    /**
     * 
     * @return The category
     */
    @JsonProperty("category")
    public String getCategory()
    {
        return category;
    }

    /**
     * 
     * @param category
     *        The category
     */
    @JsonProperty("category")
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * 
     * @return The name
     */
    @JsonProperty("name")
    public String getName()
    {
        return name;
    }

    /**
     * 
     * @param name
     *        The name
     */
    @JsonProperty("name")
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 
     * @return The created
     */
    @JsonProperty("created")
    public String getCreated()
    {
        return created;
    }

    /**
     * 
     * @param created
     *        The created
     */
    @JsonProperty("created")
    public void setCreated(String created)
    {
        this.created = created;
    }

    /**
     * 
     * @return The modified
     */
    @JsonProperty("modified")
    public String getModified()
    {
        return modified;
    }

    /**
     * 
     * @param modified
     *        The modified
     */
    @JsonProperty("modified")
    public void setModified(String modified)
    {
        this.modified = modified;
    }

    /**
     * 
     * @return The eTag
     */
    @JsonProperty("eTag")
    public String getETag()
    {
        return eTag;
    }

    /**
     * 
     * @param eTag
     *        The eTag
     */
    @JsonProperty("eTag")
    public void setETag(String eTag)
    {
        this.eTag = eTag;
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
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * 
     * @return The state
     */
    @JsonProperty("state")
    public String getState()
    {
        return state;
    }

    /**
     * 
     * @param state
     *        The state
     */
    @JsonProperty("state")
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * 
     * (Required)
     * 
     * @return The stackingMode
     */
    @JsonProperty("stackingMode")
    public EnclosureGroups.StackingMode getStackingMode()
    {
        return stackingMode;
    }

    /**
     * 
     * (Required)
     * 
     * @param stackingMode
     *        The stackingMode
     */
    @JsonProperty("stackingMode")
    public void setStackingMode(EnclosureGroups.StackingMode stackingMode)
    {
        this.stackingMode = stackingMode;
    }

    /**
     * 
     * @return The portMappingCount
     */
    @JsonProperty("portMappingCount")
    public Integer getPortMappingCount()
    {
        return portMappingCount;
    }

    /**
     * 
     * @param portMappingCount
     *        The portMappingCount
     */
    @JsonProperty("portMappingCount")
    public void setPortMappingCount(Integer portMappingCount)
    {
        this.portMappingCount = portMappingCount;
    }

    /**
     * 
     * @return The portMappings
     */
    @JsonProperty("portMappings")
    public List<PortMapping> getPortMappings()
    {
        return portMappings;
    }

    /**
     * 
     * @param portMappings
     *        The portMappings
     */
    @JsonProperty("portMappings")
    public void setPortMappings(List<PortMapping> portMappings)
    {
        this.portMappings = portMappings;
    }

    /**
     * 
     * (Required)
     * 
     * @return The interconnectBayMappingCount
     */
    @JsonProperty("interconnectBayMappingCount")
    public Integer getInterconnectBayMappingCount()
    {
        return interconnectBayMappingCount;
    }

    /**
     * 
     * (Required)
     * 
     * @param interconnectBayMappingCount
     *        The interconnectBayMappingCount
     */
    @JsonProperty("interconnectBayMappingCount")
    public void setInterconnectBayMappingCount(
            Integer interconnectBayMappingCount)
    {
        this.interconnectBayMappingCount = interconnectBayMappingCount;
    }

    /**
     * 
     * (Required)
     * 
     * @return The interconnectBayMappings
     */
    @JsonProperty("interconnectBayMappings")
    public List<InterconnectBayMapping> getInterconnectBayMappings()
    {
        return interconnectBayMappings;
    }

    /**
     * 
     * (Required)
     * 
     * @param interconnectBayMappings
     *        The interconnectBayMappings
     */
    @JsonProperty("interconnectBayMappings")
    public void setInterconnectBayMappings(
            List<InterconnectBayMapping> interconnectBayMappings)
    {
        this.interconnectBayMappings = interconnectBayMappings;
    }

    /**
     * 
     * @return The description
     */
    @JsonProperty("description")
    public String getDescription()
    {
        return description;
    }

    /**
     * 
     * @param description
     *        The description
     */
    @JsonProperty("description")
    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(uri).append(category).append(name)
                .append(created).append(modified).append(eTag).append(status)
                .append(state).append(stackingMode).append(portMappingCount)
                .append(portMappings).append(interconnectBayMappingCount)
                .append(interconnectBayMappings).append(description)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof EnclosureGroups) == false)
        {
            return false;
        }
        EnclosureGroups rhs = ((EnclosureGroups) other);
        return new EqualsBuilder()
                .append(uri, rhs.uri)
                .append(category, rhs.category)
                .append(name, rhs.name)
                .append(created, rhs.created)
                .append(modified, rhs.modified)
                .append(eTag, rhs.eTag)
                .append(status, rhs.status)
                .append(state, rhs.state)
                .append(stackingMode, rhs.stackingMode)
                .append(portMappingCount, rhs.portMappingCount)
                .append(portMappings, rhs.portMappings)
                .append(interconnectBayMappingCount,
                        rhs.interconnectBayMappingCount)
                .append(interconnectBayMappings, rhs.interconnectBayMappings)
                .append(description, rhs.description).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum StackingMode
    {

        None ("None"),
        Enclosure ("Enclosure"),
        SwitchPairs ("SwitchPairs"),
        MultiEnclosure (
                "MultiEnclosure");
        private final String value;
        private static Map<String, EnclosureGroups.StackingMode> constants = new HashMap<String, EnclosureGroups.StackingMode>();

        static
        {
            for (EnclosureGroups.StackingMode c : values())
            {
                constants.put(c.value, c);
            }
        }

        private StackingMode(String value)
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
        public static EnclosureGroups.StackingMode fromValue(String value)
        {
            EnclosureGroups.StackingMode constant = constants.get(value);
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
