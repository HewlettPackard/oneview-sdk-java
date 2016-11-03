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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CreateArtifactsBundle implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<InputArtifacts> buildPlans = new ArrayList<>();
    private List<String> deploymentClusters = new ArrayList<>();
    private List<String> deploymentGroups = new ArrayList<>();
    private List<InputArtifacts> deploymentPlans = new ArrayList<>();
    private String description;
    private List<InputArtifacts> goldenImages = new ArrayList<>();
    private List<String> goldenVolumes = new ArrayList<>();
    private List<String> i3sAppliance = new ArrayList<>();
    private String name;
    private List<String> oeVolumes = new ArrayList<>();
    private List<InputArtifacts> planScripts = new ArrayList<>();
    private List<String> statelessServers = new ArrayList<>();

    /**
     * @return the buildPlans
     */
    public List<InputArtifacts> getBuildPlans() {
        return buildPlans;
    }

    /**
     * @param buildPlans the buildPlans to set
     */
    public void setBuildPlans(List<InputArtifacts> buildPlans) {
        this.buildPlans = buildPlans;
    }

    /**
     * @return the deploymentClusters
     */
    public List<String> getDeploymentClusters() {
        return deploymentClusters;
    }

    /**
     * @param deploymentClusters the deploymentClusters to set
     */
    public void setDeploymentClusters(List<String> deploymentClusters) {
        this.deploymentClusters = deploymentClusters;
    }

    /**
     * @return the deploymentGroups
     */
    public List<String> getDeploymentGroups() {
        return deploymentGroups;
    }

    /**
     * @param deploymentGroups the deploymentGroups to set
     */
    public void setDeploymentGroups(List<String> deploymentGroups) {
        this.deploymentGroups = deploymentGroups;
    }

    /**
     * @return the deploymentPlans
     */
    public List<InputArtifacts> getDeploymentPlans() {
        return deploymentPlans;
    }

    /**
     * @param deploymentPlans the deploymentPlans to set
     */
    public void setDeploymentPlans(List<InputArtifacts> deploymentPlans) {
        this.deploymentPlans = deploymentPlans;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the goldenImages
     */
    public List<InputArtifacts> getGoldenImages() {
        return goldenImages;
    }

    /**
     * @param goldenImages the goldenImages to set
     */
    public void setGoldenImages(List<InputArtifacts> goldenImages) {
        this.goldenImages = goldenImages;
    }

    /**
     * @return the goldenVolumes
     */
    public List<String> getGoldenVolumes() {
        return goldenVolumes;
    }

    /**
     * @param goldenVolumes the goldenVolumes to set
     */
    public void setGoldenVolumes(List<String> goldenVolumes) {
        this.goldenVolumes = goldenVolumes;
    }

    /**
     * @return the i3sAppliance
     */
    public List<String> getI3sAppliance() {
        return i3sAppliance;
    }

    /**
     * @param i3sAppliance the i3sAppliance to set
     */
    public void setI3sAppliance(List<String> i3sAppliance) {
        this.i3sAppliance = i3sAppliance;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the oeVolumes
     */
    public List<String> getOeVolumes() {
        return oeVolumes;
    }

    /**
     * @param oeVolumes the oeVolumes to set
     */
    public void setOeVolumes(List<String> oeVolumes) {
        this.oeVolumes = oeVolumes;
    }

    /**
     * @return the planScripts
     */
    public List<InputArtifacts> getPlanScripts() {
        return planScripts;
    }

    /**
     * @param planScripts the planScripts to set
     */
    public void setPlanScripts(List<InputArtifacts> planScripts) {
        this.planScripts = planScripts;
    }

    /**
     * @return the statelessServers
     */
    public List<String> getStatelessServers() {
        return statelessServers;
    }

    /**
     * @param statelessServers the statelessServers to set
     */
    public void setStatelessServers(List<String> statelessServers) {
        this.statelessServers = statelessServers;
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
