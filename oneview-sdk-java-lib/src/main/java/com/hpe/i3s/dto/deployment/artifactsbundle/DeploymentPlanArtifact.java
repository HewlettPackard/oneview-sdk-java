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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DeploymentPlanArtifact implements Serializable {

    private static final long serialVersionUID = 1L;

    private String deploymentplanName;
    private String description;
    private String dpId;
    private String goldenImageName;
    private String oebpName;
    private boolean readOnly;

    /**
     * @return the deploymentplanName
     */
    public String getDeploymentplanName() {
        return deploymentplanName;
    }

    /**
     * @param deploymentplanName the deploymentplanName to set
     */
    public void setDeploymentplanName(String deploymentplanName) {
        this.deploymentplanName = deploymentplanName;
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
     * @return the dpId
     */
    public String getDpId() {
        return dpId;
    }

    /**
     * @param dpId the dpId to set
     */
    public void setDpId(String dpId) {
        this.dpId = dpId;
    }

    /**
     * @return the goldenImageName
     */
    public String getGoldenImageName() {
        return goldenImageName;
    }

    /**
     * @param goldenImageName the goldenImageName to set
     */
    public void setGoldenImageName(String goldenImageName) {
        this.goldenImageName = goldenImageName;
    }

    /**
     * @return the oebpName
     */
    public String getOebpName() {
        return oebpName;
    }

    /**
     * @param oebpName the oebpName to set
     */
    public void setOebpName(String oebpName) {
        this.oebpName = oebpName;
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
