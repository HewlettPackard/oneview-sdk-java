/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
@JsonPropertyOrder({ "resourceId", "uuid", "xmlKeyName", "isoFileName", "baselineShortName", "bundleSize", "version",
        "releaseDate", "supportedOSList", "supportedLanguages", "fwComponents", "swPackagesFullPath", "state", "lastTaskUri",
        "description", "status", "name", "eTag", "created", "modified", "category", "uri", "type" })
public class FwBaseline implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("resourceId")
    private String resourceId;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("xmlKeyName")
    private String xmlKeyName;
    @JsonProperty("isoFileName")
    private String isoFileName;
    @JsonProperty("baselineShortName")
    private String baselineShortName;
    @JsonProperty("bundleSize")
    private Double bundleSize;
    @JsonProperty("version")
    private String version;
    @JsonProperty("releaseDate")
    private String releaseDate;
    @JsonProperty("supportedOSList")
    private List<String> supportedOSList = new ArrayList<String>();
    @JsonProperty("supportedLanguages")
    private String supportedLanguages;
    @JsonProperty("fwComponents")
    private List<FwComponent> fwComponents = new ArrayList<FwComponent>();
    @JsonProperty("swPackagesFullPath")
    private String swPackagesFullPath;
    @JsonProperty("state")
    private String state;
    @JsonProperty("lastTaskUri")
    private String lastTaskUri;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
    @JsonProperty("name")
    private String name;
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
     * @return The resourceId
     */
    @JsonProperty("resourceId")
    public String getResourceId() {
        return resourceId;
    }

    /**
     * 
     * @param resourceId
     *            The resourceId
     */
    @JsonProperty("resourceId")
    public void setResourceId(final String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 
     * @return The uuid
     */
    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    /**
     * 
     * @param uuid
     *            The uuid
     */
    @JsonProperty("uuid")
    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    /**
     * 
     * @return The xmlKeyName
     */
    @JsonProperty("xmlKeyName")
    public String getXmlKeyName() {
        return xmlKeyName;
    }

    /**
     * 
     * @param xmlKeyName
     *            The xmlKeyName
     */
    @JsonProperty("xmlKeyName")
    public void setXmlKeyName(final String xmlKeyName) {
        this.xmlKeyName = xmlKeyName;
    }

    /**
     * 
     * @return The isoFileName
     */
    @JsonProperty("isoFileName")
    public String getIsoFileName() {
        return isoFileName;
    }

    /**
     * 
     * @param isoFileName
     *            The isoFileName
     */
    @JsonProperty("isoFileName")
    public void setIsoFileName(final String isoFileName) {
        this.isoFileName = isoFileName;
    }

    /**
     * 
     * @return The baselineShortName
     */
    @JsonProperty("baselineShortName")
    public String getBaselineShortName() {
        return baselineShortName;
    }

    /**
     * 
     * @param baselineShortName
     *            The baselineShortName
     */
    @JsonProperty("baselineShortName")
    public void setBaselineShortName(final String baselineShortName) {
        this.baselineShortName = baselineShortName;
    }

    /**
     * 
     * @return The bundleSize
     */
    @JsonProperty("bundleSize")
    public Double getBundleSize() {
        return bundleSize;
    }

    /**
     * 
     * @param bundleSize
     *            The bundleSize
     */
    @JsonProperty("bundleSize")
    public void setBundleSize(final Double bundleSize) {
        this.bundleSize = bundleSize;
    }

    /**
     * 
     * @return The version
     */
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *            The version
     */
    @JsonProperty("version")
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * 
     * @return The releaseDate
     */
    @JsonProperty("releaseDate")
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * 
     * @param releaseDate
     *            The releaseDate
     */
    @JsonProperty("releaseDate")
    public void setReleaseDate(final String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * 
     * @return The supportedOSList
     */
    @JsonProperty("supportedOSList")
    public List<String> getSupportedOSList() {
        return supportedOSList;
    }

    /**
     * 
     * @param supportedOSList
     *            The supportedOSList
     */
    @JsonProperty("supportedOSList")
    public void setSupportedOSList(final List<String> supportedOSList) {
        this.supportedOSList = supportedOSList;
    }

    /**
     * 
     * @return The supportedLanguages
     */
    @JsonProperty("supportedLanguages")
    public String getSupportedLanguages() {
        return supportedLanguages;
    }

    /**
     * 
     * @param supportedLanguages
     *            The supportedLanguages
     */
    @JsonProperty("supportedLanguages")
    public void setSupportedLanguages(final String supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }

    /**
     * 
     * @return The fwComponents
     */
    @JsonProperty("fwComponents")
    public List<FwComponent> getFwComponents() {
        return fwComponents;
    }

    /**
     * 
     * @param fwComponents
     *            The fwComponents
     */
    @JsonProperty("fwComponents")
    public void setFwComponents(final List<FwComponent> fwComponents) {
        this.fwComponents = fwComponents;
    }

    /**
     * 
     * @return The swPackagesFullPath
     */
    @JsonProperty("swPackagesFullPath")
    public String getSwPackagesFullPath() {
        return swPackagesFullPath;
    }

    /**
     * 
     * @param swPackagesFullPath
     *            The swPackagesFullPath
     */
    @JsonProperty("swPackagesFullPath")
    public void setSwPackagesFullPath(final String swPackagesFullPath) {
        this.swPackagesFullPath = swPackagesFullPath;
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
     * @return The lastTaskUri
     */
    @JsonProperty("lastTaskUri")
    public String getLastTaskUri() {
        return lastTaskUri;
    }

    /**
     * 
     * @param lastTaskUri
     *            The lastTaskUri
     */
    @JsonProperty("lastTaskUri")
    public void setLastTaskUri(final String lastTaskUri) {
        this.lastTaskUri = lastTaskUri;
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
     * @return the type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
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
        return new HashCodeBuilder().append(resourceId).append(uuid).append(xmlKeyName).append(isoFileName)
                .append(baselineShortName).append(bundleSize).append(version).append(releaseDate).append(supportedOSList)
                .append(supportedLanguages).append(fwComponents).append(swPackagesFullPath).append(state).append(lastTaskUri)
                .append(description).append(status).append(name).append(eTag).append(created).append(modified).append(category)
                .append(uri).append(type).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FwBaseline) == false) {
            return false;
        }
        final FwBaseline rhs = ((FwBaseline) other);
        return new EqualsBuilder().append(resourceId, rhs.resourceId).append(uuid, rhs.uuid).append(xmlKeyName, rhs.xmlKeyName)
                .append(isoFileName, rhs.isoFileName).append(baselineShortName, rhs.baselineShortName)
                .append(bundleSize, rhs.bundleSize).append(version, rhs.version).append(releaseDate, rhs.releaseDate)
                .append(supportedOSList, rhs.supportedOSList).append(supportedLanguages, rhs.supportedLanguages)
                .append(fwComponents, rhs.fwComponents).append(swPackagesFullPath, rhs.swPackagesFullPath).append(state, rhs.state)
                .append(lastTaskUri, rhs.lastTaskUri).append(description, rhs.description).append(status, rhs.status)
                .append(name, rhs.name).append(eTag, rhs.eTag).append(created, rhs.created).append(modified, rhs.modified)
                .append(category, rhs.category).append(uri, rhs.uri).append(type, rhs.type).isEquals();
    }

}
