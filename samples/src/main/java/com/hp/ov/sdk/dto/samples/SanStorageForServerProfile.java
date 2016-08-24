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
package com.hp.ov.sdk.dto.samples;

import java.util.ArrayList;
import java.util.List;

import com.hp.ov.sdk.dto.servers.StorageTargetType;


public class SanStorageForServerProfile {

    private String hostOSType;
    private List<StorageVolume> storageVolume = new ArrayList<SanStorageForServerProfile.StorageVolume>();

    public class StorageVolume {
        private String volumeName;
        private List<String> storageTargets = new ArrayList<String>();
        private StorageTargetType storageTargetType;
        private String lunType;
        private Boolean isEnabled;

        public String getVolumeName() {
            return volumeName;
        }

        public void setVolumeName(final String volumeName) {
            this.volumeName = volumeName;
        }

        public List<String> getStorageTargets() {
            return storageTargets;
        }

        public void setStorageTargets(final List<String> storageTargets) {
            this.storageTargets = storageTargets;
        }

        public StorageTargetType getStorageTargetType() {
            return storageTargetType;
        }

        public void setStorageTargetType(final StorageTargetType storageTargetType) {
            this.storageTargetType = storageTargetType;
        }

        public String getLunType() {
            return lunType;
        }

        public void setLunType(final String lunType) {
            this.lunType = lunType;
        }

        public Boolean getIsEnabled() {
            return isEnabled;
        }

        public void setIsEnabled(final Boolean isEnabled) {
            this.isEnabled = isEnabled;
        }
    }

    public String getHostOSType() {
        return hostOSType;
    }

    public void setHostOSType(final String hostOSType) {
        this.hostOSType = hostOSType;
    }

    public List<StorageVolume> getStorageVolume() {
        return storageVolume;
    }

    public void setStorageVolume(final List<StorageVolume> storageVolume) {
        this.storageVolume = storageVolume;
    }

    public StorageVolume createStorageVolume() {
        return new StorageVolume();
    }
}
