/*
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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

package com.hp.ov.sdk.util.samples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.exceptions.SDKPropertiesFileException;
import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.http.core.client.SDKConfiguration;

public class HPOneViewCredential {

    private static final Logger LOGGER = LoggerFactory.getLogger(HPOneViewCredential.class);

    private static final String ONEVIEW_JAVA_SDK_CONFIG_PROPERTIES_FILE = "oneview_java_sdk_config.properties";

    private SDKConfiguration sdkConfiguration;

    public HPOneViewCredential() {
        try {
            sdkConfiguration = SDKConfiguration.fromFile(ONEVIEW_JAVA_SDK_CONFIG_PROPERTIES_FILE);
        } catch (SDKPropertiesFileException e) {
            LOGGER.error("Error loading configuration file.", e);
        }
    }

    public RestParams createRestParams() {
        return new RestParams(sdkConfiguration);
    }

    public HttpSslProperties createHttpSslProperties() {
        return new HttpSslProperties(sdkConfiguration);
    }

}
