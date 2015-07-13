/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SanProviderRequest implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private List<Property> connectionInfo = new ArrayList<Property>();

    /**
     * 
     * @return
     *         The connectionInfo
     */
    public List<Property> getConnectionInfo()
    {
        return connectionInfo;
    }

    /**
     * 
     * @param connectionInfo
     *        The connectionInfo
     */
    public void setConnectionInfo(List<Property> connectionInfo)
    {
        this.connectionInfo = connectionInfo;
    }

}
