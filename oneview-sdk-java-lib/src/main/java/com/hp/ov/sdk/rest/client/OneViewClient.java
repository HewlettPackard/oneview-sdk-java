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

import java.lang.reflect.Constructor;

import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKException;
import com.hp.ov.sdk.rest.client.facilities.DataCenterClient;
import com.hp.ov.sdk.rest.client.facilities.UnmanagedDeviceClient;
import com.hp.ov.sdk.rest.client.networking.ConnectionTemplateClient;
import com.hp.ov.sdk.rest.client.networking.EthernetNetworkClient;
import com.hp.ov.sdk.rest.client.networking.FabricClient;
import com.hp.ov.sdk.rest.client.networking.FcNetworkClient;
import com.hp.ov.sdk.rest.client.networking.FcoeNetworkClient;
import com.hp.ov.sdk.rest.client.networking.InterconnectTypeClient;
import com.hp.ov.sdk.rest.client.networking.LogicalInterconnectGroupClient;
import com.hp.ov.sdk.rest.client.networking.LogicalSwitchClient;
import com.hp.ov.sdk.rest.client.networking.LogicalSwitchGroupClient;
import com.hp.ov.sdk.rest.client.networking.NetworkSetClient;
import com.hp.ov.sdk.rest.client.networking.SwitchClient;
import com.hp.ov.sdk.rest.client.networking.SwitchTypeClient;
import com.hp.ov.sdk.rest.client.security.LoginSessionClient;
import com.hp.ov.sdk.rest.client.server.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.server.ServerHardwareClient;
import com.hp.ov.sdk.rest.client.server.ServerHardwareTypeClient;
import com.hp.ov.sdk.rest.client.settings.FirmwareBundleClient;
import com.hp.ov.sdk.rest.client.settings.VersionClient;
import com.hp.ov.sdk.rest.client.storage.StoragePoolClient;
import com.hp.ov.sdk.rest.client.storage.StorageSystemClient;
import com.hp.ov.sdk.rest.client.storage.StorageVolumeAttachmentClient;
import com.hp.ov.sdk.rest.client.storage.StorageVolumeClient;
import com.hp.ov.sdk.rest.client.storage.StorageVolumeTemplateClient;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.OneViewConnector;

public class OneViewClient {

    private final BaseClient baseClient;

    private EnclosureGroupClient enclosureGroupClient;
    private FcoeNetworkClient fcoeNetworkClient;
    private FcNetworkClient fcNetworkClient;
    private EthernetNetworkClient ethernetNetworkClient;
    private LogicalSwitchClient logicalSwitchClient;
    private LogicalSwitchGroupClient logicalSwitchGroupClient;
    private NetworkSetClient networkSetClient;
    private SwitchTypeClient switchTypeClient;
    private DataCenterClient dataCenterClient;
    private UnmanagedDeviceClient unmanagedDeviceClient;
    private ConnectionTemplateClient connectionTemplateClient;
    private FabricClient fabricClient;
    private SwitchClient switchClient;
    private StorageSystemClient storageSystemClient;
    private StoragePoolClient storagePoolClient;
    private StorageVolumeClient storageVolumeClient;
    private StorageVolumeAttachmentClient storageVolumeAttachmentClient;
    private StorageVolumeTemplateClient storageVolumeTemplateClient;
    private InterconnectTypeClient interconnectTypeClient;
    private LogicalInterconnectGroupClient logicalInterconnectGroupClient;

    private ServerHardwareClient serverHardwareClient;
    private ServerHardwareTypeClient serverHardwareTypeClient;

    private FirmwareBundleClient firmwareBundleClient;

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

    public synchronized EnclosureGroupClient enclosureGroup() {
        return this.getClient(this.enclosureGroupClient, EnclosureGroupClient.class);
    }

    public synchronized FcoeNetworkClient fcoeNetwork() {
        return this.getClient(this.fcoeNetworkClient, FcoeNetworkClient.class);
    }

    public synchronized FcNetworkClient fcNetwork() {
        return this.getClient(this.fcNetworkClient, FcNetworkClient.class);
    }

    public synchronized EthernetNetworkClient ethernetNetwork() {
        return this.getClient(this.ethernetNetworkClient, EthernetNetworkClient.class);
    }

    public synchronized LogicalSwitchClient logicalSwitch() {
        return this.getClient(this.logicalSwitchClient, LogicalSwitchClient.class);
    }

    public synchronized LogicalSwitchGroupClient logicalSwitchGroup() {
        return this.getClient(this.logicalSwitchGroupClient, LogicalSwitchGroupClient.class);
    }

    public synchronized NetworkSetClient networkSet() {
        return this.getClient(this.networkSetClient, NetworkSetClient.class);
    }

    public synchronized SwitchTypeClient switchType() {
        return this.getClient(this.switchTypeClient, SwitchTypeClient.class);
    }

    public synchronized DataCenterClient dataCenter() {
        return this.getClient(this.dataCenterClient, DataCenterClient.class);
    }

    public synchronized UnmanagedDeviceClient unmanagedDevice() {
        return this.getClient(this.unmanagedDeviceClient, UnmanagedDeviceClient.class);
    }

    public synchronized ConnectionTemplateClient connectionTemplate() {
        return this.getClient(this.connectionTemplateClient, ConnectionTemplateClient.class);
    }

    public synchronized FabricClient fabric() {
        return this.getClient(this.fabricClient, FabricClient.class);
    }

    public synchronized SwitchClient switches() {
        return this.getClient(this.switchClient, SwitchClient.class);
    }

    public synchronized StorageSystemClient storageSystem() {
        return this.getClient(this.storageSystemClient, StorageSystemClient.class);
    }

    public synchronized StoragePoolClient storagePool() {
        return this.getClient(this.storagePoolClient, StoragePoolClient.class);
    }

    public synchronized StorageVolumeClient storageVolume() {
        return this.getClient(this.storageVolumeClient, StorageVolumeClient.class);
    }

    public synchronized StorageVolumeAttachmentClient storageVolumeAttachment() {
        return this.getClient(this.storageVolumeAttachmentClient, StorageVolumeAttachmentClient.class);
    }

    public synchronized StorageVolumeTemplateClient storageVolumeTemplate() {
        return this.getClient(this.storageVolumeTemplateClient, StorageVolumeTemplateClient.class);
    }

    public synchronized InterconnectTypeClient interconnectType() {
        return this.getClient(this.interconnectTypeClient, InterconnectTypeClient.class);
    }

    public synchronized LogicalInterconnectGroupClient logicalInterconnectGroup() {
        return this.getClient(this.logicalInterconnectGroupClient, LogicalInterconnectGroupClient.class);
    }

    public synchronized ServerHardwareClient serverHardware() {
        return this.getClient(this.serverHardwareClient, ServerHardwareClient.class);
    }

    public synchronized ServerHardwareTypeClient serverHardwareType() {
        return this.getClient(this.serverHardwareTypeClient, ServerHardwareTypeClient.class);
    }

    public synchronized FirmwareBundleClient firmwareBundle() {
        return this.getClient(this.firmwareBundleClient, FirmwareBundleClient.class);
    }

    private <T> T getClient(T client, Class<T> clientClass) {
        if (client == null) {
            try {
                Constructor<T> constructor = clientClass.getConstructor(BaseClient.class);

                client = constructor.newInstance(this.baseClient);
            } catch (ReflectiveOperationException e) {
                throw new SDKException(SDKErrorEnum.internalServerError, null, null, null, null, e);
            }
        }
        return client;
    }

}
