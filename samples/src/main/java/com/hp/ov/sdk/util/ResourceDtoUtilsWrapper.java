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
package com.hp.ov.sdk.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hp.ov.sdk.dto.samples.NetworkForServerProfile;
import com.hp.ov.sdk.dto.samples.SanStorageForServerProfile.StorageVolume;
import com.hp.ov.sdk.dto.samples.ServerProfileValue;
import com.hp.ov.sdk.dto.servers.SanStorage;
import com.hp.ov.sdk.dto.servers.VolumeAttachment;
import com.hp.ov.sdk.dto.servers.serverprofile.ProfileConnection;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;

public class ResourceDtoUtilsWrapper {

    private final ResourceDtoUtils resourceDtoUtils;

    public ResourceDtoUtilsWrapper(ResourceDtoUtils resourceDtoUtils) {
        this.resourceDtoUtils = resourceDtoUtils;
    }

    public ServerProfile buildServerProfile(ApiVersion apiVersion, final ServerProfileValue serverProfileValue) {
        Integer j = 1;
        final HashMap<String, Integer> fcId = new HashMap<String, Integer>();
        List<ProfileConnection> connections = new ArrayList<ProfileConnection>();
        SanStorage sanStorage;

        if (serverProfileValue.getNetworkForServerProfile().size() > 0) {
            for (final NetworkForServerProfile networkForServerProfile : serverProfileValue.getNetworkForServerProfile()) {
                connections.add(resourceDtoUtils.buildProfileConnection(j, networkForServerProfile.getNetworkName(),
                        networkForServerProfile.getRequestedMbps(), networkForServerProfile.getAllocatedMbps(),
                        networkForServerProfile.getMaximumMbps(), networkForServerProfile.getNetworkType(),
                        networkForServerProfile.getBoot(), networkForServerProfile.getConnectionName()));
                if ((networkForServerProfile.getNetworkType().toString()).equalsIgnoreCase("FibreChannel")) {
                    fcId.put(networkForServerProfile.getNetworkName(), j);
                }
                j++;
            }
        } else {
            connections = null;
        }
        if (serverProfileValue.getStorageVolumeForServerProfile().getStorageVolume().size() > 0) {
            final List<VolumeAttachment> volumeAttachments = new ArrayList<VolumeAttachment>();
            for (final StorageVolume storageVolume : serverProfileValue.getStorageVolumeForServerProfile().getStorageVolume()) {
                volumeAttachments.add(resourceDtoUtils.buildVolumeAttachment(storageVolume.getVolumeName(),
                        serverProfileValue.getUseBayNameForServerHardwareUri(), j, storageVolume.getIsEnabled(),
                        storageVolume.getStorageTargets(), storageVolume.getStorageTargetType(), storageVolume.getLunType(), fcId));
                j++;
            }
            sanStorage = resourceDtoUtils.buildSanStorage(serverProfileValue.getStorageVolumeForServerProfile()
                    .getHostOSType(), volumeAttachments);
        } else {
            sanStorage = null;
        }

        return resourceDtoUtils.buildServerProfile(apiVersion, serverProfileValue.getTemplateName(), serverProfileValue.getBayName(),
                serverProfileValue.getUseBayNameForServerHardwareUri(), serverProfileValue.getEnclosureGroupName(),
                serverProfileValue.getAffinity(), serverProfileValue.getWwnType(), serverProfileValue.getMacType(),
                serverProfileValue.getSerialNumberType(), sanStorage, connections, serverProfileValue.getLocalStorage(),
                serverProfileValue.getBoot(), serverProfileValue.getBios(), serverProfileValue.getFirmware(),
                serverProfileValue.getOsDeploymentSettings());
    }
}
