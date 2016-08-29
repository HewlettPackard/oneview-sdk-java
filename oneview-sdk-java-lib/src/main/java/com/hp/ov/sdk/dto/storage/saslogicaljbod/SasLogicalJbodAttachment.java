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

package com.hp.ov.sdk.dto.storage.saslogicaljbod;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class SasLogicalJbodAttachment extends BaseModelResource {

    private static final long serialVersionUID = 8593601776759596818L;

    private Integer mezzSlotNumber;
    private String sasLogicalJBODUri;
    private String serverHardwareUri;
    private String serverProfileUri;

    /**
     * @return the mezzSlotNumber
     */
    public Integer getMezzSlotNumber() {
        return mezzSlotNumber;
    }

    /**
     * @param mezzSlotNumber the mezzSlotNumber to set
     */
    public void setMezzSlotNumber(Integer mezzSlotNumber) {
        this.mezzSlotNumber = mezzSlotNumber;
    }

    /**
     * @return the sasLogicalJBODUri
     */
    public String getSasLogicalJBODUri() {
        return sasLogicalJBODUri;
    }

    /**
     * @param sasLogicalJBODUri the sasLogicalJBODUri to set
     */
    public void setSasLogicalJBODUri(String sasLogicalJBODUri) {
        this.sasLogicalJBODUri = sasLogicalJBODUri;
    }

    /**
     * @return the serverHardwareUri
     */
    public String getServerHardwareUri() {
        return serverHardwareUri;
    }

    /**
     * @param serverHardwareUri the serverHardwareUri to set
     */
    public void setServerHardwareUri(String serverHardwareUri) {
        this.serverHardwareUri = serverHardwareUri;
    }

    /**
     * @return the serverProfileUri
     */
    public String getServerProfileUri() {
        return serverProfileUri;
    }

    /**
     * @param serverProfileUri the serverProfileUri to set
     */
    public void setServerProfileUri(String serverProfileUri) {
        this.serverProfileUri = serverProfileUri;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
