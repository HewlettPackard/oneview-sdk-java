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
package com.hpe.i3s.dto.deployment.artifactsbundle;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class ArtifactsBundle extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private int artifactsCount;
    private String artifactsbundleID;
    private boolean backupService;
    private List<BuildPlanArtifact> buildPlans = new ArrayList<>();
    private String checksum;
    private List<DeploymentPlanArtifact> deploymentPlans = new ArrayList<>();
    private String downloadURI;
    private GoldenImageArtifact goldenimage;
    private boolean importbundle;
    private String lastBackUpDownloadTime;
    private List<PlanScriptArtifact> planScripts = new ArrayList<>();
    private boolean readOnly;
    private boolean recoverBundle;
    private int size;

    /**
     * @return the artifactsCount
     */
    public int getArtifactsCount() {
        return artifactsCount;
    }

    /**
     * @param artifactsCount the artifactsCount to set
     */
    public void setArtifactsCount(int artifactsCount) {
        this.artifactsCount = artifactsCount;
    }

    /**
     * @return the artifactsbundleID
     */
    public String getArtifactsbundleID() {
        return artifactsbundleID;
    }

    /**
     * @param artifactsbundleID the artifactsbundleID to set
     */
    public void setArtifactsbundleID(String artifactsbundleID) {
        this.artifactsbundleID = artifactsbundleID;
    }

    /**
     * @return the backupService
     */
    public boolean isBackupService() {
        return backupService;
    }

    /**
     * @param backupService the backupService to set
     */
    public void setBackupService(boolean backupService) {
        this.backupService = backupService;
    }

    /**
     * @return the buildPlans
     */
    public List<BuildPlanArtifact> getBuildPlans() {
        return buildPlans;
    }

    /**
     * @param buildPlans the buildPlans to set
     */
    public void setBuildPlans(List<BuildPlanArtifact> buildPlans) {
        this.buildPlans = buildPlans;
    }

    /**
     * @return the checksum
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     * @param checksum the checksum to set
     */
    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    /**
     * @return the deploymentPlans
     */
    public List<DeploymentPlanArtifact> getDeploymentPlans() {
        return deploymentPlans;
    }

    /**
     * @param deploymentPlans the deploymentPlans to set
     */
    public void setDeploymentPlans(List<DeploymentPlanArtifact> deploymentPlans) {
        this.deploymentPlans = deploymentPlans;
    }

    /**
     * @return the downloadURI
     */
    public String getDownloadURI() {
        return downloadURI;
    }

    /**
     * @param downloadURI the downloadURI to set
     */
    public void setDownloadURI(String downloadURI) {
        this.downloadURI = downloadURI;
    }

    /**
     * @return the goldenimage
     */
    public GoldenImageArtifact getGoldenimage() {
        return goldenimage;
    }

    /**
     * @param goldenimage the goldenimage to set
     */
    public void setGoldenimage(GoldenImageArtifact goldenimage) {
        this.goldenimage = goldenimage;
    }

    /**
     * @return the importbundle
     */
    public boolean isImportbundle() {
        return importbundle;
    }

    /**
     * @param importbundle the importbundle to set
     */
    public void setImportbundle(boolean importbundle) {
        this.importbundle = importbundle;
    }

    /**
     * @return the lastBackUpDownloadTime
     */
    public String getLastBackUpDownloadTime() {
        return lastBackUpDownloadTime;
    }

    /**
     * @param lastBackUpDownloadTime the lastBackUpDownloadTime to set
     */
    public void setLastBackUpDownloadTime(String lastBackUpDownloadTime) {
        this.lastBackUpDownloadTime = lastBackUpDownloadTime;
    }

    /**
     * @return the planScripts
     */
    public List<PlanScriptArtifact> getPlanScripts() {
        return planScripts;
    }

    /**
     * @param planScripts the planScripts to set
     */
    public void setPlanScripts(List<PlanScriptArtifact> planScripts) {
        this.planScripts = planScripts;
    }

    /**
     * @return the readOnly
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * @param readOnly the readOnly to set
     */
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * @return the recoverBundle
     */
    public boolean isRecoverBundle() {
        return recoverBundle;
    }

    /**
     * @param recoverBundle the recoverBundle to set
     */
    public void setRecoverBundle(boolean recoverBundle) {
        this.recoverBundle = recoverBundle;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
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
