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

package com.hp.ov.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.exceptions.SDKPropertiesFileException;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.http.core.client.SDKConfiguration;

public class OneViewClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(OneViewClientSample.class);

    private static final String ONEVIEW_JAVA_SDK_CONFIG_PROPERTIES_FILE = "oneview_java_sdk_config.properties";

    private SDKConfiguration config;
    private OneViewClient client;

    public OneViewClient getOneViewClient() {
        if (client == null) {
            try {
                client = new OneViewClient(this.getSDKConfiguration());
            } catch (Exception e) {
                LOGGER.error("An error occurred while creating a client instance", e);
            }
        }
        return client;
    }

    public SDKConfiguration getSDKConfiguration() {
        if (config == null) {
            try {
                config = SDKConfiguration.fromFile(ONEVIEW_JAVA_SDK_CONFIG_PROPERTIES_FILE);
            } catch (SDKPropertiesFileException e) {
                LOGGER.error("An error occurred while loading configuration file", e);
            }
        }
        return this.config;
    }

}
