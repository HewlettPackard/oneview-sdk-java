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
@JsonPropertyOrder({ "partNumber", "minimumFirmwareVersion", "maximumFirmwareVersion", "downlinkCapabilities", "portInfos",
        "unsupportedCapabilities", "downlinkCount", "description", "status", "name", "state", "eTag", "created", "modified",
        "category", "uri", "type" })
public class InterconnectTypes implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("partNumber")
    private String partNumber;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("minimumFirmwareVersion")
    private String minimumFirmwareVersion;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("maximumFirmwareVersion")
    private String maximumFirmwareVersion;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("downlinkCapabilities")
    private List<String> downlinkCapabilities = new ArrayList<String>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("portInfos")
    private List<PortInfo> portInfos = new ArrayList<PortInfo>();
    @JsonProperty("unsupportedCapabilities")
    private List<String> unsupportedCapabilities = new ArrayList<String>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("downlinkCount")
    private Integer downlinkCount;
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
     * @return The partNumber
     */
    @JsonProperty("partNumber")
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * 
     * (Required)
     * 
     * @param partNumber
     *            The partNumber
     */
    @JsonProperty("partNumber")
    public void setPartNumber(final String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * 
     * (Required)
     * 
     * @return The minimumFirmwareVersion
     */
    @JsonProperty("minimumFirmwareVersion")
    public String getMinimumFirmwareVersion() {
        return minimumFirmwareVersion;
    }

    /**
     * 
     * (Required)
     * 
     * @param minimumFirmwareVersion
     *            The minimumFirmwareVersion
     */
    @JsonProperty("minimumFirmwareVersion")
    public void setMinimumFirmwareVersion(final String minimumFirmwareVersion) {
        this.minimumFirmwareVersion = minimumFirmwareVersion;
    }

    /**
     * 
     * (Required)
     * 
     * @return The maximumFirmwareVersion
     */
    @JsonProperty("maximumFirmwareVersion")
    public String getMaximumFirmwareVersion() {
        return maximumFirmwareVersion;
    }

    /**
     * 
     * (Required)
     * 
     * @param maximumFirmwareVersion
     *            The maximumFirmwareVersion
     */
    @JsonProperty("maximumFirmwareVersion")
    public void setMaximumFirmwareVersion(final String maximumFirmwareVersion) {
        this.maximumFirmwareVersion = maximumFirmwareVersion;
    }

    /**
     * 
     * (Required)
     * 
     * @return The downlinkCapabilities
     */
    @JsonProperty("downlinkCapabilities")
    public List<String> getDownlinkCapabilities() {
        return downlinkCapabilities;
    }

    /**
     * 
     * (Required)
     * 
     * @param downlinkCapabilities
     *            The downlinkCapabilities
     */
    @JsonProperty("downlinkCapabilities")
    public void setDownlinkCapabilities(final List<String> downlinkCapabilities) {
        this.downlinkCapabilities = downlinkCapabilities;
    }

    /**
     * 
     * (Required)
     * 
     * @return The portInfos
     */
    @JsonProperty("portInfos")
    public List<PortInfo> getPortInfos() {
        return portInfos;
    }

    /**
     * 
     * (Required)
     * 
     * @param portInfos
     *            The portInfos
     */
    @JsonProperty("portInfos")
    public void setPortInfos(final List<PortInfo> portInfos) {
        this.portInfos = portInfos;
    }

    /**
     * 
     * @return The unsupportedCapabilities
     */
    @JsonProperty("unsupportedCapabilities")
    public List<String> getUnsupportedCapabilities() {
        return unsupportedCapabilities;
    }

    /**
     * 
     * @param unsupportedCapabilities
     *            The unsupportedCapabilities
     */
    @JsonProperty("unsupportedCapabilities")
    public void setUnsupportedCapabilities(final List<String> unsupportedCapabilities) {
        this.unsupportedCapabilities = unsupportedCapabilities;
    }

    /**
     * 
     * (Required)
     * 
     * @return The downlinkCount
     */
    @JsonProperty("downlinkCount")
    public Integer getDownlinkCount() {
        return downlinkCount;
    }

    /**
     * 
     * (Required)
     * 
     * @param downlinkCount
     *            The downlinkCount
     */
    @JsonProperty("downlinkCount")
    public void setDownlinkCount(final Integer downlinkCount) {
        this.downlinkCount = downlinkCount;
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
     * (Required)
     * 
     * @return The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * (Required)
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
        return new HashCodeBuilder().append(type).append(partNumber).append(minimumFirmwareVersion).append(maximumFirmwareVersion)
                .append(downlinkCapabilities).append(portInfos).append(unsupportedCapabilities).append(downlinkCount)
                .append(description).append(status).append(name).append(state).append(eTag).append(created).append(modified)
                .append(category).append(uri).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InterconnectTypes) == false) {
            return false;
        }
        final InterconnectTypes rhs = ((InterconnectTypes) other);
        return new EqualsBuilder().append(type, rhs.type).append(partNumber, rhs.partNumber)
                .append(minimumFirmwareVersion, rhs.minimumFirmwareVersion)
                .append(maximumFirmwareVersion, rhs.maximumFirmwareVersion).append(downlinkCapabilities, rhs.downlinkCapabilities)
                .append(portInfos, rhs.portInfos).append(unsupportedCapabilities, rhs.unsupportedCapabilities)
                .append(downlinkCount, rhs.downlinkCount).append(description, rhs.description).append(status, rhs.status)
                .append(name, rhs.name).append(state, rhs.state).append(eTag, rhs.eTag).append(created, rhs.created)
                .append(modified, rhs.modified).append(category, rhs.category).append(uri, rhs.uri).isEquals();
    }

}
