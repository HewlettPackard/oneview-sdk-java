/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class MsmbAlertsMessageDto extends BaseMessagingAlertModel
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private MsmbAlertsResource resource;

    public MsmbAlertsResource getResource()
    {
        return resource;
    }

    public void setResource(final MsmbAlertsResource resource)
    {
        this.resource = resource;
    }

}
