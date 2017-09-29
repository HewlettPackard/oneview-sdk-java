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

package com.hp.ov.sdk.rest.client.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.settings.License;
import com.hp.ov.sdk.dto.settings.LicenseType;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.http.core.BasicURIQuery;
import com.hp.ov.sdk.rest.http.core.URIQuery;

public class LicenseClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(LicenseClientSample.class);

    // These are the variables to be defined by the user
    // ================================
    // Make sure to escape double quotes (") in the key with \
    private static final String LICENSE_KEY = "XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX XXXX \"XXXXXXXXX XXXXXXXXXX XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXX\"";
    private static final LicenseType LICENSE_TYPE = LicenseType.Permanent;
    private static final String PRODUCT = "Synergy 8Gb FC Upgrade";
    // ================================

    private LicenseClient client;

    public LicenseClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        this.client = oneViewClient.license();
    }

    public void addLicense() {
        License license = new License();

        license.setKey(LICENSE_KEY);
        license.setType(ResourceCategory.RC_LICENSE); // API 120
        license.setType(ResourceCategory.RC_LICENSE_V200); // API 200 - 300
        license.setType(ResourceCategory.RC_LICENSE_V500); // API 500

        License addedLicense = this.client.add(license);

        LOGGER.info("Added license object returned to client : " + addedLicense.toJsonString());
    }

    public void getAllLicenses() {
        ResourceCollection<License> licenses = client.getAll();

        LOGGER.info("Licenses returned to client : " + licenses.toJsonString());
    }

    public void getLicenseById() {
        License license = client.getAll().get(0);

        license = client.getById(license.getLicenseId());

        LOGGER.info("License object returned to client : " + license.toJsonString());
    }

    public void getLicensesByProduct() {
        BasicURIQuery query = new BasicURIQuery();

        query.addParameter(URIQuery.FILTER, "product='" + PRODUCT + "'");

        ResourceCollection<License> licenses = client.get(query);

        LOGGER.info("Licenses returned to client : " + licenses.toJsonString());
    }

    public void getLicensesByLicenseType() {
        BasicURIQuery query = new BasicURIQuery();

        query.addParameter(URIQuery.FILTER, "licenseType='" + LICENSE_TYPE + "'");

        ResourceCollection<License> licenses = client.get(query);

        LOGGER.info("Licenses returned to client : " + licenses.toJsonString());
    }

    public void getLicensesByNodeId() {
        String nodeId = client.getAll().get(0).getNodes().get(0).getNodeId();

        BasicURIQuery query = new BasicURIQuery();

        query.addParameter(URIQuery.FILTER, "nodeId='" + nodeId + "'");

        ResourceCollection<License> licenses = client.get(query);

        LOGGER.info("Licenses returned to client : " + licenses.toJsonString());
    }

    public void getLicensesByAdditionalKeys() {
        String additionalKeys = client.getAll().get(0).getAdditionalKeys().get(0);

        BasicURIQuery query = new BasicURIQuery();

        query.addParameter(URIQuery.FILTER, "additionalKeys='" + additionalKeys + "'");

        ResourceCollection<License> licenses = client.get(query);

        LOGGER.info("Licenses returned to client : " + licenses.toJsonString());
    }

    public void deleteLicense() {
        License license = client.getAll().get(0);

        String response = client.delete(license.getLicenseId());

        LOGGER.info("Response returned to client : " + response);
    }

    public static void main(String[] args) {
        LicenseClientSample client = new LicenseClientSample();

        client.addLicense();

        client.getAllLicenses();
        client.getLicenseById();
        client.getLicensesByProduct();
        client.getLicensesByLicenseType();
        client.getLicensesByNodeId();
        client.getLicensesByAdditionalKeys();

        client.deleteLicense();
    }

}
