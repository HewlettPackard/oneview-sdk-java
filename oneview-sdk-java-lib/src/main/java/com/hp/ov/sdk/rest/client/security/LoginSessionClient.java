/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
 */

package com.hp.ov.sdk.rest.client.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.dto.security.login.LoginInformation;
import com.hp.ov.sdk.dto.security.login.LoginSession;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.Request;

public class LoginSessionClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSessionClient.class);

    private static final String LOGIN_SESSIONS = "/rest/login-sessions";

    private final BaseClient baseClient;

    public LoginSessionClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Authenticate user with specified credentials. User name, password and an optional
     * directory are specified as input in the request body.
     *
     * @param loginInformation object containing the authentication details.
     *
     * @return {@link LoginSession} in case of a successful login, contains the sessionId
     * for further requests.
     */
    public LoginSession authenticate(LoginInformation loginInformation) {
        LOGGER.info("LoginSessionClient : authenticate : Start");

        Request request = new Request(HttpMethod.POST, LOGIN_SESSIONS, loginInformation);
        LoginSession loginSession = baseClient.executeRequest(request, LoginSession.class);

        LOGGER.info("LoginSessionClient : authenticate : End");

        return loginSession;
    }

}
