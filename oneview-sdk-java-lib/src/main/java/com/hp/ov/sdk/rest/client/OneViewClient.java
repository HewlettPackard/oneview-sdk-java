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
import com.hp.ov.sdk.rest.client.activity.AlertClient;
import com.hp.ov.sdk.rest.client.facilities.DataCenterClient;
import com.hp.ov.sdk.rest.client.facilities.PowerDeliveryDeviceClient;
import com.hp.ov.sdk.rest.client.facilities.RackClient;
import com.hp.ov.sdk.rest.client.facilities.UnmanagedDeviceClient;
import com.hp.ov.sdk.rest.client.networking.ConnectionTemplateClient;
import com.hp.ov.sdk.rest.client.networking.EthernetNetworkClient;
import com.hp.ov.sdk.rest.client.networking.FabricClient;
import com.hp.ov.sdk.rest.client.networking.FcNetworkClient;
import com.hp.ov.sdk.rest.client.networking.FcoeNetworkClient;
import com.hp.ov.sdk.rest.client.networking.InterconnectClient;
import com.hp.ov.sdk.rest.client.networking.InterconnectTypeClient;
import com.hp.ov.sdk.rest.client.networking.LogicalDownlinkClient;
import com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient;
import com.hp.ov.sdk.rest.client.networking.LogicalInterconnectGroupClient;
import com.hp.ov.sdk.rest.client.networking.LogicalSwitchClient;
import com.hp.ov.sdk.rest.client.networking.LogicalSwitchGroupClient;
import com.hp.ov.sdk.rest.client.networking.NetworkSetClient;
import com.hp.ov.sdk.rest.client.networking.SasInterconnectClient;
import com.hp.ov.sdk.rest.client.networking.SasInterconnectTypeClient;
import com.hp.ov.sdk.rest.client.networking.SasLogicalInterconnectClient;
import com.hp.ov.sdk.rest.client.networking.SasLogicalInterconnectGroupClient;
import com.hp.ov.sdk.rest.client.networking.SwitchClient;
import com.hp.ov.sdk.rest.client.networking.SwitchTypeClient;
import com.hp.ov.sdk.rest.client.networking.UplinkSetClient;
import com.hp.ov.sdk.rest.client.security.LoginSessionClient;
import com.hp.ov.sdk.rest.client.server.EnclosureClient;
import com.hp.ov.sdk.rest.client.server.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.server.LogicalEnclosureClient;
import com.hp.ov.sdk.rest.client.server.ServerHardwareClient;
import com.hp.ov.sdk.rest.client.server.ServerHardwareTypeClient;
import com.hp.ov.sdk.rest.client.server.ServerProfileClient;
import com.hp.ov.sdk.rest.client.server.ServerProfileTemplateClient;
import com.hp.ov.sdk.rest.client.settings.FirmwareBundleClient;
import com.hp.ov.sdk.rest.client.settings.FirmwareDriverClient;
import com.hp.ov.sdk.rest.client.settings.VersionClient;
import com.hp.ov.sdk.rest.client.storage.DriveEnclosureClient;
import com.hp.ov.sdk.rest.client.storage.FcSanDeviceManagerClient;
import com.hp.ov.sdk.rest.client.storage.FcSanManagedSanClient;
import com.hp.ov.sdk.rest.client.storage.FcSanProviderClient;
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

    private AlertClient alertClient;
    private DriveEnclosureClient driveEnclosureClient;
    private EnclosureGroupClient enclosureGroupClient;
    private EnclosureClient enclosureClient;
    private LogicalEnclosureClient logicalEnclosureClient;
    private FcoeNetworkClient fcoeNetworkClient;
    private FcNetworkClient fcNetworkClient;
    private EthernetNetworkClient ethernetNetworkClient;
    private LogicalSwitchClient logicalSwitchClient;
    private LogicalSwitchGroupClient logicalSwitchGroupClient;
    private NetworkSetClient networkSetClient;
    private UplinkSetClient uplinkSetClient;
    private SwitchTypeClient switchTypeClient;
    private RackClient rackClient;
    private DataCenterClient dataCenterClient;
    private PowerDeliveryDeviceClient powerDeliveryDeviceClient;
    private UnmanagedDeviceClient unmanagedDeviceClient;
    private ConnectionTemplateClient connectionTemplateClient;
    private FabricClient fabricClient;
    private SasInterconnectClient sasInterconnectClient;
    private SwitchClient switchClient;
    private StorageSystemClient storageSystemClient;
    private StoragePoolClient storagePoolClient;
    private StorageVolumeClient storageVolumeClient;
    private StorageVolumeAttachmentClient storageVolumeAttachmentClient;
    private StorageVolumeTemplateClient storageVolumeTemplateClient;
    private FcSanDeviceManagerClient fcSanDeviceManagerClient;
    private FcSanProviderClient fcSanProviderClient;
    private FcSanManagedSanClient fcSanManagedSanClient;
    private InterconnectTypeClient interconnectTypeClient;
    private InterconnectClient interconnectClient;
    private LogicalInterconnectGroupClient logicalInterconnectGroupClient;
    private LogicalInterconnectClient logicalInterconnectClient;
    private LogicalDownlinkClient logicalDownlinkClient;
    private ServerHardwareClient serverHardwareClient;
    private ServerHardwareTypeClient serverHardwareTypeClient;
    private ServerProfileClient serverProfileClient;
    private ServerProfileTemplateClient serverProfileTemplateClient;
    private FirmwareBundleClient firmwareBundleClient;
    private FirmwareDriverClient firmwareDriverClient;
    private SasInterconnectTypeClient sasInterconnectTypeClient;
    private SasLogicalInterconnectGroupClient sasLogicalInterconnectGroupClient;
    private SasLogicalInterconnectClient sasLogicalInterconnectClient;

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

    public synchronized AlertClient alert() {
        return this.getClient(this.alertClient, AlertClient.class);
    }

    public synchronized DriveEnclosureClient driveEnclosure() {
        return this.getClient(this.driveEnclosureClient, DriveEnclosureClient.class);
    }

    public synchronized EnclosureGroupClient enclosureGroup() {
        return this.getClient(this.enclosureGroupClient, EnclosureGroupClient.class);
    }

    public synchronized EnclosureClient enclosure() {
        return this.getClient(this.enclosureClient, EnclosureClient.class);
    }

    public synchronized LogicalEnclosureClient logicalEnclosure() {
        return this.getClient(this.logicalEnclosureClient, LogicalEnclosureClient.class);
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

    public synchronized UplinkSetClient uplinkSet() {
        return this.getClient(this.uplinkSetClient, UplinkSetClient.class);
    }

    public synchronized SwitchTypeClient switchType() {
        return this.getClient(this.switchTypeClient, SwitchTypeClient.class);
    }

    public synchronized RackClient rack() {
        return this.getClient(this.rackClient, RackClient.class);
    }

    public synchronized DataCenterClient dataCenter() {
        return this.getClient(this.dataCenterClient, DataCenterClient.class);
    }

    public synchronized PowerDeliveryDeviceClient powerDeliveryDevice() {
        return this.getClient(this.powerDeliveryDeviceClient, PowerDeliveryDeviceClient.class);
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

    public synchronized SasInterconnectClient sasInterconnects() {
        return this.getClient(this.sasInterconnectClient, SasInterconnectClient.class);
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

    public synchronized FcSanDeviceManagerClient fcSanDeviceManager() {
        return this.getClient(this.fcSanDeviceManagerClient, FcSanDeviceManagerClient.class);
    }

    public synchronized FcSanProviderClient fcSanProvider() {
        return this.getClient(this.fcSanProviderClient, FcSanProviderClient.class);
    }

    public synchronized FcSanManagedSanClient fcSanManagedSan() {
        return this.getClient(this.fcSanManagedSanClient, FcSanManagedSanClient.class);
    }

    public synchronized InterconnectTypeClient interconnectType() {
        return this.getClient(this.interconnectTypeClient, InterconnectTypeClient.class);
    }

    public synchronized InterconnectClient interconnect() {
        return this.getClient(this.interconnectClient, InterconnectClient.class);
    }

    public synchronized LogicalInterconnectGroupClient logicalInterconnectGroup() {
        return this.getClient(this.logicalInterconnectGroupClient, LogicalInterconnectGroupClient.class);
    }

    public synchronized LogicalInterconnectClient logicalInterconnect() {
        return this.getClient(this.logicalInterconnectClient, LogicalInterconnectClient.class);
    }

    public synchronized LogicalDownlinkClient logicalDownlink() {
        return this.getClient(this.logicalDownlinkClient, LogicalDownlinkClient.class);
    }

    public synchronized ServerHardwareClient serverHardware() {
        return this.getClient(this.serverHardwareClient, ServerHardwareClient.class);
    }

    public synchronized ServerHardwareTypeClient serverHardwareType() {
        return this.getClient(this.serverHardwareTypeClient, ServerHardwareTypeClient.class);
    }

    public synchronized ServerProfileClient serverProfile() {
        return this.getClient(this.serverProfileClient, ServerProfileClient.class);
    }

    public synchronized ServerProfileTemplateClient serverProfileTemplate() {
        return this.getClient(this.serverProfileTemplateClient, ServerProfileTemplateClient.class);
    }

    public synchronized FirmwareBundleClient firmwareBundle() {
        return this.getClient(this.firmwareBundleClient, FirmwareBundleClient.class);
    }

    public synchronized FirmwareDriverClient firmwareDriver() {
        return this.getClient(this.firmwareDriverClient, FirmwareDriverClient.class);
    }

    public synchronized SasInterconnectTypeClient sasInterconnectType() {
        return this.getClient(this.sasInterconnectTypeClient, SasInterconnectTypeClient.class);
    }

    public synchronized SasLogicalInterconnectGroupClient sasLogicalInterconnectGroup() {
        return this.getClient(this.sasLogicalInterconnectGroupClient, SasLogicalInterconnectGroupClient.class);
    }

    public synchronized SasLogicalInterconnectClient sasLogicalInterconnect() {
        return this.getClient(this.sasLogicalInterconnectClient, SasLogicalInterconnectClient.class);
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
