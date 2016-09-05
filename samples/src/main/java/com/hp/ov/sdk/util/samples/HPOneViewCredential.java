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

import com.hp.ov.sdk.SamplesConstants;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.client.security.LoginSessionClient;
import com.hp.ov.sdk.rest.client.settings.VersionClient;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.OneViewConnector;

public class HPOneViewCredential {

    @Deprecated
    public static RestParams createCredentials() {
        RestParams params = createRestParams();

        BaseClient baseClient = new BaseClient(params,
                new ResourceAdaptor(),
                HttpRestClient.getClient(),
                TaskMonitorManager.getInstance());

        OneViewConnector connector = new OneViewConnector(
                params, createHttpSslProperties(),
                new VersionClient(baseClient),
                new LoginSessionClient(baseClient));

        connector.connect();

        return params;
    }

    public static RestParams createRestParams() {
        RestParams params = new RestParams();

        params.setHostname(SamplesConstants.HOSTNAME);
        params.setUserName(SamplesConstants.USERNAME);
        params.setPassword(SamplesConstants.PASSWORD);
        params.setApiVersion(SamplesConstants.VERSION);
        params.setDomain(SamplesConstants.DOMAIN);

        return params;
    }

    public static HttpSslProperties createHttpSslProperties() {
        HttpSslProperties httpSslProperties = new HttpSslProperties();

        httpSslProperties.setTrustStore(SamplesConstants.TRUST_STORE_FILE);
        httpSslProperties.setTrustStorePassword(SamplesConstants.TRUST_STORE_PASSWORD);
        httpSslProperties.setTrustStoreType(SamplesConstants.TRUST_STORE_TYPE);

        return httpSslProperties;
    }
}
