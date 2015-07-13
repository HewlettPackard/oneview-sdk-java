/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.dto;

import java.util.ArrayList;
import java.util.List;

import com.hp.ov.sdk.dto.generated.StoragePath.StorageTargetType;

public class SanStorageForServerProfile
{

    private String hostOSType;
    private List<StorageVolume> storageVolume = new ArrayList<SanStorageForServerProfile.StorageVolume>();

    public class StorageVolume
    {
        private String volumeName;
        private List<String> storageTargets = new ArrayList<String>();
        private StorageTargetType storageTargetType;
        private String lunType;
        private Boolean isEnabled;

        public String getVolumeName()
        {
            return volumeName;
        }

        public void setVolumeName(String volumeName)
        {
            this.volumeName = volumeName;
        }

        public List<String> getStorageTargets()
        {
            return storageTargets;
        }

        public void setStorageTargets(List<String> storageTargets)
        {
            this.storageTargets = storageTargets;
        }

        public StorageTargetType getStorageTargetType()
        {
            return storageTargetType;
        }

        public void setStorageTargetType(StorageTargetType storageTargetType)
        {
            this.storageTargetType = storageTargetType;
        }

        public String getLunType()
        {
            return lunType;
        }

        public void setLunType(String lunType)
        {
            this.lunType = lunType;
        }

        public Boolean getIsEnabled()
        {
            return isEnabled;
        }

        public void setIsEnabled(Boolean isEnabled)
        {
            this.isEnabled = isEnabled;
        }
    }

    public String getHostOSType()
    {
        return hostOSType;
    }

    public void setHostOSType(String hostOSType)
    {
        this.hostOSType = hostOSType;
    }

    public List<StorageVolume> getStorageVolume()
    {
        return storageVolume;
    }

    public void setStorageVolume(List<StorageVolume> storageVolume)
    {
        this.storageVolume = storageVolume;
    }

    public StorageVolume createStorageVolume()
    {
        return new StorageVolume();
    }
}
