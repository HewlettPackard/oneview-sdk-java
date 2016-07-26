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
package com.hp.ov.sdk.dto.servers;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class OSDeploymentSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    private DeploymentModeSettings deploymentModeSettings;
    private boolean manageOSDeployment;

    /**
     * @return the deploymentModeSettings
     */
    public DeploymentModeSettings getDeploymentModeSettings() {
        return deploymentModeSettings;
    }

    /**
     * @param deploymentModeSettings the deploymentModeSettings to set
     */
    public void setDeploymentModeSettings(DeploymentModeSettings deploymentModeSettings) {
        this.deploymentModeSettings = deploymentModeSettings;
    }

    /**
     * @return the manageOSDeployment
     */
    public boolean isManageOSDeployment() {
        return manageOSDeployment;
    }

    /**
     * @param manageOSDeployment the manageOSDeployment to set
     */
    public void setManageOSDeployment(boolean manageOSDeployment) {
        this.manageOSDeployment = manageOSDeployment;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(deploymentModeSettings)
                .append(manageOSDeployment)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OSDeploymentSettings) == false) {
            return false;
        }
        final OSDeploymentSettings rhs = ((OSDeploymentSettings) other);
        return new EqualsBuilder()
                .append(deploymentModeSettings, rhs.deploymentModeSettings)
                .append(manageOSDeployment, rhs.manageOSDeployment)
                .isEquals();
    }

}
