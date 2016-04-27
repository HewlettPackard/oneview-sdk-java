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

public class AddStoragePool implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String poolName;
    private String storageSystemUri;

    /**
     * 
     * @return The poolName
     */
    public String getPoolName() {
        return poolName;
    }

    /**
     * 
     * @param poolName
     *            The poolName
     */
    public void setPoolName(final String poolName) {
        this.poolName = poolName;
    }

    /**
     * 
     * @return The storageSystemUri
     */
    public String getStorageSystemUri() {
        return storageSystemUri;
    }

    /**
     * 
     * @param storageSystemUri
     *            The storageSystemUri
     */
    public void setStorageSystemUri(final String storageSystemUri) {
        this.storageSystemUri = storageSystemUri;
    }

}
