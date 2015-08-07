/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "interconnectType", "enableIgmpSnooping", "igmpIdleTimeoutInterval", "enableFastMacCacheFailover",
        "macRefreshInterval", "enableNetworkLoopProtection", "enablePauseFloodProtection", "dependentResourceUri", "name", "id",
        "description", "status", "state", "eTag", "created", "modified", "category", "uri", "type" })
public class EthernetSettings implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("interconnectType")
    private EthernetSettings.InterconnectType interconnectType = EthernetSettings.InterconnectType.fromValue("Ethernet");
    @JsonProperty("enableIgmpSnooping")
    private Boolean enableIgmpSnooping = false;
    @JsonProperty("igmpIdleTimeoutInterval")
    private Integer igmpIdleTimeoutInterval = 260;
    @JsonProperty("enableFastMacCacheFailover")
    private Boolean enableFastMacCacheFailover = true;
    @JsonProperty("macRefreshInterval")
    private Integer macRefreshInterval = 5;
    @JsonProperty("enableNetworkLoopProtection")
    private Boolean enableNetworkLoopProtection = true;
    @JsonProperty("enablePauseFloodProtection")
    private Boolean enablePauseFloodProtection = true;
    @JsonProperty("dependentResourceUri")
    private String dependentResourceUri;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private String id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
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
     * @return The interconnectType
     */
    @JsonProperty("interconnectType")
    public EthernetSettings.InterconnectType getInterconnectType() {
        return interconnectType;
    }

    /**
     * 
     * @param interconnectType
     *            The interconnectType
     */
    @JsonProperty("interconnectType")
    public void setInterconnectType(final EthernetSettings.InterconnectType interconnectType) {
        this.interconnectType = interconnectType;
    }

    /**
     * 
     * @return The enableIgmpSnooping
     */
    @JsonProperty("enableIgmpSnooping")
    public Boolean getEnableIgmpSnooping() {
        return enableIgmpSnooping;
    }

    /**
     * 
     * @param enableIgmpSnooping
     *            The enableIgmpSnooping
     */
    @JsonProperty("enableIgmpSnooping")
    public void setEnableIgmpSnooping(final Boolean enableIgmpSnooping) {
        this.enableIgmpSnooping = enableIgmpSnooping;
    }

    /**
     * 
     * @return The igmpIdleTimeoutInterval
     */
    @JsonProperty("igmpIdleTimeoutInterval")
    public Integer getIgmpIdleTimeoutInterval() {
        return igmpIdleTimeoutInterval;
    }

    /**
     * 
     * @param igmpIdleTimeoutInterval
     *            The igmpIdleTimeoutInterval
     */
    @JsonProperty("igmpIdleTimeoutInterval")
    public void setIgmpIdleTimeoutInterval(final Integer igmpIdleTimeoutInterval) {
        this.igmpIdleTimeoutInterval = igmpIdleTimeoutInterval;
    }

    /**
     * 
     * @return The enableFastMacCacheFailover
     */
    @JsonProperty("enableFastMacCacheFailover")
    public Boolean getEnableFastMacCacheFailover() {
        return enableFastMacCacheFailover;
    }

    /**
     * 
     * @param enableFastMacCacheFailover
     *            The enableFastMacCacheFailover
     */
    @JsonProperty("enableFastMacCacheFailover")
    public void setEnableFastMacCacheFailover(final Boolean enableFastMacCacheFailover) {
        this.enableFastMacCacheFailover = enableFastMacCacheFailover;
    }

    /**
     * 
     * @return The macRefreshInterval
     */
    @JsonProperty("macRefreshInterval")
    public Integer getMacRefreshInterval() {
        return macRefreshInterval;
    }

    /**
     * 
     * @param macRefreshInterval
     *            The macRefreshInterval
     */
    @JsonProperty("macRefreshInterval")
    public void setMacRefreshInterval(final Integer macRefreshInterval) {
        this.macRefreshInterval = macRefreshInterval;
    }

    /**
     * 
     * @return The enableNetworkLoopProtection
     */
    @JsonProperty("enableNetworkLoopProtection")
    public Boolean getEnableNetworkLoopProtection() {
        return enableNetworkLoopProtection;
    }

    /**
     * 
     * @param enableNetworkLoopProtection
     *            The enableNetworkLoopProtection
     */
    @JsonProperty("enableNetworkLoopProtection")
    public void setEnableNetworkLoopProtection(final Boolean enableNetworkLoopProtection) {
        this.enableNetworkLoopProtection = enableNetworkLoopProtection;
    }

    /**
     * 
     * @return The enablePauseFloodProtection
     */
    @JsonProperty("enablePauseFloodProtection")
    public Boolean getEnablePauseFloodProtection() {
        return enablePauseFloodProtection;
    }

    /**
     * 
     * @param enablePauseFloodProtection
     *            The enablePauseFloodProtection
     */
    @JsonProperty("enablePauseFloodProtection")
    public void setEnablePauseFloodProtection(final Boolean enablePauseFloodProtection) {
        this.enablePauseFloodProtection = enablePauseFloodProtection;
    }

    /**
     * 
     * @return The dependentResourceUri
     */
    @JsonProperty("dependentResourceUri")
    public String getDependentResourceUri() {
        return dependentResourceUri;
    }

    /**
     * 
     * @param dependentResourceUri
     *            The dependentResourceUri
     */
    @JsonProperty("dependentResourceUri")
    public void setDependentResourceUri(final String dependentResourceUri) {
        this.dependentResourceUri = dependentResourceUri;
    }

    /**
     * 
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *            The name
     */
    @JsonProperty("name")
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 
     * @return The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *            The id
     */
    @JsonProperty("id")
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * 
     * @return The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *            The description
     */
    @JsonProperty("description")
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * 
     * @return The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *            The status
     */
    @JsonProperty("status")
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * 
     * @return The state
     */
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *            The state
     */
    @JsonProperty("state")
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * 
     * @return The eTag
     */
    @JsonProperty("eTag")
    public String getETag() {
        return eTag;
    }

    /**
     * 
     * @param eTag
     *            The eTag
     */
    @JsonProperty("eTag")
    public void setETag(final String eTag) {
        this.eTag = eTag;
    }

    /**
     * 
     * @return The created
     */
    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    /**
     * 
     * @param created
     *            The created
     */
    @JsonProperty("created")
    public void setCreated(final String created) {
        this.created = created;
    }

    /**
     * 
     * @return The modified
     */
    @JsonProperty("modified")
    public String getModified() {
        return modified;
    }

    /**
     * 
     * @param modified
     *            The modified
     */
    @JsonProperty("modified")
    public void setModified(final String modified) {
        this.modified = modified;
    }

    /**
     * 
     * @return The category
     */
    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *            The category
     */
    @JsonProperty("category")
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     * 
     * @return The uri
     */
    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    /**
     * 
     * @param uri
     *            The uri
     */
    @JsonProperty("uri")
    public void setUri(final String uri) {
        this.uri = uri;
    }

    /**
     * 
     * @return The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *            The type
     */
    @JsonProperty("type")
    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(interconnectType).append(enableIgmpSnooping).append(igmpIdleTimeoutInterval)
                .append(enableFastMacCacheFailover).append(macRefreshInterval).append(enableNetworkLoopProtection)
                .append(enablePauseFloodProtection).append(dependentResourceUri).append(name).append(id).append(description)
                .append(status).append(state).append(eTag).append(created).append(modified).append(category).append(uri)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EthernetSettings) == false) {
            return false;
        }
        final EthernetSettings rhs = ((EthernetSettings) other);
        return new EqualsBuilder().append(interconnectType, rhs.interconnectType)
                .append(enableIgmpSnooping, rhs.enableIgmpSnooping).append(igmpIdleTimeoutInterval, rhs.igmpIdleTimeoutInterval)
                .append(enableFastMacCacheFailover, rhs.enableFastMacCacheFailover)
                .append(macRefreshInterval, rhs.macRefreshInterval)
                .append(enableNetworkLoopProtection, rhs.enableNetworkLoopProtection)
                .append(enablePauseFloodProtection, rhs.enablePauseFloodProtection)
                .append(dependentResourceUri, rhs.dependentResourceUri).append(name, rhs.name).append(id, rhs.id)
                .append(description, rhs.description).append(status, rhs.status).append(state, rhs.state).append(eTag, rhs.eTag)
                .append(created, rhs.created).append(modified, rhs.modified).append(category, rhs.category).append(uri, rhs.uri)
                .isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum InterconnectType {

        Ethernet("Ethernet"), FibreChannel("FibreChannel");
        private final String value;
        private static Map<String, EthernetSettings.InterconnectType> constants = new HashMap<String, EthernetSettings.InterconnectType>();

        static {
            for (final EthernetSettings.InterconnectType c : values()) {
                constants.put(c.value, c);
            }
        }

        private InterconnectType(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static EthernetSettings.InterconnectType fromValue(final String value) {
            final EthernetSettings.InterconnectType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
