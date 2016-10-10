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
import com.hp.ov.sdk.certs.MessagingCertificateClient;
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
import com.hp.ov.sdk.rest.http.core.client.HttpSslProperties;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;
import com.hp.ov.sdk.util.OneViewConnector;

public class OneViewClient {

    private final BaseClient baseClient;

    private final Map<Class<?>, Object> instances = new ConcurrentHashMap<>();

    public OneViewClient(RestParams params, HttpSslProperties httpSslProperties) {
        this.baseClient = new BaseClient(params);

        OneViewConnector connector = new OneViewConnector(
                params, httpSslProperties,
                new VersionClient(this.baseClient),
                new LoginSessionClient(this.baseClient));

        connector.connect();
    }

    public synchronized AlertClient alert() {
        return Reflection.newProxy(AlertClient.class,
                new ClientRequestHandler<>(this.baseClient, AlertClient.class));
    }

    public synchronized ConnectionTemplateClient connectionTemplate() {
        return Reflection.newProxy(ConnectionTemplateClient.class,
                new ClientRequestHandler<>(this.baseClient, ConnectionTemplateClient.class));
    }

    public synchronized DataCenterClient dataCenter() {
        return Reflection.newProxy(DataCenterClient.class,
                new ClientRequestHandler<>(this.baseClient, DataCenterClient.class));
    }

    public synchronized DriveEnclosureClient driveEnclosure() {
        return Reflection.newProxy(DriveEnclosureClient.class,
                new ClientRequestHandler<>(this.baseClient, DriveEnclosureClient.class));
    }

    public synchronized EnclosureClient enclosure() {
        return Reflection.newProxy(EnclosureClient.class,
                new ClientRequestHandler<>(this.baseClient, EnclosureClient.class));
    }

    public synchronized EnclosureGroupClient enclosureGroup() {
        return Reflection.newProxy(EnclosureGroupClient.class,
                new ClientRequestHandler<>(this.baseClient, EnclosureGroupClient.class));
    }

    public synchronized EthernetNetworkClient ethernetNetwork() {
        return Reflection.newProxy(EthernetNetworkClient.class,
                new ClientRequestHandler<>(this.baseClient, EthernetNetworkClient.class));
    }

    public synchronized FabricClient fabric() {
        return Reflection.newProxy(FabricClient.class,
                new ClientRequestHandler<>(this.baseClient, FabricClient.class));
    }

    public synchronized FcNetworkClient fcNetwork() {
        return Reflection.newProxy(FcNetworkClient.class,
                new ClientRequestHandler<>(this.baseClient, FcNetworkClient.class));
    }

    public synchronized FcoeNetworkClient fcoeNetwork() {
        return Reflection.newProxy(FcoeNetworkClient.class,
                new ClientRequestHandler<>(this.baseClient, FcoeNetworkClient.class));
    }

    public synchronized FcSanDeviceManagerClient fcSanDeviceManager() {
        return this.getClient(FcSanDeviceManagerClient.class);
    }

    public synchronized FcSanManagedSanClient fcSanManagedSan() {
        return this.getClient(FcSanManagedSanClient.class);
    }

    public synchronized FcSanProviderClient fcSanProvider() {
        return this.getClient(FcSanProviderClient.class);
    }

    public synchronized FirmwareBundleClient firmwareBundle() {
        return this.getClient(FirmwareBundleClient.class);
    }

    public synchronized FirmwareDriverClient firmwareDriver() {
        return this.getClient(FirmwareDriverClient.class);
    }

    public synchronized InterconnectClient interconnect() {
        return this.getClient(InterconnectClient.class);
    }

    public synchronized InterconnectLinkTopologyClient interconnectLinkTopology() {
        return this.getClient(InterconnectLinkTopologyClient.class);
    }

    public synchronized InterconnectTypeClient interconnectType() {
        return Reflection.newProxy(InterconnectTypeClient.class,
                new ClientRequestHandler<>(this.baseClient, InterconnectTypeClient.class));
    }

    public synchronized InternalLinkSetClient internalLinkSet() {
        return Reflection.newProxy(InternalLinkSetClient.class,
                new ClientRequestHandler<>(this.baseClient, InternalLinkSetClient.class));
    }

    public synchronized LogicalDownlinkClient logicalDownlink() {
        return Reflection.newProxy(LogicalDownlinkClient.class,
                new ClientRequestHandler<>(this.baseClient, LogicalDownlinkClient.class));
    }

    public synchronized LogicalEnclosureClient logicalEnclosure() {
        return Reflection.newProxy(LogicalEnclosureClient.class,
                new ClientRequestHandler<>(this.baseClient, LogicalEnclosureClient.class));
    }

    public synchronized LogicalInterconnectClient logicalInterconnect() {
        return this.getClient(LogicalInterconnectClient.class);
    }

