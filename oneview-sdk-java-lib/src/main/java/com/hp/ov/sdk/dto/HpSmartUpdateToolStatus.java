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

package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class HpSmartUpdateToolStatus implements Serializable {

    private InstallState hpSUTInstallState;
    private InstallState installState;
    private String lastOperationTime;
    private String mode;
    private String serviceState;
    private String version;

    public InstallState getHpSUTInstallState() {
        return hpSUTInstallState;
    }

    public void setHpSUTInstallState(InstallState hpSUTInstallState) {
        this.hpSUTInstallState = hpSUTInstallState;
    }

    public InstallState getInstallState() {
        return installState;
    }

    public void setInstallState(InstallState installState) {
        this.installState = installState;
    }

    public String getLastOperationTime() {
        return lastOperationTime;
    }

    public void setLastOperationTime(String lastOperationTime) {
        this.lastOperationTime = lastOperationTime;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getServiceState() {
        return serviceState;
    }

    public void setServiceState(String serviceState) {
        this.serviceState = serviceState;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
