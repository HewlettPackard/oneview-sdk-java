/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.constants;

public class ResourceUris {

    /**
     * PLEASE, try to keep it in alphabetical order
     */
    public static final String SERVER_PROFILE_URI = "/rest/server-profiles";
    public static final String STORAGE_VOLUME_ATTACHMENT_URI = "/rest/storage-volume-attachments";
    public static final String STORAGE_VOLUME_URI = "/rest/storage-volumes";

    public static final String APPLIANCE_VERSION = "/rest/version";
    public static final String ASSOCIATED_PROFILES = "associatedProfiles";
    public static final String ASSOCIATED_UPLINK_GROUPS = "associatedUplinkGroups";
    public static final String AVAILABLE_NETWORKS_URI = SERVER_PROFILE_URI + "/available-networks";
    public static final String AVAILABLE_SERVERS_URI = SERVER_PROFILE_URI + "/available-servers";
    public static final String AVAILABLE_STORAGE_SYSTEM = SERVER_PROFILE_URI + "/available-storage-system";
    public static final String AVAILABLE_STORAGE_SYSTEMS = AVAILABLE_STORAGE_SYSTEM + "s";
    public static final String AVAILABLE_TARGETS = SERVER_PROFILE_URI + "/available-targets";
    public static final String BULK_ETHERNET_URI = "/rest/ethernet-networks/bulk";
    public static final String CONNECTION_TEMPLATES = "/rest/connection-templates";
    public static final String CONNECTION_TEMPLATE_URI = "/rest/connection-templates";
    public static final String DATA_CENTER_URI = "/rest/datacenters";
    public static final String DATA_CENTER_VISUAL_CONTENT_URI = "visualContent";
    public static final String DEFAULT_CONNECTION_TEMPLATE_URI = "/rest/connection-templates/defaultConnectionTemplate";
    public static final String ENCLOSURE_GROUP_SCRIPT_URI = "script";
    public static final String ENCLOSURE_GROUP_URI = "/rest/enclosure-groups";
    public static final String ENVIRONMENT_CONFIGURATION_URI = "environmentalConfiguration";
    public static final String FC_SANS_DEVICE_MANAGER = "device-managers";
    public static final String FC_SANS_DEVICE_MANAGER_URI = "/rest/fc-sans/device-managers";
    public static final String FC_SANS_PROVIDER_URI = "/rest/fc-sans/providers";
    public static final String FCOE_NETWORK_URI = "/rest/fcoe-networks";
    public static final String FIRMWARE_BUNDLE_URI = "/rest/firmware-bundles";
    public static final String FIRMWARE_DRIVER_URI = "/rest/firmware-drivers";
    public static final String ETHERNET_URI = "/rest/ethernet-networks";
    public static final String INTERCONNECT_TYPE_URI = "/rest/interconnect-types";
    public static final String INTERCONNECT_URI = "/rest/interconnects";
    public static final String INTERCONNECT_PORTS_URI = "ports";
    public static final String INTERCONNECT_STATISTICS_URI = "statistics";
    public static final String INTERCONNECT_SUBPORT_URI = "subport";
    public static final String INTERCONNECT_NAME_SERVERS_URI = "nameServers";
    public static final String INTERCONNECT_UPDATE_PORTS_URI = "update-ports";
    public static final String INTERCONNECT_RESET_PORT_PROTECTION_URI = "resetportprotection";
    public static final String LOGICAL_DOWNLINK_URI = "/rest/logical-downlinks";
    public static final String LOGICAL_INTERCONNECT_GROUPS_URI = "/rest/logical-interconnect-groups";
    public static final String LOGICAL_INTERCONNECT_URI = "/rest/logical-interconnects";
    public static final String LOGICAL_INTERCONNECT_FIRMWARE_URI = "firmware";
    public static final String LOGICAL_INTERCONNECT_LOCATION_URI = "locations";
    public static final String LOGICAL_INTERCONNECT_INTERCONNECTS_URI = "interconnects";
    public static final String LOGICAL_INTERCONNECT_SNMP_CONFIGURATION_URI = "snmp-configuration";
    public static final String LOGICAL_INTERCONNECT_FORWARDING_INFORMATION_BASE_URI = "forwarding-information-base";
    public static final String LOGICAL_INTERCONNECT_CONFIGURATION_URI = "configuration";
    public static final String LOGICAL_INTERCONNECT_QOS_AGGREGATED_CONFIGURATION_URI = "qos-aggregated-configuration";
    public static final String LOGICAL_INTERCONNECT_TELEMETRY_CONFIGURATION_URI = "telemetry-configurations";
    public static final String LOGICAL_INTERCONNECT_PORT_MONITOR_URI = "port-monitor";
    public static final String LOGICAL_INTERCONNECT_SETTINGS_URI = "settings";
    public static final String LOGICAL_INTERCONNECT_ETHERNET_SETTINGS_URI = "ethernetSettings";
    public static final String LOGICAL_INTERCONNECT_COMPLIANCE_URI = "compliance";
    public static final String LOGICAL_INTERCONNECT_INTERNAL_NETWORKS_URI = "internalNetworks";
    public static final String LOGICAL_INTERCONNECT_INTERNAL_VLANS_URI = "internalVlans";
    public static final String LOGICAL_INTERCONNECT_UNASSIGNED_UPLINK_PORTS_URI = "unassignedUplinkPortsForPortMonitor";
    public static final String LOGICAL_SWITCH_GROUPS_URI = "/rest/logical-switch-groups";
    public static final String LOGICAL_SWITCHES_REFRESH_URI = "refresh";
    public static final String LOGICAL_SWITCHES_URI = "/rest/logical-switches";
    public static final String LOGIN_SESSIONS = "/rest/login-sessions";
    public static final String MANAGED_PORTS_STORAGE_SYSTEM_URI = "managedPorts";
    public static final String NETWORK_SETS_URI = "/rest/network-sets";
    public static final String POWER_DEVICE_DISCOVERY_URI = "/rest/power-devices/discover";
    public static final String POWER_DEVICE_POWER_STATE_URI = "powerState";
    public static final String POWER_DEVICE_REFRESH_STATE_URI = "refreshState";
    public static final String POWER_DEVICE_UID_STATE_URI = "uidState";
    public static final String POWER_DEVICE_UTILIZATION_URI = "utilization";
    public static final String POWER_DEVICE_SYNCHRONOUS_URI = "synchronous";
    public static final String POWER_DEVICE_URI = "/rest/power-devices";
    public static final String PROFILE_PORTS_URI = SERVER_PROFILE_URI + "/profile-ports";
    public static final String RACK_DEVICE_TOPOLOGY = "deviceTopology";
    public static final String RACK_URI = "/rest/racks";
    public static final String SERVER_HARDWARE_TYPE_URI = "/rest/server-hardware-types";
    public static final String SERVER_PROFILE_COMPLIANCE_PREVIEW_URI = "compliance-preview";
    public static final String SERVER_PROFILE_COMPLIANCE_MESSAGES_URI = "messages";
    public static final String SERVER_PROFILE_COMPLIANCE_TRANSFORMATION_URI = "transformation";
    public static final String STORAGE_POOL_STORAGE_SYSTEM_URI = "storage-pools";
    public static final String STORAGE_POOL_URI = "/rest/storage-pools";
    public static final String STORAGE_SYSTEM_URI = "/rest/storage-systems";
    public static final String STORAGE_SYSTEM_HOST_TYPES_URI = STORAGE_SYSTEM_URI + "/host-types";
    public static final String STORAGE_VOLUME_ATTACHMENT_PATH_URI = "paths";
    public static final String STORAGE_VOLUME_ATTACHMENT_REPAIR_URI = STORAGE_VOLUME_ATTACHMENT_URI + "/repair";
    public static final String STORAGE_VOLUME_ATTACHABLE_URI = STORAGE_VOLUME_URI + "/attachable-volumes";
    public static final String STORAGE_VOLUME_REPAIR_URI = STORAGE_VOLUME_URI + "/repair";
    public static final String STORAGE_VOLUME_SNAPSHOTS_URI = "snapshots";
    public static final String STORAGE_VOLUME_TEMPLATE_URI = "/rest/storage-volume-templates";
    public static final String STORAGE_VOLUME_TEMPLATE_CONNECTABLE_URI = "connectable-volume-templates";
    public static final String SWITCH_TYPE_URI = "/rest/switch-types";
    public static final String SWITCHES_STATISTICS_URI = "statistics";
    public static final String SWITCHES_URI = "/rest/switches";
    public static final String SWITCHES_UPDATE_PORTS_URI = "update-ports";
    public static final String TASK_URI = "/rest/tasks";
    public static final String UNMANAGED_DEVICE_URI = "/rest/unmanaged-devices";
    public static final String UPLINK_SETS_URI = "/rest/uplink-sets";
    public static final String WITHOUT_ETHERNET = "withoutEthernet";

}
