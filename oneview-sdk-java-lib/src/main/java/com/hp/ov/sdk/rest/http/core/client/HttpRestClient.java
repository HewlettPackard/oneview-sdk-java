/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.http.core.client;

import org.json.JSONObject;

public interface HttpRestClient {

    /**
     * module for sending web service requests.
     */
    public String sendRequestToHPOV(final RestParams params, final JSONObject jsonObject);

}
