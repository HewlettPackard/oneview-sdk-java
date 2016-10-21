/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.http.core.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.hp.ov.sdk.exceptions.SDKPropertiesFileException;

public class SDKConfiguration {

    private static final int DEFAULT_MESSAGE_BUS_PORT = 5671;
    private static final ApiVersion DEFAULT_API_VERSION = ApiVersion.V_300;
    private static final String DEFAULT_DOMAIN = "LOCAL";

    private static final String[] MANDATORY_FIELDS = {
            SDKConfiguration.USERNAME,
            SDKConfiguration.PASSWORD,
            SDKConfiguration.HOSTNAME};

    // Message bus properties keys
    private static final String MESSAGE_BUS_PORT = "messagebus.port";

    // Trust store properties keys
    private static final String TRUST_STORE_FILE = "truststore.file";
    private static final String TRUST_STORE_PASSWORD = "truststore.password";

    // OneView properties keys
    private static final String API_VERSION = "oneview.api_version";
    private static final String HOSTNAME = "oneview.hostname";
    private static final String DOMAIN = "oneview.domain";
    private static final String USERNAME = "oneview.username";
    private static final String PASSWORD = "oneview.password";

    private final Properties properties;

    private SDKConfiguration(Properties properties) throws SDKPropertiesFileException {
        if (!properties.keySet().containsAll(Arrays.asList(MANDATORY_FIELDS))) {
            throw new SDKPropertiesFileException("Missing mandatory SDK configuration parameters");
        }
        this.properties = properties;
    }

    public String getPropertyValue(String propertyName, String defaultValue) {
        return this.properties.getProperty(propertyName, defaultValue);
    }

    public int getMessageBusPort() throws NumberFormatException {
        return Integer.parseInt(this.properties.getProperty(MESSAGE_BUS_PORT,
                String.valueOf(DEFAULT_MESSAGE_BUS_PORT)));
    }

    public String getTrustStoreFile() {
        return this.properties.getProperty(TRUST_STORE_FILE);
    }

    public String getTrustStorePassword() {
        return this.properties.getProperty(TRUST_STORE_PASSWORD);
    }

    public ApiVersion getOneViewApiVersion() {
        return ApiVersion.fromStringValue(this.properties.getProperty(API_VERSION,
                String.valueOf(DEFAULT_API_VERSION.getValue())));
    }

    public String getOneViewHostname() {
        return this.properties.getProperty(HOSTNAME);
    }

    public String getOneViewUserName() {
        return this.properties.getProperty(USERNAME);
    }

    public String getOneViewPassword() {
        return this.properties.getProperty(PASSWORD);
    }

    public String getOneViewDomain() {
        return this.properties.getProperty(DOMAIN, DEFAULT_DOMAIN);
    }

    public static SDKConfiguration fromFile(String filePath) {
        InputStream inputStream = SDKConfiguration.class.getClassLoader().getResourceAsStream(filePath);

        if (inputStream != null) {
            try {
                Properties properties = new Properties();

                properties.load(inputStream);

                return new SDKConfiguration(properties);
            } catch (IOException e) {
                throw new SDKPropertiesFileException(e);
            }
        } else {
            throw new SDKPropertiesFileException("Properties file '" + filePath + "' not found in classpath.");
        }
    }

    public static SDKConfigurationBuilder create() {
        return new SDKConfigurationBuilder();
    }

    public static class SDKConfigurationBuilder {

        private Map<String, String> values = new HashMap<>();

        public SDKConfigurationBuilder withOneViewHostname(String hostname) {
            values.put(SDKConfiguration.HOSTNAME, hostname);
            return this;
        }

        public SDKConfigurationBuilder withOneViewUser(String userName, String password) {
            values.put(SDKConfiguration.USERNAME, userName);
            values.put(SDKConfiguration.PASSWORD, password);
            return this;
        }

        public SDKConfigurationBuilder withOneViewDomain(String domain) {
            values.put(SDKConfiguration.DOMAIN, domain);
            return this;
        }

        public SDKConfigurationBuilder withOneViewApiVersion(ApiVersion version) {
            values.put(SDKConfiguration.API_VERSION, String.valueOf(version.getValue()));
            return this;
        }

        public SDKConfigurationBuilder withMessageBusPort(int port) {
            values.put(SDKConfiguration.MESSAGE_BUS_PORT, String.valueOf(port));
            return this;
        }

        public SDKConfigurationBuilder withTrustStore(String path, String password) {
            values.put(SDKConfiguration.TRUST_STORE_FILE, path);
            values.put(SDKConfiguration.TRUST_STORE_PASSWORD, password);
            return this;
        }

        public SDKConfiguration build() throws RuntimeException {
            Properties properties = new Properties();

            properties.putAll(values);

            return new SDKConfiguration(properties);
        }
    }

}
