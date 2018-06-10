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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.security.login.LoginDetail;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class LoginDetailClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginDetailClientSample.class);

    private final LoginDetailClient loginDetailClient;

    public LoginDetailClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        this.loginDetailClient = oneViewClient.loginDetail();
    }

    private void getLoginDetails() {
        LoginDetail loginDetail = this.loginDetailClient.getLoginDetails();

        LOGGER.info("Login Detail object returned to client: {}", loginDetail.toJsonString());
    }

    public static void main(String[] args) {        
        LoginDetailClientSample client = new LoginDetailClientSample();

        client.getLoginDetails();
    }
}
