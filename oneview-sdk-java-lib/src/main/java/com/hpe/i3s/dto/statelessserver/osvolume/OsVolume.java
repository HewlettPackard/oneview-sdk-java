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
package com.hpe.i3s.dto.statelessserver.osvolume;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class OsVolume extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private List<DependentArtifacts> dependentArtifacts = new ArrayList<>();
    private String deploymentClusterUri;
    private String goldenVolumeUri;
    private String oeVolumeIQN;
    private String oeVolumeId;
    private String oeVolumeIp;
    private int size;
    private String statelessServerUri;

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
     * @return the deploymentClusterUri
     */
    public String getDeploymentClusterUri() {
        return deploymentClusterUri;
    }

    /**
     * @param deploymentClusterUri the deploymentClusterUri to set
     */
    public void setDeploymentClusterUri(String deploymentClusterUri) {
        this.deploymentClusterUri = deploymentClusterUri;
    }

    /**
     * @return the goldenVolumeUri
     */
    public String getGoldenVolumeUri() {
        return goldenVolumeUri;
    }

    /**
     * @param goldenVolumeUri the goldenVolumeUri to set
     */
    public void setGoldenVolumeUri(String goldenVolumeUri) {
        this.goldenVolumeUri = goldenVolumeUri;
    }

    /**
     * @return the oeVolumeIQN
     */
    public String getOeVolumeIQN() {
        return oeVolumeIQN;
    }

    /**
     * @param oeVolumeIQN the oeVolumeIQN to set
     */
    public void setOeVolumeIQN(String oeVolumeIQN) {
        this.oeVolumeIQN = oeVolumeIQN;
    }

    /**
     * @return the oeVolumeId
     */
    public String getOeVolumeId() {
        return oeVolumeId;
    }

    /**
     * @param oeVolumeId the oeVolumeId to set
     */
    public void setOeVolumeId(String oeVolumeId) {
        this.oeVolumeId = oeVolumeId;
    }

    /**
     * @return the oeVolumeIp
     */
    public String getOeVolumeIp() {
        return oeVolumeIp;
    }

    /**
     * @param oeVolumeIp the oeVolumeIp to set
     */
    public void setOeVolumeIp(String oeVolumeIp) {
        this.oeVolumeIp = oeVolumeIp;
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

    /**
     * @return the statelessServerUri
     */
    public String getStatelessServerUri() {
        return statelessServerUri;
    }

    /**
     * @param statelessServerUri the statelessServerUri to set
     */
    public void setStatelessServerUri(String statelessServerUri) {
        this.statelessServerUri = statelessServerUri;
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
