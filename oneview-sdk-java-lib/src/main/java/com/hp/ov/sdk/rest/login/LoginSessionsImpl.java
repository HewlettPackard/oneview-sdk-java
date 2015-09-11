/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/

package com.hp.ov.sdk.rest.login;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.LoginSessionAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.LoginSessionDto;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

@Component
public class LoginSessionsImpl implements LoginSessions {

    private static final Logger logger = LoggerFactory.getLogger(LoginSessionsImpl.class);

    @Autowired
    private HttpRestClient httpRestClient;

    @Autowired
    private LoginSessionAdaptor loginSessionAdaptor;

    @Autowired
    private UrlUtils urlUtils;

    @Override
    public String getLoginSessionId(final RestParams params, final LoginSessionDto dto) {

        logger.info("LoginSessionsImpl : getLoginSessionId : Start");
        String sessionId = null;
        String request = null;
        JSONObject jsonObject = new JSONObject();
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGIN_SESSIONS));

        jsonObject = loginSessionAdaptor.buildJSONObjectFromDto(dto);
        request = httpRestClient.sendRequestToHPOV(params, jsonObject);
        sessionId = loginSessionAdaptor.retrieveSessionId(request);
        logger.debug("LoginSessionsImpl : getLoginSessionId : sessionId : " + sessionId);
        logger.info("LoginSessionsImpl : getLoginSessionId : End");
        return sessionId;
    }

}
