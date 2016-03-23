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
    public static final String BULK_ETHERNET_URI = "/rest/ethernet-networks/bulk";
    public static final String CA_CERT_URI = "/rest/certificates/ca";
    public static final String CONNECTION_TEMPLATES = "/rest/connection-templates";
    public static final String CONNECTION_TEMPLATE_URI = "/rest/connection-templates";
    public static final String DEFAULT_CONNECTION_TEMPLATE_URI = "/rest/connection-templates/defaultConnectionTemplate";
    public static final String ENCLOSURE_URI = "/rest/enclosures";
    public static final String ENCLOSURE_GROUP_URI = "/rest/enclosure-groups";
    public static final String ENVIRONMENT_CONFIGURATION_URI = "environmentalConfiguration";
    public static final String FC_NETWORK_URI = "/rest/fc-networks";
    public static final String FC_SANS_DEVICE_MANAGER = "device-managers";
    public static final String FC_SANS_DEVICE_MANAGER_URI = "/rest/fc-sans/device-managers";
    public static final String FC_SANS_MANAGED_SAN_ENDPOINTS = "endpoints";
    public static final String FC_SANS_MANAGED_SAN_ISSUES = "issues";
    public static final String FC_SANS_PROVIDER_URI = "/rest/fc-sans/providers";
    public static final String FC_SANS_MANAGED_SAN_URI = "/rest/fc-sans/managed-sans";
    public static final String FCOE_NETWORK_URI = "/rest/fcoe-networks";
    public static final String FIRMWARE_DRIVER_URI = "/rest/firmware-drivers";
    public static final String ETHERNET_URI = "/rest/ethernet-networks";
    public static final String INTERCONNECT_TYPE_URI = "/rest/interconnect-types";
    public static final String INTERCONNECT_URI = "/rest/interconnects";
    public static final String LOGICAL_ENCLOSURE_URI = "/rest/logical-enclosures";
    public static final String LOGICAL_INTERCONNECT_GROUPS_URI = "/rest/logical-interconnect-groups";
    public static final String LOGICAL_INTERCONNECT_URI = "/rest/logical-interconnects";
    public static final String LOGICAL_SWITCH_GROUPS_URI = "/rest/logical-switch-groups";
    public static final String LOGICAL_SWITCHES_REFRESH_URI = "refresh";
    public static final String LOGICAL_SWITCHES_URI = "/rest/logical-switches";
    public static final String LOGIN_SESSIONS = "/rest/login-sessions";
    public static final String MANANGED_PORTS_STORAGE_SYSTEM_URI = "managedPorts";
    public static final String NETWORK_SETS_URI = "/rest/network-sets";
    public static final String POWER_STATE_URI = "powerState";
    public static final String PROFILE_PORTS_URI = SERVER_PROFILE_URI + "/profile-ports";
    public static final String RABBIT_MQ_CLIENT_CERT = "/rest/certificates/client/rabbitmq";
    public static final String RABBIT_MQ_CLIENT_CERT_KEYPAIR = "/rest/certificates/client/rabbitmq/keypair/default";
    public static final String SERVER_HARWARE_URI = "/rest/server-hardware";
    public static final String STORAGE_POOL_STORAGE_SYSTEM_URI = "storage-pools";
    public static final String STORAGE_POOL_URI = "/rest/storage-pools";
    public static final String STORAGE_SYSTEM_URI = "/rest/storage-systems";
    public static final String STORAGE_SYSTEM_HOST_TYPES_URI = "/host-types";
    public static final String STORAGE_VOLUME_ATTACHMENT_PATH_URI = "paths";
    public static final String STORAGE_VOLUME_ATTACHMENT_REPAIR_URI = STORAGE_VOLUME_ATTACHMENT_URI + "/repair";
    public static final String STORAGE_VOLUME_ATTACHABLE_URI = STORAGE_VOLUME_URI + "/attachable-volumes";
    public static final String STORAGE_VOLUME_REPAIR_URI = STORAGE_VOLUME_URI + "/repair";
    public static final String STORAGE_VOLUME_SNAPSHOTS_URI = "snapshots";
    public static final String STORAGE_VOLUME_TEMPLATE_URI = "/rest/storage-volume-templates";
    public static final String STORAGE_VOLUME_TEMPLATE_CONNECTABLE_URI = "connectable-volume-templates";
    public static final String SWITCHES_ENVIRONMENTAL_CONFIGURATION_URI = "environmentalConfiguration";
    public static final String SWITCHES_STATISTICS_URI = "statistics";
    public static final String SWITCHES_URI = "/rest/switches";
    public static final String TASK_URI = "/rest/tasks";
    public static final String UPLINK_SETS_URI = "/rest/uplink-sets";

}
