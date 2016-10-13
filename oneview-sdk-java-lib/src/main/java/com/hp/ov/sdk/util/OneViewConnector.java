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

package com.hp.ov.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.ApplianceVersions;
import com.hp.ov.sdk.dto.security.login.LoginInformation;
import com.hp.ov.sdk.dto.security.login.LoginSession;
import com.hp.ov.sdk.exceptions.SDKApiVersionMismatchException;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.rest.client.security.LoginSessionClient;
import com.hp.ov.sdk.rest.client.settings.VersionClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public class OneViewConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(OneViewConnector.class);

    private final RestParams params;
    private final VersionClient versionClient;
    private final LoginSessionClient loginSessionClient;

    public OneViewConnector(RestParams params,
            VersionClient versionClient,
            LoginSessionClient loginSessionClient) {
        this.params = params;
        this.versionClient = versionClient;
        this.loginSessionClient = loginSessionClient;
    }

    public void connect() {
        this.checkVersions();
        this.setupSessionId();
    }

    private void checkVersions() {
        ApplianceVersions versions = this.versionClient.getApplianceVersions();

        int requestedVersion = params.getApiVersion().getValue();
        int applianceMinimumVersion = versions.getMinimumVersion();
        int applianceCurrentVersion = versions.getCurrentVersion();

        LOGGER.info("########### Checking API Version Start ####################");

        if (requestedVersion >= applianceMinimumVersion && requestedVersion <= applianceCurrentVersion) {
            LOGGER.info("API version : You are trying to connect to appliance version: "
                    + requestedVersion  + " and it is matching with the minimum and current version ("
                    + applianceMinimumVersion + " to " + applianceCurrentVersion + ")");

            LOGGER.info("########### Checking API Version End ####################");
            return;
        }
        throw new SDKApiVersionMismatchException(SDKErrorEnum.apiMismatchError,
                null, null, null, SdkConstants.APPLIANCE, null);
    }

    private void setupSessionId() {
        LoginInformation loginInformation = new LoginInformation();

        loginInformation.setUserName(this.params.getUserName());
        loginInformation.setPassword(this.params.getPassword());

        LoginSession loginSession = this.loginSessionClient.authenticate(loginInformation);

        this.params.setSessionId(loginSession.getSessionID());
    }

}
