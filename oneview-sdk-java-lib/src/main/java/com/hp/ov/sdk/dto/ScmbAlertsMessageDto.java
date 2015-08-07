/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class ScmbAlertsMessageDto extends BaseMessagingAlertModel {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private ScmbAlertsResource resource;

    public ScmbAlertsResource getResource() {
        return resource;
    }

    public void setResource(final ScmbAlertsResource resource) {
        this.resource = resource;
    }

}
