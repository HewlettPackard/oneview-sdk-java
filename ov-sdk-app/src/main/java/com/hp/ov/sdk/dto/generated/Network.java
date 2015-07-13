/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.hp.ov.sdk.dto.JsonRequest;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "vlanId", "internalVlanId", "smartLink", "privateNetwork",
        "purpose", "connectionTemplateUri", "connectionTemplate",
        "ethernetNetworkType", "description", "status", "name", "state",
        "eTag", "created", "modified", "category", "uri", "type", "jsonRequest"
})
public class Network implements Serializable
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
    @JsonProperty("type")
    private String type;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("vlanId")
    private Integer vlanId;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("internalVlanId")
    private Integer internalVlanId;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("smartLink")
    private Boolean smartLink;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("privateNetwork")
    private Boolean privateNetwork;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("purpose")
    private Network.Purpose purpose;
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
    @JsonProperty("ethernetNetworkType")
    private Network.EthernetNetworkType ethernetNetworkType = Network.EthernetNetworkType
            .fromValue("Tagged");
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
    @JsonProperty("jsonRequest")
    private JsonRequest jsonRequest;
    @JsonProperty("connectionTemplate")
    private ConnectionTemplate connectionTemplate;

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
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * 
     * (Required)
     * 
     * @return The vlanId
     */
    @JsonProperty("vlanId")
    public Integer getVlanId()
    {
        return vlanId;
    }

    /**
     * 
     * (Required)
     * 
     * @param vlanId
     *        The vlanId
     */
    @JsonProperty("vlanId")
    public void setVlanId(Integer vlanId)
    {
        this.vlanId = vlanId;
    }

    /**
     * 
     * (Required)
     * 
     * @return The internalVlanId
     */
    @JsonProperty("internalVlanId")
    public Integer getInternalVlanId()
    {
        return internalVlanId;
    }

    /**
     * 
     * (Required)
     * 
     * @param internalVlanId
     *        The internalVlanId
     */
    @JsonProperty("internalVlanId")
    public void setInternalVlanId(Integer internalVlanId)
    {
        this.internalVlanId = internalVlanId;
    }

    /**
     * 
     * (Required)
     * 
     * @return The smartLink
     */
    @JsonProperty("smartLink")
    public Boolean getSmartLink()
    {
        return smartLink;
    }

    /**
     * 
     * (Required)
     * 
     * @param smartLink
     *        The smartLink
     */
    @JsonProperty("smartLink")
    public void setSmartLink(Boolean smartLink)
    {
        this.smartLink = smartLink;
    }

    /**
     * 
     * (Required)
     * 
     * @return The privateNetwork
     */
    @JsonProperty("privateNetwork")
    public Boolean getPrivateNetwork()
    {
        return privateNetwork;
    }

    /**
     * 
     * (Required)
     * 
     * @param privateNetwork
     *        The privateNetwork
     */
    @JsonProperty("privateNetwork")
    public void setPrivateNetwork(Boolean privateNetwork)
    {
        this.privateNetwork = privateNetwork;
    }

    /**
     * 
     * (Required)
     * 
     * @return The purpose
     */
    @JsonProperty("purpose")
    public Network.Purpose getPurpose()
    {
        return purpose;
    }

    /**
     * 
     * (Required)
     * 
     * @param purpose
     *        The purpose
     */
    @JsonProperty("purpose")
    public void setPurpose(Network.Purpose purpose)
    {
        this.purpose = purpose;
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
    public void setConnectionTemplateUri(String connectionTemplateUri)
    {
        this.connectionTemplateUri = connectionTemplateUri;
    }

    /**
     * 
     * (Required)
     * 
     * @return The ethernetNetworkType
     */
    @JsonProperty("ethernetNetworkType")
    public Network.EthernetNetworkType getEthernetNetworkType()
    {
        return ethernetNetworkType;
    }

    /**
     * 
     * (Required)
     * 
     * @param ethernetNetworkType
     *        The ethernetNetworkType
     */
    @JsonProperty("ethernetNetworkType")
    public void setEthernetNetworkType(
            Network.EthernetNetworkType ethernetNetworkType)
    {
        this.ethernetNetworkType = ethernetNetworkType;
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
     * @return the connectionTemplate
     */
    public ConnectionTemplate getConnectionTemplate()
    {
        return connectionTemplate;
    }

    /**
     * @param connectionTemplate
     *        the connectionTemplate to set
     */
    public void setConnectionTemplate(ConnectionTemplate connectionTemplate)
    {
        this.connectionTemplate = connectionTemplate;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
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
    public void setJsonRequest(JsonRequest jsonRequest)
    {
        this.jsonRequest = jsonRequest;
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(connectionTemplate)
                .append(jsonRequest).append(type).append(vlanId)
                .append(internalVlanId).append(smartLink)
                .append(privateNetwork).append(purpose)
                .append(connectionTemplateUri).append(ethernetNetworkType)
                .append(description).append(status).append(name).append(state)
                .append(eTag).append(created).append(modified).append(category)
                .append(uri).toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof Network) == false)
        {
            return false;
        }
        Network rhs = ((Network) other);
        return new EqualsBuilder()
                .append(connectionTemplate, rhs.connectionTemplate)
                .append(jsonRequest, rhs.jsonRequest).append(type, rhs.type)
                .append(vlanId, rhs.vlanId)
                .append(internalVlanId, rhs.internalVlanId)
                .append(smartLink, rhs.smartLink)
                .append(privateNetwork, rhs.privateNetwork)
                .append(purpose, rhs.purpose)
                .append(connectionTemplateUri, rhs.connectionTemplateUri)
                .append(ethernetNetworkType, rhs.ethernetNetworkType)
                .append(description, rhs.description)
                .append(status, rhs.status).append(name, rhs.name)
                .append(state, rhs.state).append(eTag, rhs.eTag)
                .append(created, rhs.created).append(modified, rhs.modified)
                .append(category, rhs.category).append(uri, rhs.uri).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum EthernetNetworkType
    {

        Tagged ("Tagged"),
        Untagged ("Untagged"),
        Tunnel ("Tunnel"),
        Unknown (
                "Unknown"),
        NotApplicable ("NotApplicable");
        private final String value;
        private static Map<String, Network.EthernetNetworkType> constants = new HashMap<String, Network.EthernetNetworkType>();

        static
        {
            for (Network.EthernetNetworkType c : values())
            {
                constants.put(c.value, c);
            }
        }

        private EthernetNetworkType(String value)
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
        public static Network.EthernetNetworkType fromValue(String value)
        {
            Network.EthernetNetworkType constant = constants.get(value);
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

    @Generated("org.jsonschema2pojo")
    public static enum Purpose
    {

        General ("General"),
        Management ("Management"),
        VMMigration ("VMMigration"),
        FaultTolerance (
                "FaultTolerance");
        private final String value;
        private static Map<String, Network.Purpose> constants = new HashMap<String, Network.Purpose>();

        static
        {
            for (Network.Purpose c : values())
            {
                constants.put(c.value, c);
            }
        }

        private Purpose(String value)
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
        public static Network.Purpose fromValue(String value)
        {
            Network.Purpose constant = constants.get(value);
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
