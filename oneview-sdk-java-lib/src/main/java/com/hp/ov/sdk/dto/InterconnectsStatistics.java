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
package com.hp.ov.sdk.dto;

import java.util.List;

import com.google.gson.annotations.Since;

public class InterconnectsStatistics extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String switchId;
    private ModuleStatistics moduleStatistics;
    private List<PortStatistics> portStatistics;
    @Since(200)
    private String resetEpoch;

    public String getSwitchId() {
        return switchId;
    }

    public void setSwitchId(String switchId) {
        this.switchId = switchId;
    }

    public ModuleStatistics getModuleStatistics() {
        return moduleStatistics;
    }

    public void setModuleStatistics(ModuleStatistics moduleStatistics) {
        this.moduleStatistics = moduleStatistics;
    }

    public List<PortStatistics> getPortStatistics() {
        return portStatistics;
    }

    public void setPortStatistics(List<PortStatistics> portStatistics) {
        this.portStatistics = portStatistics;
    }

    public String getResetEpoch() {
        return resetEpoch;
    }

    public void setResetEpoch(String resetEpoch) {
        this.resetEpoch = resetEpoch;
    }
}
