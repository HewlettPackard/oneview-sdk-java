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
package com.hp.ov.sdk.dto.servers.enclosuregroup;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DeploymentModeSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    private String deploymentMode;
    private String deploymentNetworkUri;

    /**
     * @return the deploymentMode
     */
    public String getDeploymentMode() {
        return deploymentMode;
    }

    /**
     * @param deploymentMode the deploymentMode to set
     */
    public void setDeploymentMode(String deploymentMode) {
        this.deploymentMode = deploymentMode;
    }

    /**
     * @return the deploymentNetworkUri
     */
    public String getDeploymentNetworkUri() {
        return deploymentNetworkUri;
    }

    /**
     * @param deploymentNetworkUri the deploymentNetworkUri to set
     */
    public void setDeploymentNetworkUri(String deploymentNetworkUri) {
        this.deploymentNetworkUri = deploymentNetworkUri;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(deploymentMode)
                .append(deploymentNetworkUri)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DeploymentModeSettings) == false) {
            return false;
        }
        final DeploymentModeSettings rhs = ((DeploymentModeSettings) other);
        return new EqualsBuilder()
                .append(deploymentMode, rhs.deploymentMode)
                .append(deploymentNetworkUri, rhs.deploymentNetworkUri)
                .isEquals();
    }
}
