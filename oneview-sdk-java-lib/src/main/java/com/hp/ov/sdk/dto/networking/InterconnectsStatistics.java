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
package com.hp.ov.sdk.dto.networking;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.PortStatistics;

public class InterconnectsStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    private ModuleStatistics moduleStatistics;
    private List<PortStatistics> portStatistics;
    @Since(200)
    private String resetEpoch;
    private String switchId;
    private String type;

    /**
     * @return the moduleStatistics
     */
    public ModuleStatistics getModuleStatistics() {
        return moduleStatistics;
    }

    /**
     * @param moduleStatistics the moduleStatistics to set
     */
    public void setModuleStatistics(ModuleStatistics moduleStatistics) {
        this.moduleStatistics = moduleStatistics;
    }

    /**
     * @return the portStatistics
     */
    public List<PortStatistics> getPortStatistics() {
        return portStatistics;
    }

    /**
     * @param portStatistics the portStatistics to set
     */
    public void setPortStatistics(List<PortStatistics> portStatistics) {
        this.portStatistics = portStatistics;
    }

    /**
     * @return the resetEpoch
     */
    public String getResetEpoch() {
        return resetEpoch;
    }

    /**
     * @param resetEpoch the resetEpoch to set
     */
    public void setResetEpoch(String resetEpoch) {
        this.resetEpoch = resetEpoch;
    }

    /**
     * @return the switchId
     */
    public String getSwitchId() {
        return switchId;
    }

    /**
     * @param switchId the switchId to set
     */
    public void setSwitchId(String switchId) {
        this.switchId = switchId;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
