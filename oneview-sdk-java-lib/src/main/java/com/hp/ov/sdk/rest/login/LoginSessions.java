/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package com.hp.ov.sdk.rest.login;

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
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginSessions {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSessions.class);

    private LoginSessionAdaptor loginSessionAdaptor;

    public LoginSessions(LoginSessionAdaptor loginSessionAdaptor) {
        this.loginSessionAdaptor = loginSessionAdaptor;
    }

    public String getLoginSessionId(final RestParams params) {
        LOGGER.info("LoginSessions : getLoginSessionId : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGIN_SESSIONS));

        JSONObject jsonObject = loginSessionAdaptor.buildJSONObjectFromDto(loginSessionAdaptor.buildDto(params));
        String request = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        String sessionId = loginSessionAdaptor.retrieveSessionId(request);

        LOGGER.debug("LoginSessions : getLoginSessionId : sessionId : " + sessionId);
        LOGGER.info("LoginSessions : getLoginSessionId : End");

        return sessionId;
    }

}
