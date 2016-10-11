/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto.storage;


import java.util.List;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.RefreshState;

public class StorageVolumeAttachment extends BaseModelResource {

    private static final long serialVersionUID = 4991706012953235568L;

    private String hostName;
    private Boolean individualPathControl;
    private String lun;
    private String osType;
    private List<StorageVolumeAttachmentPath> paths;
    private RefreshState refreshState;
    private String serverProfileUri;
    private String stateReason;
    private String storageVolumeUri;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Boolean getIndividualPathControl() {
        return individualPathControl;
    }

    public void setIndividualPathControl(Boolean individualPathControl) {
        this.individualPathControl = individualPathControl;
    }

    public String getLun() {
        return lun;
    }

    public void setLun(String lun) {
        this.lun = lun;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public List<StorageVolumeAttachmentPath> getPaths() {
        return paths;
    }

    public void setPaths(List<StorageVolumeAttachmentPath> paths) {
        this.paths = paths;
    }

    public RefreshState getRefreshState() {
        return refreshState;
    }

    public void setRefreshState(RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    public String getServerProfileUri() {
        return serverProfileUri;
    }

    public void setServerProfileUri(String serverProfileUri) {
        this.serverProfileUri = serverProfileUri;
    }

    public String getStateReason() {
        return stateReason;
    }

    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    public String getStorageVolumeUri() {
        return storageVolumeUri;
    }

    public void setStorageVolumeUri(String storageVolumeUri) {
        this.storageVolumeUri = storageVolumeUri;
    }
}
