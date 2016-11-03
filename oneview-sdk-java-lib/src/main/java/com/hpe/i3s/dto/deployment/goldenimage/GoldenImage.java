/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
 */

package com.hpe.i3s.dto.deployment.goldenimage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hpe.i3s.dto.deployment.DependentArtifacts;

public class GoldenImage extends BaseModelResource {

    private static final long serialVersionUID = 2916995464582105235L;

    private String artifactBundleCategory;
    private String buildPlanCategory;
    private String buildPlanName;
    private String buildPlanUri;
    private String bundleName;
    private String bundleURI;
    private String checkSum;
    private List<DependentArtifacts> dependentArtifacts = new ArrayList<>();
    private String id;
    private Boolean imageCapture;
    private Boolean importedFromBundle;
    private String osVolumeCategory;
    private String osVolumeName;
    private String osVolumeURI;
    private Boolean readOnly;
    private Integer size;

    /**
     * @return the artifactBundleCategory
     */
    public String getArtifactBundleCategory() {
        return artifactBundleCategory;
    }

    /**
     * @param artifactBundleCategory the artifactBundleCategory to set
     */
    public void setArtifactBundleCategory(String artifactBundleCategory) {
        this.artifactBundleCategory = artifactBundleCategory;
    }

    /**
     * @return the buildPlanCategory
     */
    public String getBuildPlanCategory() {
        return buildPlanCategory;
    }

    /**
     * @param buildPlanCategory the buildPlanCategory to set
     */
    public void setBuildPlanCategory(String buildPlanCategory) {
        this.buildPlanCategory = buildPlanCategory;
    }

    /**
     * @return the buildPlanName
     */
    public String getBuildPlanName() {
        return buildPlanName;
    }

    /**
     * @param buildPlanName the buildPlanName to set
     */
    public void setBuildPlanName(String buildPlanName) {
        this.buildPlanName = buildPlanName;
    }

    /**
     * @return the buildPlanUri
     */
    public String getBuildPlanUri() {
        return buildPlanUri;
    }

    /**
     * @param buildPlanUri the buildPlanUri to set
     */
    public void setBuildPlanUri(String buildPlanUri) {
        this.buildPlanUri = buildPlanUri;
    }

    /**
     * @return the bundleName
     */
    public String getBundleName() {
        return bundleName;
    }

    /**
     * @param bundleName the bundleName to set
     */
    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    /**
     * @return the bundleURI
     */
    public String getBundleURI() {
        return bundleURI;
    }

    /**
     * @param bundleURI the bundleURI to set
     */
    public void setBundleURI(String bundleURI) {
        this.bundleURI = bundleURI;
    }

    /**
     * @return the checkSum
     */
    public String getCheckSum() {
        return checkSum;
    }

    /**
     * @param checkSum the checkSum to set
     */
    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    /**
     * @return the dependentArtifacts
     */
    public List<DependentArtifacts> getDependentArtifacts() {
        return dependentArtifacts;
    }

    /**
     * @param dependentArtifacts the dependentArtifacts to set
     */
    public void setDependentArtifacts(List<DependentArtifacts> dependentArtifacts) {
        this.dependentArtifacts = dependentArtifacts;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the imageCapture
     */
    public Boolean getImageCapture() {
        return imageCapture;
    }

    /**
     * @param imageCapture the imageCapture to set
     */
    public void setImageCapture(Boolean imageCapture) {
        this.imageCapture = imageCapture;
    }

    /**
     * @return the importedFromBundle
     */
    public Boolean getImportedFromBundle() {
        return importedFromBundle;
    }

    /**
     * @param importedFromBundle the importedFromBundle to set
     */
    public void setImportedFromBundle(Boolean importedFromBundle) {
        this.importedFromBundle = importedFromBundle;
    }

    /**
     * @return the osVolumeCategory
     */
    public String getOsVolumeCategory() {
        return osVolumeCategory;
    }

    /**
     * @param osVolumeCategory the osVolumeCategory to set
     */
    public void setOsVolumeCategory(String osVolumeCategory) {
        this.osVolumeCategory = osVolumeCategory;
    }

    /**
     * @return the osVolumeName
     */
    public String getOsVolumeName() {
        return osVolumeName;
    }

    /**
     * @param osVolumeName the osVolumeName to set
     */
    public void setOsVolumeName(String osVolumeName) {
        this.osVolumeName = osVolumeName;
    }

    /**
     * @return the osVolumeURI
     */
    public String getOsVolumeURI() {
        return osVolumeURI;
    }

    /**
     * @param osVolumeURI the osVolumeURI to set
     */
    public void setOsVolumeURI(String osVolumeURI) {
        this.osVolumeURI = osVolumeURI;
    }

    /**
     * @return the readOnly
     */
    public Boolean getReadOnly() {
        return readOnly;
    }

    /**
     * @param readOnly the readOnly to set
     */
    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * @return the size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
