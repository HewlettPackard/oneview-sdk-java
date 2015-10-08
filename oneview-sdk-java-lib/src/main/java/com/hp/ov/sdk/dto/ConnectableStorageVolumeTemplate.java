/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConnectableStorageVolumeTemplate implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private List<String> availableNetworks = new ArrayList<String>();
    private TemplateProvisioningData provisioning;
    private String storageSystemName;
    private String storageSystemUri;
    private String templateName;
    private String templateUri;

    /**
     * 
     * @return The availableNetworks
     */
    public List<String> getAvailableNetworks() {
        return availableNetworks;
    }

    /**
     * 
     * @param availableNetworks
     *            The availableNetworks
     */
    public void setAvailableNetworks(final List<String> availableNetworks) {
        this.availableNetworks = availableNetworks;
    }

    /**
     * 
     * @return The provisioning
     */
    public TemplateProvisioningData getProvisioning() {
        return provisioning;
    }

    /**
     * 
     * @param provisioning
     *            The provisioning
     */
    public void setProvisioning(final TemplateProvisioningData provisioning) {
        this.provisioning = provisioning;
    }

    /**
     * 
     * @return The storageSystemName
     */
    public String getStorageSystemName() {
        return storageSystemName;
    }

    /**
     * 
     * @param storageSystemName
     *            The storageSystemName
     */
    public void setStorageSystemName(final String storageSystemName) {
        this.storageSystemName = storageSystemName;
    }

    /**
     * 
     * @return The storageSystemUri
     */
    public String getStorageSystemUri() {
        return storageSystemUri;
    }

    /**
     * 
     * @param storageSystemUri
     *            The storageSystemUri
     */
    public void setStorageSystemUri(final String storageSystemUri) {
        this.storageSystemUri = storageSystemUri;
    }

    /**
     * 
     * @return The templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * 
     * @param templateName
     *            The templateName
     */
    public void setTemplateName(final String templateName) {
        this.templateName = templateName;
    }

    /**
     * 
     * @return The templateUri
     */
    public String getTemplateUri() {
        return templateUri;
    }

    /**
     * 
     * @param templateUri
     *            The templateUri
     */
    public void setTemplateUri(final String templateUri) {
        this.templateUri = templateUri;
    }

}
