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

    public static final String APPLIANCE_VERSION = "/rest/version";
    public static final String AVAILABLE_NETWORKS_URI = SERVER_PROFILE_URI + "/available-networks";
    public static final String AVAILABLE_SERVERS_URI = SERVER_PROFILE_URI + "/available-servers";
    public static final String AVAILABLE_STORAGE_SYSTEM = SERVER_PROFILE_URI + "/available-storage-system";
    public static final String AVAILABLE_STORAGE_SYSTEMS = AVAILABLE_STORAGE_SYSTEM + "s";
    public static final String AVAILABLE_TARGETS = SERVER_PROFILE_URI + "/available-targets";
    public static final String ENVIRONMENT_CONFIGURATION_URI = "environmentalConfiguration";
    public static final String FIRMWARE_BUNDLE_URI = "/rest/firmware-bundles";
    public static final String FIRMWARE_DRIVER_URI = "/rest/firmware-drivers";
    public static final String INTERCONNECT_URI = "/rest/interconnects";
    public static final String INTERCONNECT_PORTS_URI = "ports";
    public static final String INTERCONNECT_STATISTICS_URI = "statistics";
    public static final String INTERCONNECT_SUBPORT_URI = "subport";
    public static final String INTERCONNECT_NAME_SERVERS_URI = "nameServers";
    public static final String INTERCONNECT_UPDATE_PORTS_URI = "update-ports";
    public static final String INTERCONNECT_RESET_PORT_PROTECTION_URI = "resetportprotection";
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
    public static final String LOGIN_SESSIONS = "/rest/login-sessions";
    public static final String PROFILE_PORTS_URI = SERVER_PROFILE_URI + "/profile-ports";
    public static final String SERVER_HARDWARE_TYPE_URI = "/rest/server-hardware-types";
    public static final String SERVER_PROFILE_COMPLIANCE_PREVIEW_URI = "compliance-preview";
    public static final String SERVER_PROFILE_COMPLIANCE_MESSAGES_URI = "messages";
    public static final String SERVER_PROFILE_COMPLIANCE_TRANSFORMATION_URI = "transformation";
    public static final String STORAGE_POOL_URI = "/rest/storage-pools";
    public static final String STORAGE_VOLUME_ATTACHMENT_PATH_URI = "paths";
    public static final String STORAGE_VOLUME_ATTACHMENT_REPAIR_URI = STORAGE_VOLUME_ATTACHMENT_URI + "/repair";
    public static final String TASK_URI = "/rest/tasks";

}
