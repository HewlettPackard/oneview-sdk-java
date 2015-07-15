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
        "networkUris", "connectionTemplateUri",
        "nativeNetworkUri", "description", "status", "name", "state", "eTag",
        "created", "modified", "category", "uri", "type"
})
public class NetworkSets implements Serializable
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
    @JsonProperty("networkUris")
    private List<String> networkUris = new ArrayList<String>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("connectionTemplateUri")
    private String connectionTemplateUri;
    @JsonProperty("nativeNetworkUri")
    private String nativeNetworkUri;
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

    /**
     * 
     * (Required)
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
     * (Required)
     * 
     * @param type
     *        The type
     */
    @JsonProperty("type")
    public void setType(final String type)
    {
        this.type = type;
    }

    /**
     * 
     * (Required)
     * 
     * @return The networkUris
     */
    @JsonProperty("networkUris")
    public List<String> getNetworkUris()
    {
        return networkUris;
    }

    /**
     * 
     * (Required)
     * 
     * @param networkUris
     *        The networkUris
     */
    @JsonProperty("networkUris")
    public void setNetworkUris(final List<String> networkUris)
    {
        this.networkUris = networkUris;
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
     * @return The nativeNetworkUri
     */
    @JsonProperty("nativeNetworkUri")
    public String getNativeNetworkUri()
    {
        return nativeNetworkUri;
    }

    /**
     * 
     * @param nativeNetworkUri
     *        The nativeNetworkUri
     */
    @JsonProperty("nativeNetworkUri")
    public void setNativeNetworkUri(final String nativeNetworkUri)
    {
        this.nativeNetworkUri = nativeNetworkUri;
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

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(type).append(networkUris)
                .append(connectionTemplateUri).append(nativeNetworkUri)
                .append(description).append(status).append(name).append(state)
                .append(eTag).append(created).append(modified).append(category)
                .append(uri).toHashCode();
    }

    @Override
    public boolean equals(final Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof NetworkSets) == false)
        {
            return false;
        }
        final NetworkSets rhs = ((NetworkSets) other);
        return new EqualsBuilder().append(type, rhs.type)
                .append(networkUris, rhs.networkUris)
                .append(connectionTemplateUri, rhs.connectionTemplateUri)
                .append(nativeNetworkUri, rhs.nativeNetworkUri)
                .append(description, rhs.description)
                .append(status, rhs.status).append(name, rhs.name)
                .append(state, rhs.state).append(eTag, rhs.eTag)
                .append(created, rhs.created).append(modified, rhs.modified)
                .append(category, rhs.category).append(uri, rhs.uri).isEquals();
    }

}
