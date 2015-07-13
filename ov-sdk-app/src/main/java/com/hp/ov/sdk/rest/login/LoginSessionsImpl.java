/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/

package com.hp.ov.sdk.rest.login;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
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
import com.hp.ov.sdk.util.SdkUtils;

@Component
public class LoginSessionsImpl implements LoginSessions
{

    private static final Logger logger = LoggerFactory.getLogger(LoginSessionsImpl.class);

    @Autowired
    private HttpRestClient httpRestClient;

    @Autowired
    private LoginSessionAdaptor loginSessionAdaptor;

    @Autowired
    private SdkUtils sdkUtils;

    //private JSONObject jsonObject;

    //TODO - plan to move this to central place
    public void init()
    {
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "src/main/resources/rest-client-context.xml");
        httpRestClient = (HttpRestClient) ctx.getBean("httpRestClientImpl");
    }

    @Override
    public String getLoginSessionId(RestParams params, final LoginSessionDto dto)
    {

        logger.info("LoginSessionsImpl : getLoginSessionId : Start");
        String sessionId = null;
        String request = null;
        JSONObject jsonObject = new JSONObject();
        //validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        //set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(), ResourceUris.LOGIN_SESSIONS));

        jsonObject = loginSessionAdaptor.buildJSONObjectFromDto(dto);
        request = httpRestClient.sendRequestToHPOV(params, jsonObject);
        sessionId = loginSessionAdaptor.retrieveSessionId(request);
        logger.debug("LoginSessionsImpl : getLoginSessionId : sessionId : " + sessionId);
        logger.info("LoginSessionsImpl : getLoginSessionId : End");
        return sessionId;
    }

    public HttpRestClient getRestClient()
    {
        return httpRestClient;
    }

    public void setRestClient(HttpRestClient httpRestClient)
    {
        this.httpRestClient = httpRestClient;
    }

}
