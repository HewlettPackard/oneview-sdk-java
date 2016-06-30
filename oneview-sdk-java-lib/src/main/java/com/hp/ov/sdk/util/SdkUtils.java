/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.util;

import com.hp.ov.sdk.rest.client.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.EnclosureGroupClientImpl;
import com.hp.ov.sdk.rest.client.ServerHardwareClient;
import com.hp.ov.sdk.rest.client.ServerHardwareClientImpl;
import com.hp.ov.sdk.rest.client.StorageVolumeClient;
import com.hp.ov.sdk.rest.client.StorageVolumeClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

/**
 * DO NOT USE this class under any circumstances!
 * Soon this class will be deleted and if you are using it, it means you should also be deleted!
 */
@Deprecated
public class SdkUtils {
    private final ServerHardwareClient serverHardwareClient;
    private final EnclosureGroupClient enclosureGroupClient;
    private final StorageVolumeClient storageVolumeClient;

    private static final class SdkUtilsHolder {
        private static final SdkUtils INSTANCE = new SdkUtils(
                ServerHardwareClientImpl.getClient(),
                EnclosureGroupClientImpl.getClient(),
                StorageVolumeClientImpl.getClient());
    }

    private SdkUtils(
        ServerHardwareClient serverHardwareClient,
        EnclosureGroupClient enclosureGroupClient,
        StorageVolumeClient storageVolumeClient) {

        this.serverHardwareClient = serverHardwareClient;
        this.enclosureGroupClient = enclosureGroupClient;
        this.storageVolumeClient = storageVolumeClient;
    }

    public static SdkUtils getInstance() {
        return SdkUtilsHolder.INSTANCE;
    }

    public String getServerHardwareUri(final RestParams params, final String serverHardwareName) {
        return serverHardwareClient.getServerHardwareByName(params, serverHardwareName).getUri();
    }

    public String getServerHardwareTypeUri(final RestParams params, final String serverHardwareName) {
        return serverHardwareClient.getServerHardwareByName(params, serverHardwareName).getServerHardwareTypeUri();
    }

    public String getEnclosureGroupUri(final RestParams params, final String enclosureGroupName) {
        return enclosureGroupClient.getEnclosureGroupByName(params, enclosureGroupName).getUri();
    }

    public Boolean isVolumeSharable(final RestParams params, final String volumeName) {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName).getShareable();
    }

    public String getVolumeUri(final RestParams params, final String volumeName) {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName).getUri();
    }

    public String getStoragePoolFromVolume(final RestParams params, final String volumeName) {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName).getStoragePoolUri();
    }

    public String getStorageSystemFromVolume(final RestParams params, final String volumeName) {
        return storageVolumeClient.getStorageVolumeByName(params, volumeName).getStorageSystemUri();
    }
}
