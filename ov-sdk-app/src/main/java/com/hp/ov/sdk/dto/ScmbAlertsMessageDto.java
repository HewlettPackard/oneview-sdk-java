/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class ScmbAlertsMessageDto extends BaseMessagingAlertModel
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private ScmbAlertsResource resource;

    public ScmbAlertsResource getResource()
    {
        return resource;
    }

    public void setResource(ScmbAlertsResource resource)
    {
        this.resource = resource;
    }

}
