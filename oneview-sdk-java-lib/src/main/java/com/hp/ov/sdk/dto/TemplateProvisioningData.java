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
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class TemplateProvisioningData implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String capacity;
    private String provisionType;
    private Boolean shareable;
    private String storagePoolUri;

    /**
     * 
     * @return The capacity
     */
    public String getCapacity() {
        return capacity;
    }

    /**
     * 
     * @param capacity
     *            The capacity
     */
    public void setCapacity(final String capacity) {
        this.capacity = capacity;
    }

    /**
     * 
     * @return The provisionType
     */
    public String getProvisionType() {
        return provisionType;
    }

    /**
     * 
     * @param provisionType
     *            The provisionType
     */
    public void setProvisionType(final String provisionType) {
        this.provisionType = provisionType;
    }

    /**
     * 
     * @return The shareable
     */
    public Boolean getShareable() {
        return shareable;
    }

    /**
     * 
     * @param shareable
     *            The shareable
     */
    public void setShareable(final Boolean shareable) {
        this.shareable = shareable;
    }

    /**
     * 
     * @return The storagePoolUri
     */
    public String getStoragePoolUri() {
        return storagePoolUri;
    }

    /**
     * 
     * @param storagePoolUri
     *            The storagePoolUri
     */
    public void setStoragePoolUri(final String storagePoolUri) {
        this.storagePoolUri = storagePoolUri;
    }

}
