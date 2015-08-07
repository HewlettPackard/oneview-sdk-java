/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class MsmbAlertsMessageDto extends BaseMessagingAlertModel {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private MsmbAlertsResource resource;

    public MsmbAlertsResource getResource() {
        return resource;
    }

    public void setResource(final MsmbAlertsResource resource) {
        this.resource = resource;
    }

}
