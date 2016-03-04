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
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class StorageVolumeProvisioningParameters implements Serializable {

    private static final long serialVersionUID = 4987198808352824437L;

    private String provisionType;
    private String requestedCapacity;
    private Boolean shareable;
    private String storagePoolUri;

    public String getProvisionType() {
        return provisionType;
    }

    public void setProvisionType(final String provisionType) {
        this.provisionType = provisionType;
    }

    public String getRequestedCapacity() {
        return requestedCapacity;
    }

    public void setRequestedCapacity(final String requestedCapacity) {
        this.requestedCapacity = requestedCapacity;
    }

    public Boolean getShareable() {
        return shareable;
    }

    public void setShareable(final Boolean shareable) {
        this.shareable = shareable;
    }

    public String getStoragePoolUri() {
        return storagePoolUri;
    }

    public void setStoragePoolUri(final String storagePoolUri) {
        this.storagePoolUri = storagePoolUri;
    }

}
