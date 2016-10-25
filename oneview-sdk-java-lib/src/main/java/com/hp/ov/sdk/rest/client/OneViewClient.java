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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.reflect.Reflection;
import com.hp.ov.sdk.rest.client.security.MessagingCertificateClient;
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
import com.hp.ov.sdk.rest.client.networking.InterconnectLinkTopologyClient;
import com.hp.ov.sdk.rest.client.networking.InterconnectTypeClient;
import com.hp.ov.sdk.rest.client.networking.InternalLinkSetClient;
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
import com.hp.ov.sdk.rest.client.storage.SasLogicalJbodAttachmentClient;
import com.hp.ov.sdk.rest.client.storage.SasLogicalJbodClient;
import com.hp.ov.sdk.rest.client.storage.StoragePoolClient;
import com.hp.ov.sdk.rest.client.storage.StorageSystemClient;
import com.hp.ov.sdk.rest.client.storage.StorageVolumeAttachmentClient;
import com.hp.ov.sdk.rest.client.storage.StorageVolumeClient;
import com.hp.ov.sdk.rest.client.storage.StorageVolumeTemplateClient;
import com.hp.ov.sdk.rest.http.core.client.SDKConfiguration;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;
import com.hp.ov.sdk.util.OneViewConnector;

public class OneViewClient {

    private final BaseClient baseClient;

    private final Map<Class<?>, Object> instances = new ConcurrentHashMap<>();

    public OneViewClient(SDKConfiguration config) {
        this.baseClient = new BaseClient(config);

        OneViewConnector connector = new OneViewConnector(
                config,
                new VersionClient(this.baseClient),
                new LoginSessionClient(this.baseClient));

        this.baseClient.setSessionId(connector.connect());
    }

    public synchronized AlertClient alert() {
        return this.getProxy(AlertClient.class);
    }

    public synchronized ConnectionTemplateClient connectionTemplate() {
        return this.getProxy(ConnectionTemplateClient.class);
    }

    public synchronized DataCenterClient dataCenter() {
        return this.getProxy(DataCenterClient.class);
    }

    public synchronized DriveEnclosureClient driveEnclosure() {
        return this.getProxy(DriveEnclosureClient.class);
    }

    public synchronized EnclosureClient enclosure() {
        return this.getProxy(EnclosureClient.class);
    }

    public synchronized EnclosureGroupClient enclosureGroup() {
        return this.getProxy(EnclosureGroupClient.class);
    }

    public synchronized EthernetNetworkClient ethernetNetwork() {
        return this.getProxy(EthernetNetworkClient.class);
    }

    public synchronized FabricClient fabric() {
        return this.getProxy(FabricClient.class);
    }

    public synchronized FcNetworkClient fcNetwork() {
        return this.getProxy(FcNetworkClient.class);
    }

    public synchronized FcoeNetworkClient fcoeNetwork() {
        return this.getProxy(FcoeNetworkClient.class);
    }

    public synchronized FcSanDeviceManagerClient fcSanDeviceManager() {
        return this.getProxy(FcSanDeviceManagerClient.class);
    }

    public synchronized FcSanManagedSanClient fcSanManagedSan() {
        return this.getProxy(FcSanManagedSanClient.class);
    }

    public synchronized FcSanProviderClient fcSanProvider() {
        return this.getProxy(FcSanProviderClient.class);
    }

    public synchronized FirmwareBundleClient firmwareBundle() {
        return this.getClient(FirmwareBundleClient.class);
    }

    public synchronized FirmwareDriverClient firmwareDriver() {
        return this.getProxy(FirmwareDriverClient.class);
    }

    public synchronized InterconnectClient interconnect() {
        return this.getProxy(InterconnectClient.class);
    }

    public synchronized InterconnectLinkTopologyClient interconnectLinkTopology() {
        return this.getProxy(InterconnectLinkTopologyClient.class);
    }

    public synchronized InterconnectTypeClient interconnectType() {
        return this.getProxy(InterconnectTypeClient.class);
    }

    public synchronized InternalLinkSetClient internalLinkSet() {
        return this.getProxy(InternalLinkSetClient.class);
    }

    public synchronized LogicalDownlinkClient logicalDownlink() {
        return this.getProxy(LogicalDownlinkClient.class);
    }

    public synchronized LogicalEnclosureClient logicalEnclosure() {
        return this.getProxy(LogicalEnclosureClient.class);
    }

    public synchronized LogicalInterconnectClient logicalInterconnect() {
        return this.getProxy(LogicalInterconnectClient.class);
    }

