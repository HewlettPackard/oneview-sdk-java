/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class TemplateProvisioningData implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String capacity;
    private String provisionType;
    private Boolean shareable;
    private String storagePoolUri;

    /**
     * 
     * @return
     *         The capacity
     */
    public String getCapacity()
    {
        return capacity;
    }

    /**
     * 
     * @param capacity
     *        The capacity
     */
    public void setCapacity(String capacity)
    {
        this.capacity = capacity;
    }

    /**
     * 
     * @return
     *         The provisionType
     */
    public String getProvisionType()
    {
        return provisionType;
    }

    /**
     * 
     * @param provisionType
     *        The provisionType
     */
    public void setProvisionType(String provisionType)
    {
        this.provisionType = provisionType;
    }

    /**
     * 
     * @return
     *         The shareable
     */
    public Boolean getShareable()
    {
        return shareable;
    }

    /**
     * 
     * @param shareable
     *        The shareable
     */
    public void setShareable(Boolean shareable)
    {
        this.shareable = shareable;
    }

    /**
     * 
     * @return
     *         The storagePoolUri
     */
    public String getStoragePoolUri()
    {
        return storagePoolUri;
    }

    /**
     * 
     * @param storagePoolUri
     *        The storagePoolUri
     */
    public void setStoragePoolUri(String storagePoolUri)
    {
        this.storagePoolUri = storagePoolUri;
    }

}
