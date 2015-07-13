/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.http.core.client;

import org.json.JSONObject;

public interface HttpRestClient
{

    /**
     * module for sending web service requests.
     */
    public String sendRequestToHPOV(final RestParams params, final JSONObject jsonObject);

}
