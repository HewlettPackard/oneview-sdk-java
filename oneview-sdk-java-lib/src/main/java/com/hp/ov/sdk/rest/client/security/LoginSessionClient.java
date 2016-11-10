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

import com.hp.ov.sdk.dto.security.login.LoginInformation;
import com.hp.ov.sdk.dto.security.login.LoginSession;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;

@Api(LoginSessionClient.LOGIN_SESSIONS_URI)
public interface LoginSessionClient {

    String LOGIN_SESSIONS_URI = "/rest/login-sessions";

    /**
     * Authenticate user with specified credentials. User name, password and an optional
     * directory are specified as input in the request body.
     *
     * @param loginInformation object containing the authentication details.
     *
     * @return {@link LoginSession} in case of a successful login, contains the sessionId
     * for further requests.
     */
    @Endpoint(method = HttpMethod.POST)
    LoginSession authenticate(@BodyParam LoginInformation loginInformation);

}
