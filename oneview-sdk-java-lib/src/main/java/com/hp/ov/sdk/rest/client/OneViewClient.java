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

package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.rest.client.networking.EthernetNetworkClient;
import com.hp.ov.sdk.rest.client.networking.FcNetworkClient;
import com.hp.ov.sdk.rest.client.networking.FcoeNetworkClient;
import com.hp.ov.sdk.rest.client.networking.LogicalSwitchClient;
import com.hp.ov.sdk.rest.client.networking.LogicalSwitchGroupClient;
import com.hp.ov.sdk.rest.client.networking.NetworkSetClient;
import com.hp.ov.sdk.rest.client.networking.SwitchTypeClient;
import com.hp.ov.sdk.rest.client.security.LoginSessionClient;
import com.hp.ov.sdk.rest.client.settings.VersionClient;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.OneViewConnector;

public class OneViewClient {

    private final BaseClient baseClient;

    private FcoeNetworkClient fcoeNetworkClient;
    private FcNetworkClient fcNetworkClient;
    private EthernetNetworkClient ethernetNetworkClient;
    private LogicalSwitchClient logicalSwitchClient;
    private LogicalSwitchGroupClient logicalSwitchGroupClient;
    private NetworkSetClient networkSetClient;
    private SwitchTypeClient switchTypeClient;

    public OneViewClient(RestParams params, HttpSslProperties httpSslProperties) {
        this.baseClient = new BaseClient(params,
                new ResourceAdaptor(),
                HttpRestClient.getClient(),
                TaskMonitorManager.getInstance());

        OneViewConnector connector = new OneViewConnector(
                params, httpSslProperties,
                new VersionClient(this.baseClient),
                new LoginSessionClient(this.baseClient));

        connector.connect();
    }

    public synchronized FcoeNetworkClient fcoeNetwork() {
        if (this.fcoeNetworkClient == null) {
            this.fcoeNetworkClient = new FcoeNetworkClient(this.baseClient);
        }
        return this.fcoeNetworkClient;
    }

    public synchronized FcNetworkClient fcNetwork() {
        if (this.fcNetworkClient == null) {
            this.fcNetworkClient = new FcNetworkClient(this.baseClient);
        }
        return this.fcNetworkClient;
    }

    public synchronized EthernetNetworkClient ethernetNetwork() {
        if (this.ethernetNetworkClient == null) {
            this.ethernetNetworkClient = new EthernetNetworkClient(this.baseClient);
        }
        return this.ethernetNetworkClient;
    }

    public synchronized LogicalSwitchClient logicalSwitch() {
        if (this.logicalSwitchClient == null) {
            this.logicalSwitchClient = new LogicalSwitchClient(this.baseClient);
        }
        return this.logicalSwitchClient;
    }

    public synchronized LogicalSwitchGroupClient logicalSwitchGroup() {
        if (this.logicalSwitchGroupClient == null) {
            this.logicalSwitchGroupClient = new LogicalSwitchGroupClient(this.baseClient);
        }
        return this.logicalSwitchGroupClient;
    }

    public synchronized NetworkSetClient networkSet() {
        if (this.networkSetClient == null) {
            this.networkSetClient = new NetworkSetClient(this.baseClient);
        }
        return this.networkSetClient;
    }

    public synchronized SwitchTypeClient switchType() {
        if (this.switchTypeClient == null) {
            this.switchTypeClient = new SwitchTypeClient(this.baseClient);
        }
        return this.switchTypeClient;
    }

}
