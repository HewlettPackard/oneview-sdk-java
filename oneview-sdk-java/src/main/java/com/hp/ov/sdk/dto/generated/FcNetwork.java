/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.HashMap;
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
import com.hp.ov.sdk.dto.JsonRequest;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "fabricType", "connectionTemplateUri",
        "linkStabilityTime", "managedSanUri", "autoLoginRedistribution",
        "description", "status", "name", "state", "eTag", "created",
        "modified", "category", "uri", "type", "jsonRequest"
})
public class FcNetwork implements Serializable
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
    @JsonProperty("fabricType")
    private FcNetwork.FabricType fabricType = FcNetwork.FabricType
            .fromValue("FabricAttach");
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("connectionTemplateUri")
    private String connectionTemplateUri;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("linkStabilityTime")
    private Integer linkStabilityTime = 30;
    @JsonProperty("managedSanUri")
    private String managedSanUri;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("autoLoginRedistribution")
    private Boolean autoLoginRedistribution = false;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
    @JsonProperty("name")
    private String name;
    @JsonProperty("state")
    private String state;
    @JsonProperty("eTag")
    private String eTag;
    @JsonProperty("created")
    private String created;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("category")
    private String category;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("type")
    private String type;
    @JsonProperty("jsonRequest")
    private JsonRequest jsonRequest;

    /**
     * 
     * (Required)
     * 
     * @return The fabricType
     */
    @JsonProperty("fabricType")
    public FcNetwork.FabricType getFabricType()
    {
        return fabricType;
    }

    /**
     * 
     * (Required)
     * 
     * @param fabricType
     *        The fabricType
     */
    @JsonProperty("fabricType")
    public void setFabricType(final FcNetwork.FabricType fabricType)
    {
        this.fabricType = fabricType;
    }

    /**
     * 
     * (Required)
     * 
     * @return The connectionTemplateUri
     */
    @JsonProperty("connectionTemplateUri")
    public String getConnectionTemplateUri()
    {
        return connectionTemplateUri;
    }

    /**
     * 
     * (Required)
     * 
     * @param connectionTemplateUri
     *        The connectionTemplateUri
     */
    @JsonProperty("connectionTemplateUri")
    public void setConnectionTemplateUri(final String connectionTemplateUri)
    {
        this.connectionTemplateUri = connectionTemplateUri;
    }

    /**
     * 
     * (Required)
     * 
     * @return The linkStabilityTime
     */
    @JsonProperty("linkStabilityTime")
    public Integer getLinkStabilityTime()
    {
        return linkStabilityTime;
    }

    /**
     * 
     * (Required)
     * 
     * @param linkStabilityTime
     *        The linkStabilityTime
     */
    @JsonProperty("linkStabilityTime")
    public void setLinkStabilityTime(final Integer linkStabilityTime)
    {
        this.linkStabilityTime = linkStabilityTime;
    }

    /**
     * 
     * @return The managedSanUri
     */
    @JsonProperty("managedSanUri")
    public String getManagedSanUri()
    {
        return managedSanUri;
    }

    /**
     * 
     * @param managedSanUri
     *        The managedSanUri
     */
    @JsonProperty("managedSanUri")
    public void setManagedSanUri(final String managedSanUri)
    {
        this.managedSanUri = managedSanUri;
    }

    /**
     * 
     * (Required)
     * 
     * @return The autoLoginRedistribution
     */
    @JsonProperty("autoLoginRedistribution")
    public Boolean getAutoLoginRedistribution()
    {
        return autoLoginRedistribution;
    }

    /**
     * 
     * (Required)
     * 
     * @param autoLoginRedistribution
     *        The autoLoginRedistribution
     */
    @JsonProperty("autoLoginRedistribution")
    public void setAutoLoginRedistribution(final Boolean autoLoginRedistribution)
    {
        this.autoLoginRedistribution = autoLoginRedistribution;
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
    public void setDescription(final String description)
    {
        this.description = description;
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
    public void setName(final String name)
    {
        this.name = name;
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
    public void setState(final String state)
    {
        this.state = state;
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
    public void setETag(final String eTag)
    {
        this.eTag = eTag;
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
    public void setCreated(final String created)
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
    public void setModified(final String modified)
    {
        this.modified = modified;
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
    public void setCategory(final String category)
    {
        this.category = category;
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
    public void setUri(final String uri)
    {
        this.uri = uri;
    }

    /**
     * 
     * @return The type
     */
    public String getType()
    {
        return type;
    }

    /**
     * 
     * @param type
     *        The type
     */
    public void setType(final String type)
    {
        this.type = type;
    }

    /**
     * 
     * @return The jsonRequest
     */
    public JsonRequest getJsonRequest()
    {
        return jsonRequest;
    }

    /**
     * 
     * @param jsonRequest
     *        The jsonRequest
     */
    public void setJsonRequest(final JsonRequest jsonRequest)
    {
        this.jsonRequest = jsonRequest;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(jsonRequest).append(type)
                .append(fabricType).append(connectionTemplateUri)
                .append(linkStabilityTime).append(managedSanUri)
                .append(autoLoginRedistribution).append(description)
                .append(status).append(name).append(state).append(eTag)
                .append(created).append(modified).append(category).append(uri)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof FcNetwork) == false)
        {
            return false;
        }
        final FcNetwork rhs = ((FcNetwork) other);
        return new EqualsBuilder().append(jsonRequest, rhs.jsonRequest)
                .append(type, rhs.type).append(fabricType, rhs.fabricType)
                .append(connectionTemplateUri, rhs.connectionTemplateUri)
                .append(linkStabilityTime, rhs.linkStabilityTime)
                .append(managedSanUri, rhs.managedSanUri)
                .append(autoLoginRedistribution, rhs.autoLoginRedistribution)
                .append(description, rhs.description)
                .append(status, rhs.status).append(name, rhs.name)
                .append(state, rhs.state).append(eTag, rhs.eTag)
                .append(created, rhs.created).append(modified, rhs.modified)
                .append(category, rhs.category).append(uri, rhs.uri).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum FabricType
    {

        FabricAttach ("FabricAttach"),
        DirectAttach ("DirectAttach");
        private final String value;
        private static Map<String, FcNetwork.FabricType> constants = new HashMap<String, FcNetwork.FabricType>();

        static
        {
            for (final FcNetwork.FabricType c : values())
            {
                constants.put(c.value, c);
            }
        }

        private FabricType(final String value)
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
        public static FcNetwork.FabricType fromValue(final String value)
        {
            final FcNetwork.FabricType constant = constants.get(value);
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
