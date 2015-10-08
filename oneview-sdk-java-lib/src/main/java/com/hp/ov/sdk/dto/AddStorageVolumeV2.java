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

public class AddStorageVolumeV2 implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String description;
    private Boolean isPermanent;
    private String name;
    private StorageVolumeProvisioningParameters provisioningParameters;
    private String storageSystemUri;
    private String templateUri;
    private String wwn;
    private String type;

    /**
     * 
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *            The description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * 
     * @return The isPermanent
     */
    public Boolean getIsPermanent() {
        return isPermanent;
    }

    /**
     * 
     * @param isPermanent
     *            The isPermanent
     */
    public void setIsPermanent(final Boolean isPermanent) {
        this.isPermanent = isPermanent;
    }

    /**
     * 
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *            The name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 
     * @return The provisioningParameters
     */
    public StorageVolumeProvisioningParameters getProvisioningParameters() {
        return provisioningParameters;
    }

    /**
     * 
     * @param provisioningParameters
     *            The provisioningParameters
     */
    public void setProvisioningParameters(final StorageVolumeProvisioningParameters provisioningParameters) {
        this.provisioningParameters = provisioningParameters;
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

    /**
     * 
     * @return The wwn
     */
    public String getWwn() {
        return wwn;
    }

    /**
     * 
     * @param wwn
     *            The wwn
     */
    public void setWwn(final String wwn) {
        this.wwn = wwn;
    }

    /**
     * 
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *            The type
     */
    public void setType(final String type) {
        this.type = type;
    }

}
