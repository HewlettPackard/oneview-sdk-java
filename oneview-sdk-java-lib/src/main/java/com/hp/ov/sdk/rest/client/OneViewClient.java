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

import org.apache.commons.lang3.StringUtils;

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

    /**
     * Creates or retrieves an existing instance of {@link AlertClient}.
     * This client provides an interface for managing alerts.
     *
     * @return an interface to the alerts REST API.
     */
    public synchronized AlertClient alert() {
        return this.getProxy(AlertClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link ConnectionTemplateClient}.
     * This client provides an interface for managing connection templates.
     *
     * @return an interface to the connection templates REST API.
     */
    public synchronized ConnectionTemplateClient connectionTemplate() {
        return this.getProxy(ConnectionTemplateClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link DataCenterClient}.
     * This client provides an interface for managing datacenters.
     *
     * @return an interface to the datacenters REST API.
     */
    public synchronized DataCenterClient dataCenter() {
        return this.getProxy(DataCenterClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link DriveEnclosureClient}.
     * This client provides an interface for managing drive enclosures.
     *
     * @return an interface to the drive enclosures REST API.
     */
    public synchronized DriveEnclosureClient driveEnclosure() {
        return this.getProxy(DriveEnclosureClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link EnclosureClient}.
     * This client provides an interface for managing enclosures.
     *
     * @return an interface to the enclosures REST API.
     */
    public synchronized EnclosureClient enclosure() {
        return this.getProxy(EnclosureClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link EnclosureGroupClient}.
     * This client provides an interface for managing enclosure groups.
     *
     * @return an interface to the enclosure groups REST API.
     */
    public synchronized EnclosureGroupClient enclosureGroup() {
        return this.getProxy(EnclosureGroupClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link EthernetNetworkClient}.
     * This client provides an interface for managing ethernet networks.
     *
     * @return an interface to the ethernet networks REST API.
     */
    public synchronized EthernetNetworkClient ethernetNetwork() {
        return this.getProxy(EthernetNetworkClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link FabricClient}.
     * This client provides an interface for managing fabrics.
     *
     * @return an interface to the fabrics REST API.
     */
    public synchronized FabricClient fabric() {
        return this.getProxy(FabricClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link FcNetworkClient}.
     * This client provides an interface for managing FC networks.
     *
     * @return an interface to the FC networks REST API.
     */
    public synchronized FcNetworkClient fcNetwork() {
        return this.getProxy(FcNetworkClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link FcoeNetworkClient}.
     * This client provides an interface for managing FCoE networks.
     *
     * @return an interface to the FCoE networks REST API.
     */
    public synchronized FcoeNetworkClient fcoeNetwork() {
        return this.getProxy(FcoeNetworkClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link FcSanDeviceManagerClient}.
     * This client provides an interface for managing SAN managers.
     *
     * @return an interface to the SAN managers REST API.
     */
    public synchronized FcSanDeviceManagerClient fcSanDeviceManager() {
        return this.getProxy(FcSanDeviceManagerClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link FcSanManagedSanClient}.
     * This client provides an interface for managing managed SANs.
     *
     * @return an interface to the managed SANs REST API.
     */
    public synchronized FcSanManagedSanClient fcSanManagedSan() {
        return this.getProxy(FcSanManagedSanClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link FcSanProviderClient}.
     * This client provides an interface for managing SAN providers.
     *
     * @return an interface to the SAN providers REST API.
     */
    public synchronized FcSanProviderClient fcSanProvider() {
        return this.getProxy(FcSanProviderClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link FirmwareBundleClient}.
     * This client provides an interface for managing firmware bundles.
     *
     * @return an interface to the firmware bundles REST API.
     */
    public synchronized FirmwareBundleClient firmwareBundle() {
        return this.getClient(FirmwareBundleClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link FirmwareDriverClient}.
     * This client provides an interface for managing firmware drivers.
     *
     * @return an interface to the firmware drivers REST API.
     */
    public synchronized FirmwareDriverClient firmwareDriver() {
        return this.getProxy(FirmwareDriverClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link InterconnectClient}.
     * This client provides an interface for managing interconnects.
     *
     * @return an interface to the interconnects REST API.
     */
    public synchronized InterconnectClient interconnect() {
        return this.getProxy(InterconnectClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link InterconnectLinkTopologyClient}.
     * This client provides an interface for managing interconnect link topologies.
     *
     * @return an interface to the interconnect link topologies REST API.
     */
    public synchronized InterconnectLinkTopologyClient interconnectLinkTopology() {
        return this.getProxy(InterconnectLinkTopologyClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link InterconnectTypeClient}.
     * This client provides an interface for managing interconnect types.
     *
     * @return an interface to the interconnect types REST API.
     */
    public synchronized InterconnectTypeClient interconnectType() {
        return this.getProxy(InterconnectTypeClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link InternalLinkSetClient}.
     * This client provides an interface for managing internal link sets.
     *
     * @return an interface to the internal link sets REST API.
     */
    public synchronized InternalLinkSetClient internalLinkSet() {
        return this.getProxy(InternalLinkSetClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link LogicalDownlinkClient}.
     * This client provides an interface for managing logical downlinks.
     *
     * @return an interface to the logical downlinks REST API.
     */
    public synchronized LogicalDownlinkClient logicalDownlink() {
        return this.getProxy(LogicalDownlinkClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link LogicalEnclosureClient}.
     * This client provides an interface for managing logical enclosures.
     *
     * @return an interface to the logical enclosures REST API.
     */
    public synchronized LogicalEnclosureClient logicalEnclosure() {
        return this.getProxy(LogicalEnclosureClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link LogicalInterconnectClient}.
     * This client provides an interface for managing logical interconnects.
     *
     * @return an interface to the logical interconnects REST API.
     */
    public synchronized LogicalInterconnectClient logicalInterconnect() {
        return this.getProxy(LogicalInterconnectClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link LogicalInterconnectGroupClient}.
     * This client provides an interface for managing logical interconnect groups.
     *
     * @return an interface to the logical interconnect groups REST API.
     */
    public synchronized LogicalInterconnectGroupClient logicalInterconnectGroup() {
        return this.getProxy(LogicalInterconnectGroupClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link LogicalSwitchClient}.
     * This client provides an interface for managing logical switches.
     *
     * @return an interface to the logical switches REST API.
     */
    public synchronized LogicalSwitchClient logicalSwitch() {
        return this.getProxy(LogicalSwitchClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link LogicalSwitchGroupClient}.
     * This client provides an interface for managing logical switch groups.
     *
     * @return an interface to the logical switch groups REST API.
     */
    public synchronized LogicalSwitchGroupClient logicalSwitchGroup() {
        return this.getProxy(LogicalSwitchGroupClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link MessagingCertificateClient}.
     * This client provides an interface for managing certificates.
     *
     * @return an interface to the certificates REST API.
     */
    public synchronized MessagingCertificateClient messagingCertificate() {
        return this.getClient(MessagingCertificateClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link NetworkSetClient}.
     * This client provides an interface for managing networks sets.
     *
     * @return an interface to the networks sets REST API.
     */
    public synchronized NetworkSetClient networkSet() {
        return this.getProxy(NetworkSetClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link PowerDeliveryDeviceClient}.
     * This client provides an interface for managing power delivery devices.
     *
     * @return an interface to the power delivery devices REST API.
     */
    public synchronized PowerDeliveryDeviceClient powerDeliveryDevice() {
        return this.getProxy(PowerDeliveryDeviceClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link RackClient}.
     * This client provides an interface for managing racks.
     *
     * @return an interface to the racks REST API.
     */
    public synchronized RackClient rack() {
        return this.getProxy(RackClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link SasInterconnectClient}.
     * This client provides an interface for managing SAS interconnects.
     *
     * @return an interface to the SAS interconnects REST API.
     */
    public synchronized SasInterconnectClient sasInterconnects() {
        return this.getProxy(SasInterconnectClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link SasInterconnectTypeClient}.
     * This client provides an interface for managing SAS interconnect types.
     *
     * @return an interface to the SAS interconnect types REST API.
     */
    public synchronized SasInterconnectTypeClient sasInterconnectType() {
        return this.getProxy(SasInterconnectTypeClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link SasLogicalInterconnectClient}.
     * This client provides an interface for managing SAS logical interconnects.
     *
     * @return an interface to the SAS logical interconnects REST API.
     */
    public synchronized SasLogicalInterconnectClient sasLogicalInterconnect() {
        return this.getProxy(SasLogicalInterconnectClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link SasLogicalInterconnectGroupClient}.
     * This client provides an interface for managing SAS logical interconnect groups.
     *
     * @return an interface to the SAS logical interconnect groups REST API.
     */
    public synchronized SasLogicalInterconnectGroupClient sasLogicalInterconnectGroup() {
        return this.getProxy(SasLogicalInterconnectGroupClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link SasLogicalJbodAttachmentClient}.
     * This client provides an interface for managing SAS logical JBOD attachments.
     *
     * @return an interface to the SAS logical JBOD attachments REST API.
     */
    public synchronized SasLogicalJbodAttachmentClient sasLogicalJbodAttachment() {
        return this.getProxy(SasLogicalJbodAttachmentClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link SasLogicalJbodClient}.
     * This client provides an interface for managing SAS logical JBODs.
     *
     * @return an interface to the SAS logical JBODs REST API.
     */
    public synchronized SasLogicalJbodClient sasLogicalJbod() {
        return this.getProxy(SasLogicalJbodClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link ServerHardwareClient}.
     * This client provides an interface for managing server hardware.
     *
     * @return an interface to the server hardware REST API.
     */
    public synchronized ServerHardwareClient serverHardware() {
        return this.getProxy(ServerHardwareClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link ServerHardwareTypeClient}.
     * This client provides an interface for managing server hardware types.
     *
     * @return an interface to the server hardware types REST API.
     */
    public synchronized ServerHardwareTypeClient serverHardwareType() {
        return this.getProxy(ServerHardwareTypeClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link ServerProfileClient}.
     * This client provides an interface for managing server profiles.
     *
     * @return an interface to the server profiles REST API.
     */
    public synchronized ServerProfileClient serverProfile() {
        return this.getProxy(ServerProfileClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link ServerProfileTemplateClient}.
     * This client provides an interface for managing server profile templates.
     *
     * @return an interface to the server profile templates REST API.
     */
    public synchronized ServerProfileTemplateClient serverProfileTemplate() {
        return this.getProxy(ServerProfileTemplateClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link StoragePoolClient}.
     * This client provides an interface for managing storage pools.
     *
     * @return an interface to the storage pools REST API.
     */
    public synchronized StoragePoolClient storagePool() {
        return this.getProxy(StoragePoolClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link StorageSystemClient}.
     * This client provides an interface for managing storage systems.
     *
     * @return an interface to the storage systems REST API.
     */
    public synchronized StorageSystemClient storageSystem() {
        return this.getProxy(StorageSystemClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link StorageVolumeClient}.
     * This client provides an interface for managing storage volumes.
     *
     * @return an interface to the storage volumes REST API.
     */
    public synchronized StorageVolumeClient storageVolume() {
        return this.getProxy(StorageVolumeClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link StorageVolumeAttachmentClient}.
     * This client provides an interface for managing storage volume attachments.
     *
     * @return an interface to the storage volume attachments REST API.
     */
    public synchronized StorageVolumeAttachmentClient storageVolumeAttachment() {
        return this.getProxy(StorageVolumeAttachmentClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link StorageVolumeTemplateClient}.
     * This client provides an interface for managing storage volume templates.
     *
     * @return an interface to the storage volume templates REST API.
     */
    public synchronized StorageVolumeTemplateClient storageVolumeTemplate() {
        return this.getProxy(StorageVolumeTemplateClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link SwitchClient}.
     * This client provides an interface for managing switches.
     *
     * @return an interface to the switches REST API.
     */
    public synchronized SwitchClient switches() {
        return this.getProxy(SwitchClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link SwitchTypeClient}.
     * This client provides an interface for managing switch types.
     *
     * @return an interface to the switch types REST API.
     */
    public synchronized SwitchTypeClient switchType() {
        return this.getProxy(SwitchTypeClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link UnmanagedDeviceClient}.
     * This client provides an interface for managing unmanaged devices.
     *
     * @return an interface to the unmanaged devices REST API.
     */
    public synchronized UnmanagedDeviceClient unmanagedDevice() {
        return this.getProxy(UnmanagedDeviceClient.class);
    }

    /**
     * Creates or retrieves an existing instance of {@link UplinkSetClient}.
     * This client provides an interface for managing uplink sets.
     *
     * @return an interface to the uplink sets REST API.
     */
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
            throw new SDKException(SDKErrorEnum.internalServerError, StringUtils.EMPTY, e);
        }
    }

}
