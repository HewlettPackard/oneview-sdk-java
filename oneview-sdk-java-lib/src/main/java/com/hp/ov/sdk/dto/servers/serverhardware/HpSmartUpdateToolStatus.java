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

package com.hp.ov.sdk.dto.servers.serverhardware;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class HpSmartUpdateToolStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private InstallState hpSUTInstallState;
    private InstallState installState;
    private String lastOperationTime;
    private String mode;
    private String serviceState;
    private String version;

    /**
     * @return the hpSUTInstallState
     */
    public InstallState getHpSUTInstallState() {
        return hpSUTInstallState;
    }

    /**
     * @param hpSUTInstallState the hpSUTInstallState to set
     */
    public void setHpSUTInstallState(InstallState hpSUTInstallState) {
        this.hpSUTInstallState = hpSUTInstallState;
    }

    /**
     * @return the installState
     */
    public InstallState getInstallState() {
        return installState;
    }

    /**
     * @param installState the installState to set
     */
    public void setInstallState(InstallState installState) {
        this.installState = installState;
    }

    /**
     * @return the lastOperationTime
     */
    public String getLastOperationTime() {
        return lastOperationTime;
    }

    /**
     * @param lastOperationTime the lastOperationTime to set
     */
    public void setLastOperationTime(String lastOperationTime) {
        this.lastOperationTime = lastOperationTime;
    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return the serviceState
     */
    public String getServiceState() {
        return serviceState;
    }

    /**
     * @param serviceState the serviceState to set
     */
    public void setServiceState(String serviceState) {
        this.serviceState = serviceState;
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
