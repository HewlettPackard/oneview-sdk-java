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
import java.util.Properties;

import com.hp.ov.sdk.exceptions.SDKPropertiesFileException;

public class SDKConfiguration {

    // Message bus properties keys
    private static final String SCMB_TASKS_ROUTING_KEY = "messagebus.scmb_tasks_routing_key";
    private static final String MSMB_EXCHANGE_NAME = "messagebus.msmb_exchange_name";
    private static final String MSMB_ALERTS_ROUTING_KEY = "messagebus.msmb_alerts_routing_key";
    private static final String AMQP_PORT = "messagebus.amqpPort";

    // Trust store properties keys
    private static final String TRUST_STORE_FILE = "truststore.file";
    private static final String TRUST_STORE_TYPE = "truststore.type";
    private static final String TRUST_STORE_PASSWORD = "truststore.password";

    // OneView properties keys
    private static final String API_VERSION = "oneview.api_version";
    private static final String HOSTNAME = "oneview.hostname";
    private static final String DOMAIN = "oneview.domain";
    private static final String USERNAME = "oneview.username";
    private static final String PASSWORD = "oneview.password";

    private Properties properties = new Properties();
    private InputStream inputStream;

    public SDKConfiguration(String filePath) {
        this.loadProperties(filePath);
    }

    private void loadProperties(String filePath) {

        this.inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new SDKPropertiesFileException(e);
            }
        } else {
            throw new SDKPropertiesFileException("Properties file " + filePath + " not found in classpath.");
        }
    }

    public void reloadProperties() throws IOException {
        if (this.inputStream != null) {
            properties.load(inputStream);
        }
    }

    public String getPropertyValue (String propertyName, String defaultValue) {
        return this.properties.getProperty(propertyName, defaultValue);
    }

    public String getMessageBusTaskRoutingKey() {
        return this.properties.getProperty(SCMB_TASKS_ROUTING_KEY);
    }

    public String getMessageBusExchangeName() {
        return this.properties.getProperty(MSMB_EXCHANGE_NAME);
    }

    public String getMessageBusAlertsRoutingKey() {
        return this.properties.getProperty(MSMB_ALERTS_ROUTING_KEY);
    }

    public String getRabbitMQPort() {
        return this.properties.getProperty(AMQP_PORT);
    }

    public String getTrustStoreFile() {
        return this.properties.getProperty(TRUST_STORE_FILE);
    }

    public String getTrustStorePassword() {
        return this.properties.getProperty(TRUST_STORE_PASSWORD);
    }

    public String getTrustStoreType() {
        return this.properties.getProperty(TRUST_STORE_TYPE);
    }

    public ApiVersion getOneViewApiVersion() {
        return ApiVersion.fromStringValue(this.properties.getProperty(API_VERSION));
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
        return this.properties.getProperty(DOMAIN);
    }

}
