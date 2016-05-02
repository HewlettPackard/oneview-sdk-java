/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class FwBaseline implements Serializable {

    private static final long serialVersionUID = 1L;
    private String resourceId;
    private String uuid;
    private String xmlKeyName;
    private String isoFileName;
    private String baselineShortName;
    private Double bundleSize;
    private String version;
    private String releaseDate;
    private List<String> supportedOSList = new ArrayList<String>();
    private String supportedLanguages;
    private List<FwComponent> fwComponents = new ArrayList<FwComponent>();
    private String swPackagesFullPath;
    private String state;
    private String lastTaskUri;
    private String description;
    private String status;
    private String name;
    private String eTag;
    private String created;
    private String modified;
    private String category;
    private String uri;
    private String type;

    /**
     *
     * @return The resourceId
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     *
     * @param resourceId
     *            The resourceId
     */
    public void setResourceId(final String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     *
     * @return The uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     *
     * @param uuid
     *            The uuid
     */
    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    /**
     *
     * @return The xmlKeyName
     */
    public String getXmlKeyName() {
        return xmlKeyName;
    }

    /**
     *
     * @param xmlKeyName
     *            The xmlKeyName
     */
    public void setXmlKeyName(final String xmlKeyName) {
        this.xmlKeyName = xmlKeyName;
    }

    /**
     *
     * @return The isoFileName
     */
    public String getIsoFileName() {
        return isoFileName;
    }

    /**
     *
     * @param isoFileName
     *            The isoFileName
     */
    public void setIsoFileName(final String isoFileName) {
        this.isoFileName = isoFileName;
    }

    /**
     *
     * @return The baselineShortName
     */
    public String getBaselineShortName() {
        return baselineShortName;
    }

    /**
     *
     * @param baselineShortName
     *            The baselineShortName
     */
    public void setBaselineShortName(final String baselineShortName) {
        this.baselineShortName = baselineShortName;
    }

    /**
     *
     * @return The bundleSize
     */
    public Double getBundleSize() {
        return bundleSize;
    }

    /**
     *
     * @param bundleSize
     *            The bundleSize
     */
    public void setBundleSize(final Double bundleSize) {
        this.bundleSize = bundleSize;
    }

    /**
     *
     * @return The version
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version
     *            The version
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     *
     * @return The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     *
     * @param releaseDate
     *            The releaseDate
     */
    public void setReleaseDate(final String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     *
     * @return The supportedOSList
     */
    public List<String> getSupportedOSList() {
        return supportedOSList;
    }

    /**
     *
     * @param supportedOSList
     *            The supportedOSList
     */
    public void setSupportedOSList(final List<String> supportedOSList) {
        this.supportedOSList = supportedOSList;
    }

    /**
     *
     * @return The supportedLanguages
     */
    public String getSupportedLanguages() {
        return supportedLanguages;
    }

    /**
     *
     * @param supportedLanguages
     *            The supportedLanguages
     */
    public void setSupportedLanguages(final String supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }

    /**
     *
     * @return The fwComponents
     */
    public List<FwComponent> getFwComponents() {
        return fwComponents;
    }

    /**
     *
     * @param fwComponents
     *            The fwComponents
     */
    public void setFwComponents(final List<FwComponent> fwComponents) {
        this.fwComponents = fwComponents;
    }

    /**
     *
     * @return The swPackagesFullPath
     */
    public String getSwPackagesFullPath() {
        return swPackagesFullPath;
    }

    /**
     *
     * @param swPackagesFullPath
     *            The swPackagesFullPath
     */
    public void setSwPackagesFullPath(final String swPackagesFullPath) {
        this.swPackagesFullPath = swPackagesFullPath;
    }

    /**
     *
     * @return The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     *            The state
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     *
     * @return The lastTaskUri
     */
    public String getLastTaskUri() {
        return lastTaskUri;
    }

    /**
     *
     * @param lastTaskUri
     *            The lastTaskUri
     */
    public void setLastTaskUri(final String lastTaskUri) {
        this.lastTaskUri = lastTaskUri;
    }

    /**
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *            The description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     *
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *            The status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *            The name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return The eTag
     */
    public String getETag() {
        return eTag;
    }

    /**
     *
     * @param eTag
     *            The eTag
     */
    public void setETag(final String eTag) {
        this.eTag = eTag;
    }

    /**
     *
     * @return The created
     */
    public String getCreated() {
        return created;
    }

    /**
     *
     * @param created
     *            The created
     */
    public void setCreated(final String created) {
        this.created = created;
    }

    /**
     *
     * @return The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     *
     * @param modified
     *            The modified
     */
    public void setModified(final String modified) {
        this.modified = modified;
    }

    /**
     *
     * @return The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     *            The category
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     *
     * @return The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     *
     * @param uri
     *            The uri
     */
    public void setUri(final String uri) {
        this.uri = uri;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
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
