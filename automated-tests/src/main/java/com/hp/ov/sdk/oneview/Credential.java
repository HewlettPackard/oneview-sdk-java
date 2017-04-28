/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use file except in compliance with the License.
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

package com.hp.ov.sdk.oneview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.rest.http.core.client.SDKConfiguration;

public class Credential {

    private static Credential credential;

    private static OneViewClient oneViewClient;

    private String domain;
    private ApiVersion version;

    private String hostname;
    private String username;
    private String password;

    private String enclosureHostname;
    private String enclosureUsername;
    private String enclosurePassword;

    private String storageSystemHostname;
    private String storageSystemUsername;
    private String storageSystemPassword;

    private String file_sdk_config;

    private Credential() {
    }

    public static Credential getInstance() {
        if (credential == null) {
            credential = new Credential();
        }
        return credential;
    }

    public void writeProperties(String targetFile) {
        Properties properties = new Properties();
        for (Field f : this.getClass().getDeclaredFields()) {
            try {
                if (!f.getName().equals("credential") && !f.getName().equals("oneViewClient")) {
                    properties.setProperty(f.getName(), f.get(this).toString());
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        try {
            File fileOut = new File(targetFile);
            fileOut.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(targetFile);
            properties.store(writer, "File Generation Automated \nOneView Credentials");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readProperties(String localProp) {
        try {
            FileReader reader = new FileReader(localProp);
            Properties properties = new Properties();
            properties.load(reader);

            domain = properties.getProperty("domain");
            version = ApiVersion.valueOf(properties.getProperty("version"));

            hostname = properties.getProperty("hostname");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            enclosureHostname = properties.getProperty("enclosureHostname");
            enclosureUsername = properties.getProperty("enclosureUsername");
            enclosurePassword = properties.getProperty("enclosurePassword");

            storageSystemHostname = properties.getProperty("storageSystemHostname");
            storageSystemUsername = properties.getProperty("storageSystemUsername");
            storageSystemPassword = properties.getProperty("storageSystemPassword");

            file_sdk_config = properties.getProperty("file_sdk_config");

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public OneViewClient getOneViewClient() {
        if (oneViewClient == null) {
            oneViewClient = createOneViewClient();
        }
        return oneViewClient;
    }

    private OneViewClient createOneViewClient() {
        SDKConfiguration sdkConfiguration = SDKConfiguration.fromFile(file_sdk_config);
        OneViewClient client = new OneViewClient(sdkConfiguration);
        return client;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getFile_sdk_config() {
        return file_sdk_config;
    }

    public void setFile_sdk_config(String file_sdk_config) {
        this.file_sdk_config = file_sdk_config;
    }

    public void setVersion(ApiVersion version) {
        this.version = version;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnclosureHostname() {
        return enclosureHostname;
    }

    public void setEnclosureHostname(String enclosureHostname) {
        this.enclosureHostname = enclosureHostname;
    }

    public String getEnclosureUsername() {
        return enclosureUsername;
    }

    public void setEnclosureUsername(String enclosureUsername) {
        this.enclosureUsername = enclosureUsername;
    }

    public String getEnclosurePassword() {
        return enclosurePassword;
    }

    public void setEnclosurePassword(String enclosurePassword) {
        this.enclosurePassword = enclosurePassword;
    }

    public String getStorageSystemHostname() {
        return storageSystemHostname;
    }

    public void setStorageSystemHostname(String storageSystemHostname) {
        this.storageSystemHostname = storageSystemHostname;
    }

    public String getStorageSystemUsername() {
        return storageSystemUsername;
    }

    public void setStorageSystemUsername(String storageSystemUsername) {
        this.storageSystemUsername = storageSystemUsername;
    }

    public String getStorageSystemPassword() {
        return storageSystemPassword;
    }

    public void setStorageSystemPassword(String storageSystemPassword) {
        this.storageSystemPassword = storageSystemPassword;
    }

    public String getVersion() {
        return this.version.toString();
    }

}
