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
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.ServerHardware;

public class Messages implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 
     * (Required)
     * 
     */
    private List<ServerHardware> serverHardware = new ArrayList<ServerHardware>();
    private String eTag;
    private FirmwareStatus firmwareStatus;
    private List<Connection> connections = new ArrayList<Connection>();
    private String type;

    /**
     * 
     * (Required)
     * 
     * @return The serverHardware
     */
    public List<ServerHardware> getServerHardware() {
        return serverHardware;
    }

    /**
     * 
     * (Required)
     * 
     * @param serverHardware
     *            The serverHardware
     */
    public void setServerHardware(final List<ServerHardware> serverHardware) {
        this.serverHardware = serverHardware;
    }

    /**
     * 
     * @return The eTag
     */
    public String getETag() {
        return eTag;
    }

    /**
     * 
     * @param eTag
     *            The eTag
     */
    public void setETag(final String eTag) {
        this.eTag = eTag;
    }

    /**
     * 
     * @return The firmwareStatus
     */
    public FirmwareStatus getFirmwareStatus() {
        return firmwareStatus;
    }

    /**
     * 
     * @param firmwareStatus
     *            The firmwareStatus
     */
    public void setFirmwareStatus(final FirmwareStatus firmwareStatus) {
        this.firmwareStatus = firmwareStatus;
    }

    /**
     * 
     * @return The connections
     */
    public List<Connection> getConnections() {
        return connections;
    }

    /**
     * 
     * @param connections
     *            The connections
     */
    public void setConnections(final List<Connection> connections) {
        this.connections = connections;
    }

    /**
     * 
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *            The type
     */
    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(serverHardware).append(eTag).append(firmwareStatus).append(connections).append(type)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Messages) == false) {
            return false;
        }
        final Messages rhs = ((Messages) other);
        return new EqualsBuilder().append(serverHardware, rhs.serverHardware).append(eTag, rhs.eTag)
                .append(firmwareStatus, rhs.firmwareStatus).append(connections, rhs.connections).append(type, rhs.type).isEquals();
    }

}
