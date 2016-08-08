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
package com.hp.ov.sdk.dto.firmware;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;

public class FwBaseline extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String baselineShortName;
    private Double bundleSize;
    private List<FwComponent> fwComponents = new ArrayList<FwComponent>();
    @Since(300)
    private List<FwHotfixBundle> hotfixes = new ArrayList<FwHotfixBundle>();
    private String isoFileName;
    private String lastTaskUri;
    @Since(300)
    private List<FwParentBundle> parentBundle = new ArrayList<FwParentBundle>();
    private String releaseDate;
    private String resourceId;
    private String supportedLanguages;
    private List<String> supportedOSList = new ArrayList<String>();
    private String swPackagesFullPath;
    private String uuid;
    private String version;
    private String xmlKeyName;

    /**
     * @return the baselineShortName
     */
    public String getBaselineShortName() {
        return baselineShortName;
    }

    /**
     * @param baselineShortName the baselineShortName to set
     */
    public void setBaselineShortName(String baselineShortName) {
        this.baselineShortName = baselineShortName;
    }

    /**
     * @return the bundleSize
     */
    public Double getBundleSize() {
        return bundleSize;
    }

    /**
     * @param bundleSize the bundleSize to set
     */
    public void setBundleSize(Double bundleSize) {
        this.bundleSize = bundleSize;
    }

    /**
     * @return the fwComponents
     */
    public List<FwComponent> getFwComponents() {
        return fwComponents;
    }

    /**
     * @param fwComponents the fwComponents to set
     */
    public void setFwComponents(List<FwComponent> fwComponents) {
        this.fwComponents = fwComponents;
    }

    /**
     * @return the hotfixes
     */
    public List<FwHotfixBundle> getHotfixes() {
        return hotfixes;
    }

    /**
     * @param hotfixes the hotfixes to set
     */
    public void setHotfixes(List<FwHotfixBundle> hotfixes) {
        this.hotfixes = hotfixes;
    }

    /**
     * @return the isoFileName
     */
    public String getIsoFileName() {
        return isoFileName;
    }

    /**
     * @param isoFileName the isoFileName to set
     */
    public void setIsoFileName(String isoFileName) {
        this.isoFileName = isoFileName;
    }

    /**
     * @return the lastTaskUri
     */
    public String getLastTaskUri() {
        return lastTaskUri;
    }

    /**
     * @param lastTaskUri the lastTaskUri to set
     */
    public void setLastTaskUri(String lastTaskUri) {
        this.lastTaskUri = lastTaskUri;
    }

    /**
     * @return the parentBundle
     */
    public List<FwParentBundle> getParentBundle() {
        return parentBundle;
    }

    /**
     * @param parentBundle the parentBundle to set
     */
    public void setParentBundle(List<FwParentBundle> parentBundle) {
        this.parentBundle = parentBundle;
    }

    /**
     * @return the releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate the releaseDate to set
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return the resourceId
     */
    @Override
    public String getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId the resourceId to set
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * @return the supportedLanguages
     */
    public String getSupportedLanguages() {
        return supportedLanguages;
    }

    /**
     * @param supportedLanguages the supportedLanguages to set
     */
    public void setSupportedLanguages(String supportedLanguages) {
        this.supportedLanguages = supportedLanguages;
    }

    /**
     * @return the supportedOSList
     */
    public List<String> getSupportedOSList() {
        return supportedOSList;
    }

    /**
     * @param supportedOSList the supportedOSList to set
     */
    public void setSupportedOSList(List<String> supportedOSList) {
        this.supportedOSList = supportedOSList;
    }

    /**
     * @return the swPackagesFullPath
     */
    public String getSwPackagesFullPath() {
        return swPackagesFullPath;
    }

    /**
     * @param swPackagesFullPath the swPackagesFullPath to set
     */
    public void setSwPackagesFullPath(String swPackagesFullPath) {
        this.swPackagesFullPath = swPackagesFullPath;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the xmlKeyName
     */
    public String getXmlKeyName() {
        return xmlKeyName;
    }

    /**
     * @param xmlKeyName the xmlKeyName to set
     */
    public void setXmlKeyName(String xmlKeyName) {
        this.xmlKeyName = xmlKeyName;
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof FwBaseline);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof FwBaseline) {
            FwBaseline that = (FwBaseline) obj;

            return new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(baselineShortName, that.baselineShortName)
                    .append(bundleSize, that.bundleSize)
                    .append(fwComponents, that.fwComponents)
                    .append(hotfixes, that.hotfixes)
                    .append(isoFileName, that.isoFileName)
                    .append(lastTaskUri, that.lastTaskUri)
                    .append(parentBundle, that.parentBundle)
                    .append(releaseDate, that.releaseDate)
                    .append(resourceId, that.resourceId)
                    .append(supportedLanguages, that.supportedLanguages)
                    .append(supportedOSList, that.supportedOSList)
                    .append(swPackagesFullPath, that.swPackagesFullPath)
                    .append(uuid, that.uuid)
                    .append(version, that.version)
                    .append(xmlKeyName, that.xmlKeyName)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(baselineShortName)
                .append(bundleSize)
                .append(fwComponents)
                .append(hotfixes)
                .append(isoFileName)
                .append(lastTaskUri)
                .append(parentBundle)
                .append(releaseDate)
                .append(resourceId)
                .append(supportedLanguages)
                .append(supportedOSList)
                .append(swPackagesFullPath)
                .append(uuid)
                .append(version)
                .append(xmlKeyName)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
