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
package com.hp.ov.sdk.dto.servers.serverprofile;

import java.io.Serializable;
import java.util.List;

public class ServerProfileCompliancePreview implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> automaticUpdates;
    private Boolean isOnlineUpdate;
    private List<String> manualUpdates;
    private String type;

    /**
     * @return the automaticUpdates
     */
    public List<String> getAutomaticUpdates() {
        return automaticUpdates;
    }
    /**
     * @param automaticUpdates the automaticUpdates to set
     */
    public void setAutomaticUpdates(List<String> automaticUpdates) {
        this.automaticUpdates = automaticUpdates;
    }
    /**
     * @return the isOnlineUpdate
     */
    public Boolean getIsOnlineUpdate() {
        return isOnlineUpdate;
    }
    /**
     * @param isOnlineUpdate the isOnlineUpdate to set
     */
    public void setIsOnlineUpdate(Boolean isOnlineUpdate) {
        this.isOnlineUpdate = isOnlineUpdate;
    }
    /**
     * @return the manualUpdates
     */
    public List<String> getManualUpdates() {
        return manualUpdates;
    }
    /**
     * @param manualUpdates the manualUpdates to set
     */
    public void setManualUpdates(List<String> manualUpdates) {
        this.manualUpdates = manualUpdates;
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
