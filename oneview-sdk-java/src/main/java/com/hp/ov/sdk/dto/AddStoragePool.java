/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class AddStoragePool implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String poolName;
    private String storageSystemUri;

    /**
     * 
     * @return The poolName
     */
    public String getPoolName()
    {
        return poolName;
    }

    /**
     * 
     * @param poolName
     *        The poolName
     */
    public void setPoolName(final String poolName)
    {
        this.poolName = poolName;
    }

    /**
     * 
     * @return The storageSystemUri
     */
    public String getStorageSystemUri()
    {
        return storageSystemUri;
    }

    /**
     * 
     * @param storageSystemUri
     *        The storageSystemUri
     */
    public void setStorageSystemUri(final String storageSystemUri)
    {
        this.storageSystemUri = storageSystemUri;
    }

}
