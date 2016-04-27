/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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

import java.util.ArrayList;
import java.util.List;

public class SanProviderResponse extends BaseModelResource {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private List<Property> defaultConnectionInfo = new ArrayList<Property>();
    private String deviceManagersUri;
    private String displayName;
    private Boolean isInternal;
    private String sanType;

    /**
     * 
     * @return The defaultConnectionInfo
     */
    public List<Property> getDefaultConnectionInfo() {
        return defaultConnectionInfo;
    }

    /**
     * 
     * @param defaultConnectionInfo
     *            The defaultConnectionInfo
     */
    public void setDefaultConnectionInfo(List<Property> defaultConnectionInfo) {
        this.defaultConnectionInfo = defaultConnectionInfo;
    }

    /**
     * 
     * @return The deviceManagersUri
     */
    public String getDeviceManagersUri() {
        return deviceManagersUri;
    }

    /**
     * 
     * @param deviceManagersUri
     *            The deviceManagersUri
     */
    public void setDeviceManagersUri(String deviceManagersUri) {
        this.deviceManagersUri = deviceManagersUri;
    }

    /**
     * 
     * @return The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 
     * @param displayName
     *            The displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * 
     * @return The isInternal
     */
    public Boolean getIsInternal() {
        return isInternal;
    }

    /**
     * 
     * @param isInternal
     *            The isInternal
     */
    public void setIsInternal(Boolean isInternal) {
        this.isInternal = isInternal;
    }

    /**
     * 
     * @return The sanType
     */
    public String getSanType() {
        return sanType;
    }

    /**
     * 
     * @param sanType
     *            The sanType
     */
    public void setSanType(String sanType) {
        this.sanType = sanType;
    }

}
