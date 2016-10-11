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
package com.hp.ov.sdk.dto.facilities.powerdeliverydevice;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PowerConnection implements Serializable{

    private static final long serialVersionUID = 1L;

    private String connectionUri;
    private Integer deviceConnection;
    private Integer sourceConnection;

    /**
     * @return the connectionUri
     */
    public String getConnectionUri() {
        return connectionUri;
    }

    /**
     * @param connectionUri the connectionUri to set
     */
    public void setConnectionUri(String connectionUri) {
        this.connectionUri = connectionUri;
    }

    /**
     * @return the deviceConnection
     */
    public Integer getDeviceConnection() {
        return deviceConnection;
    }

    /**
     * @param deviceConnection the deviceConnection to set
     */
    public void setDeviceConnection(Integer deviceConnection) {
        this.deviceConnection = deviceConnection;
    }

    /**
     * @return the sourceConnection
     */
    public Integer getSourceConnection() {
        return sourceConnection;
    }

    /**
     * @param sourceConnection the sourceConnection to set
     */
    public void setSourceConnection(Integer sourceConnection) {
        this.sourceConnection = sourceConnection;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        PowerConnection that = (PowerConnection) obj;

        return new EqualsBuilder()
                .append(connectionUri, that.connectionUri)
                .append(deviceConnection, that.deviceConnection)
                .append(sourceConnection, that.sourceConnection)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(connectionUri)
                .append(deviceConnection)
                .append(sourceConnection)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("connectionUri", connectionUri)
                .append("deviceConnection", deviceConnection)
                .append("sourceConnection", sourceConnection)
                .toString();
    }

}
