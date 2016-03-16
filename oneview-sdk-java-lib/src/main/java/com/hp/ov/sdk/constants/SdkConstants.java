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

public class SdkConstants {

    /**
     * PLEASE, try to keep it in alphabetical order
     */
    public static final String ACTIVE_OA_SSO_URL = "activeOaSsoUrl";
    public static final String ACTIVE_OA_SSO_URL_V200 = "sso";
    public static final String APPLIANCE = "appliance";
    public static final String BIOS = "bios";
    public static final String BULK_ETHERNET_NETWORK = "bulk-ethernet-network";
    public static final String CERTS = "appliance-certificates";
    public static final String COMPLIANCE = "compliance";
    public static final String CONFIGURATION = "configuration";
    public static final String CONNECTION_TEMPLATE = "connection-template";
    public static final String DEFAULT_SETTINGS = "defaultSettings";
    public static final String DEVICE_MANAGER = "device-managers";
    public static final String DEVICE_MANAGERS = DEVICE_MANAGER + "s";
    public static final String ENCLOSURE = "enclosures";
    public static final String ENCLOSURES = ENCLOSURE + "s";
    public static final String ENCLOSURE_FW_BASELINE = "enclosureFwBaseline";
    public static final String ENCLOSURE_GROUP = "enclosure-group";
    public static final String ENCLOSURE_GROUPS = ENCLOSURE_GROUP + "s";
    public static final String ENVIRONMENTAL_CONFIGURATION = "environmentalConfiguration";
    public static final String ETHERNET = "Ethernet";
    public static final String ETHERNET_SETTINGS = "ethernetSettings";
    public static final String FIBRE_CHANNEL = "FibreChannel";
    public static final String FIRMWARE_DRIVER = "firmware-drivers";
    public static final String FC_NETWORK = "FC_NETWORK";
    public static final String FC_NETWORKS = FC_NETWORK + "s";
    public static final String FCOE_NETWORK = "FCOE_NETWORK";
    public static final String FCOE_NETWORKS = FCOE_NETWORK + "s";
    public static final String FIRMWARE = "firmware";
    public static final String FORWARDING_INFORMATION_BASE = "forwarding-information-base";
    public static final String HTTPS = "https://";
    public static final String INTERCONNECT = "interconnect";
    public static final String INTERCONNECTS = INTERCONNECT + "s";
    public static final String INTERCONNECT_TYPE = "interconnect-type";
    public static final String INTERCONNECT_TYPES = INTERCONNECT_TYPE + "s";
    public static final String INTERNAL_NETWORKS = "internalNetworks";
    public static final String INTERNAL_VLANS = "internalVlans";
    public static final String LOCATIONS = "locations";
    public static final String LOGICAL_ENCLOSURE = "logical-enclosures";
    public static final String LOGICAL_INTERCONNECT = "logical-interconnects";
    public static final String LOGICAL_INTERCONNECTS = LOGICAL_INTERCONNECT + "s";
    public static final String LOGICAL_INTERCONNECT_GROUP = "logical-interconnect-group";
    public static final String LOGICAL_INTERCONNECT_GROUPS = LOGICAL_INTERCONNECT_GROUP + "s";
    public static final String LOGICAL_SWITCH_GROUPS = "logical-switch-groups";
    public static final String MANAGED_SAN = "managed-sans";
    public static final String MANAGED_SANS = MANAGED_SAN + "s";
    public static final String MSMB_ALERTS_ROUTING_KEY = "msmb.#";
    public static final String MSMB_EXCHANGE_NAME = "msmb";
    public static final String NETWORK = "ethernet-network";
    public static final String NETWORKS = NETWORK + "s";
    public static final String NETWORKSET = "network-set";
    public static final String NETWORKSETS = NETWORKSET + "s";
    public static final int PERCENTAGE = 100;
    public static final String PORT_MONITOR = "port-monitor";
    public static final String POWERSTATE = "powerState";
    public static final String PROVIDERS = "providers";
    public static final String QOS_AGGREGATED_CONFIGURATION = "qos-aggregated-configuration";
    public static final String RABBITMQ = "certificates-client-rabbitmq";
    public static final String REFRESH_STATE = "refreshState";
    public static final String SCMB_ALERTS_ROUTING_KEY = "scmb.alerts.#";
    public static final String SCMB_CERTS = "rabbit-mq-certificates";
    public static final String SCMB_CONNECTION = "scmb connection";
    public static final String SCMB_EXCHANGE_NAME = "scmb";
    public static final String SCMB_PROCESSOR = "scmb-processor";
    public static final String SCRIPT = "script";
    public static final String SERVER_HARDWARE = "server-hardware";
    public static final String SERVER_HARDWARES = SERVER_HARDWARE + "s";
    public static final String SERVER_PROFILE = "server-profile";
    public static final String SERVER_PROFILES = SERVER_PROFILE + "s";
    public static final String SETTINGS = "settings";
    public static final String SNMP_CONFIGURATION = "snmp-configuration";
    public static final String STANDBY_OA_SSO_URL = "standbyOaSsoUrl";
    public static final String STANDBY_OA_SSO_URL_V200 = "sso";
    public static final String SSL_VERSION = "TLSv1.2";
    public static final String STORAGE_ATTACHABLE_VOLUMES = "attachable-volumes";
    public static final String STORAGE_POOL = "storage-pool";
    public static final String STORAGE_POOLS = STORAGE_POOL + "s";
    public static final String STORAGE_SNAPSHOT = "storage-snapshot";
    public static final String STORAGE_SNAPSHOTS = STORAGE_SNAPSHOT + "s";
    public static final String STORAGE_SYSTEM = "storage-system";
    public static final String STORAGE_SYSTEMS = STORAGE_SYSTEM + "s";
    public static final String STORAGE_VOLUME = "storage-volume";
    public static final String STORAGE_VOLUMES = STORAGE_VOLUME + "s";
    public static final String STORAGE_VOLUME_ATTACHMENTS = "storage-volume-attachments";
    public static final String STORAGE_VOLUME_TEMPLATE = "storage-volume-template";
    public static final String STORAGE_VOLUME_TEMPLATES = STORAGE_VOLUME_TEMPLATE + "s";
    public static final String SUPPORT_DUMP = "support-dumps";
    public static final String SWITCHES = "switches";
    public static final String TELEMETRY_CONFIGURATIONS = "telemetry-configurations";
    public static final String UNASSIGNED_UPLINK_PORTS_FOR_PORT_MONITOR = "unassignedUplinkPortsForPortMonitor";
    public static final String UPDATE_FROM_GROUP = "updateFromGroup";
    public static final String UPLINKSET = "uplink-set";
    public static final String UPLINKSETS = UPLINKSET + "s";
    public static final String UTILIZATION = "utilization";

    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN_REQUEST = 403;
    public static final int URL_NOT_FOUND = 404;
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int CONFLICT_DUE_TO_STATE = 409;
    public static final int PRECONDITION_FAILED = 412;
    public static final int UNSUPPORTED_MEDIA_TYPE = 415;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int SERVICE_UNAVAILABLE = 503;

    public static final String JSON_TO_OBJECT_CONVERSION = "Json-to-object";
    public static final String OBJECT_TO_JOSON_CONVERSION = "Object-to-json";
    public static final String TASK_MONITOR = "task-monitor";

    public static final String FILTER_PREFIX = "filter=\"name=\'";
    public static final String FILTER_APPEND = "\'\"";

    public static final String QUERY_PREFIX = "query=name eq \"";
    public static final String QUERY_APPEND = "\"";

}
