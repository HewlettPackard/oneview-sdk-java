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
package com.hp.ov.sdk.dto.networking;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class NeighborConfigInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String remoteChassisId;
    private String remotePortId;

    /**
     * @return the remoteChassisId
     */
    public String getRemoteChassisId() {
        return remoteChassisId;
    }

    /**
     * @param remoteChassisId the remoteChassisId to set
     */
    public void setRemoteChassisId(String remoteChassisId) {
        this.remoteChassisId = remoteChassisId;
    }

    /**
     * @return the remotePortId
     */
    public String getRemotePortId() {
        return remotePortId;
    }

    /**
     * @param remotePortId the remotePortId to set
     */
    public void setRemotePortId(String remotePortId) {
        this.remotePortId = remotePortId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
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
