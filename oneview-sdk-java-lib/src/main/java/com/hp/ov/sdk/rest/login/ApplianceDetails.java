/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.login;

import com.hp.ov.sdk.dto.ApplianceDetailsDto;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface ApplianceDetails {

    public ApplianceDetailsDto getVersion(RestParams params);
}
