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
        "readCommunity", "systemContact", "trapDestinations",
        "snmpAccess", "enabled", "description", "status", "name", "state",
        "eTag", "created", "modified", "category", "uri", "type"
})
public class SnmpConfiguration implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("readCommunity")
    private String readCommunity = "public";
    @JsonProperty("systemContact")
    private String systemContact = "";
    @JsonProperty("trapDestinations")
    private List<TrapDestination> trapDestinations = new ArrayList<TrapDestination>();
    @JsonProperty("snmpAccess")
    private List<String> snmpAccess = new ArrayList<String>();
    @JsonProperty("enabled")
    private Boolean enabled = false;
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
     * @return The readCommunity
     */
    @JsonProperty("readCommunity")
    public String getReadCommunity()
    {
        return readCommunity;
    }

    /**
     * 
     * @param readCommunity
     *        The readCommunity
     */
    @JsonProperty("readCommunity")
    public void setReadCommunity(final String readCommunity)
    {
        this.readCommunity = readCommunity;
    }

    /**
     * 
     * @return The systemContact
     */
    @JsonProperty("systemContact")
    public String getSystemContact()
    {
        return systemContact;
    }

    /**
     * 
     * @param systemContact
     *        The systemContact
     */
    @JsonProperty("systemContact")
    public void setSystemContact(final String systemContact)
    {
        this.systemContact = systemContact;
    }

    /**
     * 
     * @return The trapDestinations
     */
    @JsonProperty("trapDestinations")
    public List<TrapDestination> getTrapDestinations()
    {
        return trapDestinations;
    }

    /**
     * 
     * @param trapDestinations
     *        The trapDestinations
     */
    @JsonProperty("trapDestinations")
    public void setTrapDestinations(final List<TrapDestination> trapDestinations)
    {
        this.trapDestinations = trapDestinations;
    }

    /**
     * 
     * @return The snmpAccess
     */
    @JsonProperty("snmpAccess")
    public List<String> getSnmpAccess()
    {
        return snmpAccess;
    }

    /**
     * 
     * @param snmpAccess
     *        The snmpAccess
     */
    @JsonProperty("snmpAccess")
    public void setSnmpAccess(final List<String> snmpAccess)
    {
        this.snmpAccess = snmpAccess;
    }

    /**
     * 
     * @return The enabled
     */
    @JsonProperty("enabled")
    public Boolean getEnabled()
    {
        return enabled;
    }

    /**
     * 
     * @param enabled
     *        The enabled
     */
    @JsonProperty("enabled")
    public void setEnabled(final Boolean enabled)
    {
        this.enabled = enabled;
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
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type
     *        the type to set
     */
    public void setType(final String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(type).append(readCommunity)
                .append(systemContact).append(trapDestinations)
                .append(snmpAccess).append(enabled).append(description)
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
        if ((other instanceof SnmpConfiguration) == false)
        {
            return false;
        }
        final SnmpConfiguration rhs = ((SnmpConfiguration) other);
        return new EqualsBuilder().append(type, rhs.type)
                .append(readCommunity, rhs.readCommunity)
                .append(systemContact, rhs.systemContact)
                .append(trapDestinations, rhs.trapDestinations)
                .append(snmpAccess, rhs.snmpAccess)
                .append(enabled, rhs.enabled)
                .append(description, rhs.description)
                .append(status, rhs.status).append(name, rhs.name)
                .append(state, rhs.state).append(eTag, rhs.eTag)
                .append(created, rhs.created).append(modified, rhs.modified)
                .append(category, rhs.category).append(uri, rhs.uri).isEquals();
    }

}
