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


    public static final String APPLIANCE_VERSION = "/rest/version";
    public static final String AVAILABLE_NETWORKS_URI = SERVER_PROFILE_URI + "/available-networks";
    public static final String AVAILABLE_SERVERS_URI = SERVER_PROFILE_URI + "/available-servers";
    public static final String AVAILABLE_STORAGE_SYSTEM = SERVER_PROFILE_URI + "/available-storage-system";
    public static final String AVAILABLE_STORAGE_SYSTEMS = AVAILABLE_STORAGE_SYSTEM + "s";
    public static final String AVAILABLE_TARGETS = SERVER_PROFILE_URI + "/available-targets";
    public static final String ENVIRONMENT_CONFIGURATION_URI = "environmentalConfiguration";
    public static final String FIRMWARE_BUNDLE_URI = "/rest/firmware-bundles";
    public static final String FIRMWARE_DRIVER_URI = "/rest/firmware-drivers";
    public static final String LOGIN_SESSIONS = "/rest/login-sessions";
    public static final String PROFILE_PORTS_URI = SERVER_PROFILE_URI + "/profile-ports";
    public static final String SERVER_HARDWARE_TYPE_URI = "/rest/server-hardware-types";
    public static final String SERVER_PROFILE_COMPLIANCE_PREVIEW_URI = "compliance-preview";
    public static final String SERVER_PROFILE_COMPLIANCE_MESSAGES_URI = "messages";
    public static final String SERVER_PROFILE_COMPLIANCE_TRANSFORMATION_URI = "transformation";
    public static final String TASK_URI = "/rest/tasks";

}
