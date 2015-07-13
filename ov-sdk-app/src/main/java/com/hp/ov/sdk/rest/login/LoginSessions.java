/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.login;

import com.hp.ov.sdk.dto.LoginSessionDto;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface LoginSessions
{

    public String getLoginSessionId(RestParams param, final LoginSessionDto dto);
    //TODO - add other API's

}