    public synchronized LogicalInterconnectGroupClient logicalInterconnectGroup() {
        return this.getProxy(LogicalInterconnectGroupClient.class);
    }

    public synchronized LogicalSwitchClient logicalSwitch() {
        return this.getProxy(LogicalSwitchClient.class);
    }

    public synchronized LogicalSwitchGroupClient logicalSwitchGroup() {
        return this.getProxy(LogicalSwitchGroupClient.class);
    }

    public synchronized MessagingCertificateClient messagingCertificate() {
        return this.getClient(MessagingCertificateClient.class);
    }

    public synchronized NetworkSetClient networkSet() {
        return this.getProxy(NetworkSetClient.class);
    }

    public synchronized PowerDeliveryDeviceClient powerDeliveryDevice() {
        return this.getProxy(PowerDeliveryDeviceClient.class);
    }

    public synchronized RackClient rack() {
        return this.getProxy(RackClient.class);
    }

    public synchronized SasInterconnectClient sasInterconnects() {
        return this.getProxy(SasInterconnectClient.class);
    }

    public synchronized SasInterconnectTypeClient sasInterconnectType() {
        return this.getProxy(SasInterconnectTypeClient.class);
    }

    public synchronized SasLogicalInterconnectClient sasLogicalInterconnect() {
        return this.getProxy(SasLogicalInterconnectClient.class);
    }

    public synchronized SasLogicalInterconnectGroupClient sasLogicalInterconnectGroup() {
        return this.getProxy(SasLogicalInterconnectGroupClient.class);
    }

    public synchronized SasLogicalJbodAttachmentClient sasLogicalJbodAttachment() {
        return this.getProxy(SasLogicalJbodAttachmentClient.class);
    }

    public synchronized SasLogicalJbodClient sasLogicalJbod() {
        return this.getProxy(SasLogicalJbodClient.class);
    }

    public synchronized ServerHardwareClient serverHardware() {
        return this.getProxy(ServerHardwareClient.class);
    }

    public synchronized ServerHardwareTypeClient serverHardwareType() {
        return this.getProxy(ServerHardwareTypeClient.class);
    }

    public synchronized ServerProfileClient serverProfile() {
        return this.getProxy(ServerProfileClient.class);
    }

    public synchronized ServerProfileTemplateClient serverProfileTemplate() {
        return this.getProxy(ServerProfileTemplateClient.class);
    }

    public synchronized StoragePoolClient storagePool() {
        return this.getProxy(StoragePoolClient.class);
    }

    public synchronized StorageSystemClient storageSystem() {
        return this.getProxy(StorageSystemClient.class);
    }

    public synchronized StorageVolumeClient storageVolume() {
        return this.getProxy(StorageVolumeClient.class);
    }

    public synchronized StorageVolumeAttachmentClient storageVolumeAttachment() {
        return this.getProxy(StorageVolumeAttachmentClient.class);
    }

    public synchronized StorageVolumeTemplateClient storageVolumeTemplate() {
        return this.getProxy(StorageVolumeTemplateClient.class);
    }

    public synchronized SwitchClient switches() {
        return this.getProxy(SwitchClient.class);
    }

    public synchronized SwitchTypeClient switchType() {
        return this.getProxy(SwitchTypeClient.class);
    }

    public synchronized UnmanagedDeviceClient unmanagedDevice() {
        return this.getProxy(UnmanagedDeviceClient.class);
    }

    public synchronized UplinkSetClient uplinkSet() {
        return this.getProxy(UplinkSetClient.class);
    }

    private <T> T getProxy(Class<T> clientClass) {
        T instance = (T) this.instances.get(clientClass);

        if (instance == null) {
            instance = Reflection.newProxy(clientClass, new ClientRequestHandler<>(this.baseClient, clientClass));

            this.instances.put(clientClass, instance);
        }
        return instance;
    }

    private <T> T getClient(Class<T> clientClass) {
        T instance = (T) this.instances.get(clientClass);

        if (instance == null) {
            instance = this.buildClient(clientClass);

            this.instances.put(clientClass, instance);
        }
        return instance;
    }

    private <T> T buildClient(Class<T> clientClass) {
        try {
            Constructor<T> constructor = clientClass.getConstructor(BaseClient.class);

            return constructor.newInstance(this.baseClient);
        } catch (ReflectiveOperationException e) {
            throw new SDKException(SDKErrorEnum.internalServerError, null, null, null, null, e);
        }
    }

}
