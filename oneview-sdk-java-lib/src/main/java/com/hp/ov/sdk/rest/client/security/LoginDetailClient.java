/*
 * (C) Copyright 2017 Hewlett Packard Enterprise Development LP
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

import com.hp.ov.sdk.dto.security.login.LoginDetail;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.Endpoint;

@Api(LoginDetailClient.LOGIN_DETAILS_URI)
public interface LoginDetailClient {

    String LOGIN_DETAILS_URI = "/rest/logindetails";

    /**
     * Returns the list of login details. The response contains the allowLocalLogin,
     * list of configuredLoginDomain, loginMessage.
     *
     * @return {@link LoginDetail} containing the list of login details.
     */
    @Endpoint
    LoginDetail getLoginDetails();
    
}