    public synchronized LogicalInterconnectGroupClient logicalInterconnectGroup() {
        return Reflection.newProxy(LogicalInterconnectGroupClient.class,
                new ClientRequestHandler<>(this.baseClient, LogicalInterconnectGroupClient.class));
    }

    public synchronized LogicalSwitchClient logicalSwitch() {
        return Reflection.newProxy(LogicalSwitchClient.class,
                new ClientRequestHandler<>(this.baseClient, LogicalSwitchClient.class));
    }

    public synchronized LogicalSwitchGroupClient logicalSwitchGroup() {
        return Reflection.newProxy(LogicalSwitchGroupClient.class,
                new ClientRequestHandler<>(this.baseClient, LogicalSwitchGroupClient.class));
    }

    public synchronized MessagingCertificateClient messagingCertificate() {
        return this.getClient(MessagingCertificateClient.class);
    }

    public synchronized NetworkSetClient networkSet() {
        return Reflection.newProxy(NetworkSetClient.class,
                new ClientRequestHandler<>(this.baseClient, NetworkSetClient.class));
    }

    public synchronized PowerDeliveryDeviceClient powerDeliveryDevice() {
        return this.getClient(PowerDeliveryDeviceClient.class);
    }

    public synchronized RackClient rack() {
        return Reflection.newProxy(RackClient.class,
                new ClientRequestHandler<>(this.baseClient, RackClient.class));
    }

    public synchronized SasInterconnectClient sasInterconnects() {
        return Reflection.newProxy(SasInterconnectClient.class,
                new ClientRequestHandler<>(this.baseClient, SasInterconnectClient.class));
    }

    public synchronized SasInterconnectTypeClient sasInterconnectType() {
        return Reflection.newProxy(SasInterconnectTypeClient.class,
                new ClientRequestHandler<>(this.baseClient, SasInterconnectTypeClient.class));
    }

    public synchronized SasLogicalInterconnectClient sasLogicalInterconnect() {
        return Reflection.newProxy(SasLogicalInterconnectClient.class,
                new ClientRequestHandler<>(this.baseClient, SasLogicalInterconnectClient.class));
    }

    public synchronized SasLogicalInterconnectGroupClient sasLogicalInterconnectGroup() {
        return this.getClient(SasLogicalInterconnectGroupClient.class);
    }

    public synchronized SasLogicalJbodAttachmentClient sasLogicalJbodAttachment() {
        return this.getClient(SasLogicalJbodAttachmentClient.class);
    }

    public synchronized SasLogicalJbodClient sasLogicalJbod() {
        return this.getClient(SasLogicalJbodClient.class);
    }

    public synchronized ServerHardwareClient serverHardware() {
        return Reflection.newProxy(ServerHardwareClient.class,
                new ClientRequestHandler<>(this.baseClient, ServerHardwareClient.class));
    }

    public synchronized ServerHardwareTypeClient serverHardwareType() {
        return this.getClient(ServerHardwareTypeClient.class);
    }

    public synchronized ServerProfileClient serverProfile() {
        return this.getClient(ServerProfileClient.class);
    }

    public synchronized ServerProfileTemplateClient serverProfileTemplate() {
        return this.getClient(ServerProfileTemplateClient.class);
    }

    public synchronized StoragePoolClient storagePool() {
        return this.getClient(StoragePoolClient.class);
    }

    public synchronized StorageSystemClient storageSystem() {
        return this.getClient(StorageSystemClient.class);
    }

    public synchronized StorageVolumeClient storageVolume() {
        return Reflection.newProxy(StorageVolumeClient.class,
                new ClientRequestHandler<>(this.baseClient, StorageVolumeClient.class));
    }

    public synchronized StorageVolumeAttachmentClient storageVolumeAttachment() {
        return this.getClient(StorageVolumeAttachmentClient.class);
    }

    public synchronized StorageVolumeTemplateClient storageVolumeTemplate() {
        return Reflection.newProxy(StorageVolumeTemplateClient.class,
                new ClientRequestHandler<>(this.baseClient, StorageVolumeTemplateClient.class));
    }

    public synchronized SwitchClient switches() {
        return Reflection.newProxy(SwitchClient.class,
                new ClientRequestHandler<>(this.baseClient, SwitchClient.class));
    }

    public synchronized SwitchTypeClient switchType() {
        return Reflection.newProxy(SwitchTypeClient.class,
                new ClientRequestHandler<>(this.baseClient, SwitchTypeClient.class));
    }

    public synchronized UnmanagedDeviceClient unmanagedDevice() {
        return Reflection.newProxy(UnmanagedDeviceClient.class,
                new ClientRequestHandler<>(this.baseClient, UnmanagedDeviceClient.class));
    }

    public synchronized UplinkSetClient uplinkSet() {
        return Reflection.newProxy(UplinkSetClient.class,
                new ClientRequestHandler<>(this.baseClient, UplinkSetClient.class));
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
