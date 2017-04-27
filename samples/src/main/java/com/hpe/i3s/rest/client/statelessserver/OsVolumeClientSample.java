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
package com.hpe.i3s.rest.client.statelessserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hpe.i3s.client.statelessserver.OsVolumeClient;
import com.hpe.i3s.dto.statelessserver.osvolume.OsVolume;
import com.hpe.i3s.rest.client.ImageStreamerClient;
import com.hpe.i3s.rest.client.deployment.ImageStreamerClientSample;

public class OsVolumeClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsVolumeClientSample.class);

    // These are variables to be defined by user
    // ================================
    public static final String OS_VOLUME_RESOURCE_ID = "6bc3fbbf-7d4f-44de-bd04-fdd82814ad1b";
    private static final String ARCHIVED_OS_VOLUME_RESOURCE_ID = "123";
    private static final String OS_VOLUME_NAME = "OSVolume-5";
    // ================================

    private final OsVolumeClient osVolumeClient;

    public OsVolumeClientSample() {
        ImageStreamerClient i3sClient = new ImageStreamerClientSample().getImageStreamerClient();

        this.osVolumeClient = i3sClient.osVolume();
    }

    private void getOsVolumeById() {
        OsVolume osVolume = this.osVolumeClient.getById(OS_VOLUME_RESOURCE_ID);

        LOGGER.info("OS Volume object returned to client : " + osVolume.toJsonString());
    }

    private void getAllOsVolumes() {
        ResourceCollection<OsVolume> osVolumes = this.osVolumeClient.getAll();

        LOGGER.info("OS Volumes returned to client : " + osVolumes.toJsonString());
    }

    private void getOsVolumeByName() {
        OsVolume osVolume = this.osVolumeClient.getByName(OS_VOLUME_NAME).get(0);

        LOGGER.info("OS Volume object returned to client : " + osVolume.toJsonString());
    }

    private void getArchivedOsVolume() {
        OsVolume osVolume = this.osVolumeClient.getArchivedOsVolume(ARCHIVED_OS_VOLUME_RESOURCE_ID);

        LOGGER.info("Archived OS Volume object returned to client : " + osVolume.toJsonString());
    }

    public static void main(String[] args) {
        /* This resource is available only on HPE Synergy */
        OsVolumeClientSample sample = new OsVolumeClientSample();

        sample.getOsVolumeByName();
        sample.getOsVolumeById();
        sample.getAllOsVolumes();
        sample.getArchivedOsVolume();
    }

}
