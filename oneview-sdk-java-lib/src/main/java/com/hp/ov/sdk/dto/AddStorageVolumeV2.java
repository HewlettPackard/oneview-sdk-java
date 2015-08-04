/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
