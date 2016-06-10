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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class PhysicalPort implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer interconnectPort;
    private String interconnectUri;
    private String mac;
    private Integer portNumber;
    private PhysicalPort.Type type;
    private List<VirtualPort> virtualPorts = new ArrayList<VirtualPort>();
    private String wwn;

    /**
     * 
     * @return The interconnectPort
     */
    public Integer getInterconnectPort() {
        return interconnectPort;
    }

    /**
     * 
     * @param interconnectPort
     *            The interconnectPort
     */
    public void setInterconnectPort(final Integer interconnectPort) {
        this.interconnectPort = interconnectPort;
    }

    /**
     * 
     * @return The interconnectUri
     */
    public String getInterconnectUri() {
        return interconnectUri;
    }

    /**
     * 
     * @param interconnectUri
     *            The interconnectUri
     */
    public void setInterconnectUri(final String interconnectUri) {
        this.interconnectUri = interconnectUri;
    }

    /**
     * 
     * @return The mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * 
     * @param mac
     *            The mac
     */
    public void setMac(final String mac) {
        this.mac = mac;
    }

    /**
     * 
     * @return The portNumber
     */
    public Integer getPortNumber() {
        return portNumber;
    }

    /**
     * 
     * @param portNumber
     *            The portNumber
     */
    public void setPortNumber(final Integer portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * 
     * @return The type
     */
    public PhysicalPort.Type getType() {
        return type;
    }

    /**
     * 
     * @param type
     *            The type
     */
    public void setType(final PhysicalPort.Type type) {
        this.type = type;
    }

    /**
     * 
     * @return The virtualPorts
     */
    public List<VirtualPort> getVirtualPorts() {
        return virtualPorts;
    }

    /**
     * 
     * @param virtualPorts
     *            The virtualPorts
     */
    public void setVirtualPorts(final List<VirtualPort> virtualPorts) {
        this.virtualPorts = virtualPorts;
    }

    /**
     * 
     * @return The wwn
     */
    public String getWwn() {
        return wwn;
    }

    /**
     * 
     * @param wwn
     *            The wwn
     */
    public void setWwn(final String wwn) {
        this.wwn = wwn;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(interconnectPort).append(interconnectUri).append(mac).append(portNumber).append(type)
                .append(virtualPorts).append(wwn).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PhysicalPort) == false) {
            return false;
        }
        final PhysicalPort rhs = ((PhysicalPort) other);
        return new EqualsBuilder().append(interconnectPort, rhs.interconnectPort).append(interconnectUri, rhs.interconnectUri)
                .append(mac, rhs.mac).append(portNumber, rhs.portNumber).append(type, rhs.type)
                .append(virtualPorts, rhs.virtualPorts).append(wwn, rhs.wwn).isEquals();
    }

    public static enum Type {

        Ethernet("Ethernet"), FibreChannel("FibreChannel"), InfiniBand("InfiniBand");
        private final String value;
        private static Map<String, PhysicalPort.Type> constants = new HashMap<String, PhysicalPort.Type>();

        static {
            for (final PhysicalPort.Type c : values()) {
                constants.put(c.value, c);
            }
        }

        private Type(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static PhysicalPort.Type fromValue(final String value) {
            final PhysicalPort.Type constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
