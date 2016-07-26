/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto.servers.logicalenclosure;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.servers.OSDeploymentSettings;

public class DeploymentManagerSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    private String deploymentClusterUri;
    private OSDeploymentSettings osDeploymentSettings;

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
     * @return the osDeploymentSettings
     */
    public OSDeploymentSettings getOsDeploymentSettings() {
        return osDeploymentSettings;
    }

    /**
     * @param osDeploymentSettings the osDeploymentSettings to set
     */
    public void setOsDeploymentSettings(OSDeploymentSettings osDeploymentSettings) {
        this.osDeploymentSettings = osDeploymentSettings;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(deploymentClusterUri)
                .append(osDeploymentSettings)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DeploymentManagerSettings) == false) {
            return false;
        }
        final DeploymentManagerSettings rhs = ((DeploymentManagerSettings) other);
        return new EqualsBuilder()
                .append(deploymentClusterUri, rhs.deploymentClusterUri)
                .append(osDeploymentSettings, rhs.osDeploymentSettings)
                .isEquals();
    }
}
